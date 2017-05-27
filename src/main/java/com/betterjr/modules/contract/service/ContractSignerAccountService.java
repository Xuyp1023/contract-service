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

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.common.utils.QueryTermBuilder;
import com.betterjr.common.utils.UserUtils;
import com.betterjr.mapper.pagehelper.Page;
import com.betterjr.modules.account.dubbo.interfaces.ICustOperatorService;
import com.betterjr.modules.account.entity.CustOperatorInfo;
import com.betterjr.modules.contract.dao.ContractSignerAccountMapper;
import com.betterjr.modules.contract.entity.ContractSignerAccount;

/**
 * @author liuwl
 *
 */
@Service
public class ContractSignerAccountService extends BaseService<ContractSignerAccountMapper, ContractSignerAccount> {

    @Reference(interfaceClass = ICustOperatorService.class)
    private ICustOperatorService custOperatorService;

    /**
     *
     * @param anOperId
     * @return
     */
    public boolean checkSignerAccount(final Long anOperId) {
        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("operId", anOperId);

        return this.selectByProperty(conditionMap).size() == 1;
    }

    /**
     *
     * @param anSignerAccount
     * @return
     */
    public ContractSignerAccount saveRegistSignerAccount(final ContractSignerAccount anSignerAccount) {
        final Long operId = anSignerAccount.getOperId();
        final CustOperatorInfo custOperator = custOperatorService.findCustOperatorById(operId);

        BTAssert.notNull(custOperator, "没有找到此操作员！");
        BTAssert.isTrue(BetterStringUtils.equals(custOperator.getOperOrg(), UserUtils.getOperOrg()), "操作失败！");

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        BTAssert.isTrue(BetterStringUtils.equals(operator.getOperOrg(), UserUtils.getOperOrg()), "操作失败！");

        anSignerAccount.setOperName(custOperator.getName());
        anSignerAccount.setOperOrg(UserUtils.getOperOrg());


        // TODO 注册，获取 account

        anSignerAccount.init(operator);

        final int result = this.insert(anSignerAccount);
        BTAssert.isTrue(result == 1, "注册失败！");
        return anSignerAccount;
    }

    /**
     * @param anSignerAccountId
     * @return
     */
    public ContractSignerAccount findSignerAccount(final Long anSignerAccountId) {
        final ContractSignerAccount signerAccount = this.selectByPrimaryKey(anSignerAccountId);

        BTAssert.notNull(signerAccount, "没有找到相应数据!");

        return signerAccount;
    }

    /**
     * @param anCustNo
     * @param anPageSize
     * @param anPageNum
     * @param anFlag
     * @return
     */
    public Page<ContractSignerAccount> querySignerAccountInfo(final Long anCustNo, final int anFlag, final int anPageNum, final int anPageSize) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");

        final Map<String, Object> conditionMap = QueryTermBuilder.newInstance().put("custNo", anCustNo).build();

        return this.selectPropertyByPage(conditionMap, anPageNum, anPageSize, anFlag == 1);
    }

    /**
     * @param anId
     * @return
     */
    public CustOperatorInfo findOperInfo(final Long anId) {
        final CustOperatorInfo custOperator = custOperatorService.findCustOperatorById(anId);

        BTAssert.notNull(custOperator, "没有找到此操作员！");

        return custOperator;
    }
}
