// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.3 : 2017年4月17日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.common.utils.UserUtils;
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
    public ContractCorpAccount saveRegistCorpAccount(final ContractCorpAccount anCorpAccount) {
        final Long custNo = anCorpAccount.getCustNo();

        final CustMechBase mechBase = custMechBaseService.findBaseInfo(custNo);
        BTAssert.notNull(mechBase, "没有找到公司信息");

        BTAssert.isTrue(BetterStringUtils.equals(mechBase.getOperOrg(), UserUtils.getOperOrg()), "操作失败！");

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        anCorpAccount.init(operator);

        final int result = this.insert(anCorpAccount);

        BTAssert.isTrue(result == 1, "电子合同服务企业注册失败！");

        return anCorpAccount;
    }

    /**
     *
     * @param anId
     * @return
     */
    public ContractCorpAccount findCorpAccount(final Long anId) {
        final ContractCorpAccount corpAccount =  this.selectByPrimaryKey(anId);

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
}
