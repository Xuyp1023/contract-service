// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.3 : 2017年4月17日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.common.utils.Collections3;
import com.betterjr.common.utils.QueryTermBuilder;
import com.betterjr.common.utils.UserUtils;
import com.betterjr.mapper.pagehelper.Page;
import com.betterjr.modules.account.entity.CustOperatorInfo;
import com.betterjr.modules.contract.dao.ContractCorpAccountMapper;
import com.betterjr.modules.contract.entity.ContractCorpAccount;
import com.betterjr.modules.contract.entity.ContractSignerAccount;
import com.betterjr.modules.customer.ICustMechBaseService;
import com.betterjr.modules.customer.entity.CustMechBase;

/**
 * @author liuwl
 *
 */
@Service
public class ContractCorpAccountService extends BaseService<ContractCorpAccountMapper, ContractCorpAccount> {
    @Reference(interfaceClass = ICustMechBaseService.class)
    private ICustMechBaseService custMechBaseService;

    @Resource
    private ContractSignerAccountService contractSignerAccountService;

    @Autowired
    private EsignFactory signFacory;

    /**
     *
     * @param anCustNo
     * @return
     */
    public boolean checkCorpAccount(final Long anCustNo) {
        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("custNo", anCustNo);

        return this.selectByProperty(conditionMap).size() == 1;
    }

    /**
     *
     * @param anCorpAccount
     * @return
     */
    public ContractCorpAccount saveRegistCorpAccount(ContractCorpAccount anCorpAccount) {
        final Long custNo = anCorpAccount.getCustNo();

        final CustMechBase mechBase = custMechBaseService.findBaseInfo(custNo);
        BTAssert.notNull(mechBase, "没有找到公司信息");

        BTAssert.isTrue(BetterStringUtils.equals(mechBase.getOperOrg(), UserUtils.getOperOrg()), "操作失败！");

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        anCorpAccount.init(operator);
        anCorpAccount.setBusinStatus("0");
        final int result = this.insert(anCorpAccount);
        anCorpAccount = signFacory.registCorpAccount(anCorpAccount);
        this.updateByPrimaryKey(anCorpAccount);
        BTAssert.isTrue(result == 1, "电子合同服务企业注册失败！");

        return anCorpAccount;
    }

    /**
     *
     * @param anId
     * @return
     */
    public ContractCorpAccount findCorpAccount(final Long anId) {
        final ContractCorpAccount corpAccount = this.selectByPrimaryKey(anId);

        return corpAccount;
    }

    /**
     *
     * @param anCorpAccountId
     * @param anSignerAccountId
     * @return
     */
    public ContractCorpAccount saveCorpSigner(final Long anCorpAccountId, final Long anSignerAccountId) {
        final ContractCorpAccount corpAccount = findCorpAccount(anCorpAccountId);
        BTAssert.notNull(corpAccount, "没有找到电子合同服务企业注册信息！");

        final ContractSignerAccount signerAccount = contractSignerAccountService.findSignerAccount(anSignerAccountId);
        BTAssert.notNull(corpAccount, "没有找到电子合同服务个人注册信息！");

        BTAssert.isTrue(BetterStringUtils.equals(corpAccount.getOperOrg(), UserUtils.getOperOrg()), "操作失败！");
        BTAssert.isTrue(BetterStringUtils.equals(signerAccount.getOperOrg(), UserUtils.getOperOrg()), "操作失败！");

        BTAssert.isTrue(corpAccount.getServiceCustNo().equals(signerAccount.getServiceCustNo()), "合同服务商不一致！");

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();

        corpAccount.setSignerId(signerAccount.getId());
        corpAccount.setSignerOperId(signerAccount.getOperId());
        corpAccount.setSignerOperName(signerAccount.getOperName());
        corpAccount.setSignerAccount(signerAccount.getAccount());
        corpAccount.setSignerMobileNo(signerAccount.getMobileNo());

        corpAccount.modify(operator);

        final int result = this.updateByPrimaryKeySelective(corpAccount);
        BTAssert.isTrue(result == 1, "设置企业签署人失败！");
        return corpAccount;
    }

    /**
     * @param anCustNo
     * @return
     */
    public Page<ContractCorpAccount> queryCorpAccountInfo(final Long anCustNo, final int anFlag, final int anPageNum, final int anPageSize) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");

        final Map<String, Object> conditionMap = QueryTermBuilder.newInstance().put("custNo", anCustNo).build();

        return this.selectPropertyByPage(conditionMap, anPageNum, anPageSize, anFlag == 1);
    }

    /**
     * 查找在电子合同签署的账号信息
     * 
     * @param anOperId
     *            操作员编号
     * @return
     */
    public String findSignAccountId(final Long anCustNo, final Long anServiceCustNo) {
        final Map<String, Object> queryTerm = QueryTermBuilder.newInstance().put("custNo", anCustNo).put("serviceCustNo", anServiceCustNo)
                .put("businStatus", "1").build();
        final List<ContractCorpAccount> tmpList = this.selectByProperty(queryTerm);
        if (Collections3.isEmpty(tmpList)) {
            return "";
        }
        else {
            return Collections3.getFirst(tmpList).getAccount();
        }
    }
}
