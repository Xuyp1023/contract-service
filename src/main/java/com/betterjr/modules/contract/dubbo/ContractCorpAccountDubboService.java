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
import com.betterjr.modules.contract.IContractCorpAccountService;
import com.betterjr.modules.contract.entity.ContractCorpAccount;
import com.betterjr.modules.contract.service.ContractCorpAccountService;
import com.betterjr.modules.rule.service.RuleServiceDubboFilterInvoker;

/**
 * @author liuwl
 *
 */
@Service(interfaceClass = IContractCorpAccountService.class)
public class ContractCorpAccountDubboService implements IContractCorpAccountService {
    @Resource
    private ContractCorpAccountService contractCorpAccountService;

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractCorpAccountService#webSaveRegistCorpAccount(java.util.Map)
     */
    @Override
    public String webSaveRegistCorpAccount(final Map<String, Object> anParam) {
        final ContractCorpAccount contractCorpAccount = RuleServiceDubboFilterInvoker.getInputObj();

        return AjaxObject.newOk("注册企业帐号成功！", contractCorpAccountService.saveRegistCorpAccount(contractCorpAccount)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractCorpAccountService#webCheckCorpAccount(java.lang.Long)
     */
    @Override
    public String webCheckCorpAccount(final Long anCustNo) {

        return AjaxObject.newOk("检查企业帐号成功！", contractCorpAccountService.checkCorpAccount(anCustNo)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractCorpAccountService#webFindCorpInfo(java.lang.Long)
     */
    @Override
    public String webFindCorpInfo(final Long anId) {
        return AjaxObject.newOk("查询企业帐号成功！", contractCorpAccountService.findCorpAccount(anId)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractCorpAccountService#webSaveCorpSigner(java.lang.Long, java.lang.Long)
     */
    @Override
    public String webSaveCorpSigner(final Long anCorpAccountId, final Long anSignerAccountId) {
        return AjaxObject.newOk("设置企业帐号签署人成功！", contractCorpAccountService.saveCorpSigner(anCorpAccountId, anSignerAccountId)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractCorpAccountService#webQueryCorpAccountInfo(java.lang.Long)
     */
    @Override
    public String webQueryCorpAccountInfo(final Long anCustNo) {
        // TODO Auto-generated method stub
        return null;
    }

}
