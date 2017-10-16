// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月26日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.dubbo;

import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.modules.contract.IContractSignerAccountService;
import com.betterjr.modules.contract.entity.ContractSignerAccount;
import com.betterjr.modules.contract.service.ContractSignerAccountService;
import com.betterjr.modules.rule.service.RuleServiceDubboFilterInvoker;

/**
 * @author liuwl
 *
 */
@Service(interfaceClass = IContractSignerAccountService.class)
public class ContractSignerAccountDubboService implements IContractSignerAccountService {
    @Resource
    private ContractSignerAccountService contractSignerAccountService;

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractSignerAccountService#webSaveRegistSignerAccount(java.util.Map)
     */
    @Override
    public String webSaveRegistSignerAccount(final Map<String, Object> anParam) {
        final ContractSignerAccount contractSignerAccount = RuleServiceDubboFilterInvoker.getInputObj();

        return AjaxObject.newOk("注册签署人成功！", contractSignerAccountService.saveRegistSignerAccount(contractSignerAccount))
                .toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractSignerAccountService#webFindOperInfo(java.lang.Long)
     */
    @Override
    public String webFindOperInfo(final Long anId) {
        return AjaxObject.newOk("查询签署人成功！", contractSignerAccountService.findOperInfo(anId)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractSignerAccountService#webCheckSignerAccount(java.lang.Long)
     */
    @Override
    public String webCheckSignerAccount(final Long anOperId) {
        return AjaxObject.newOk("检查签署人成功！", contractSignerAccountService.checkSignerAccount(anOperId)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractSignerAccountService#webQuerySignerAccountInfo(java.lang.Long)
     */
    @Override
    public String webQuerySignerAccountInfo(final Long anCustNo, final int anFlag, final int anPageNum,
            final int anPageSize) {
        return AjaxObject
                .newOkWithPage("检查签署人成功！",
                        contractSignerAccountService.querySignerAccountInfo(anCustNo, anFlag, anPageNum, anPageSize))
                .toJson();
    }

}
