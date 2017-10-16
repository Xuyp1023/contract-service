// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月25日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.dubbo;

import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.modules.contract.IContractStamperService;
import com.betterjr.modules.contract.entity.ContractStamper;
import com.betterjr.modules.contract.service.ContractStamperService;
import com.betterjr.modules.rule.service.RuleServiceDubboFilterInvoker;

/**
 * @author liuwl
 *
 */
@Service(interfaceClass = IContractStamperService.class)
public class ContractStamperDubboService implements IContractStamperService {
    @Resource
    private ContractStamperService contractStamperService;

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStamperService#webQueryOwnStamper()
     */
    @Override
    public String webQueryOwnStamper(final Long anCustNo, final int anFlag, final int anPageNum, final int anPageSize) {
        return AjaxObject.newOkWithPage("印章查询成功！",
                contractStamperService.queryOwnStamper(anCustNo, anFlag, anPageNum, anPageSize)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStamperService#webSaveAddOwnStamper(java.util.Map, java.lang.String)
     */
    @Override
    public String webSaveAddOwnStamper(final Map<String, Object> anParam, final String anOriginFileId) {
        final ContractStamper contractStamper = RuleServiceDubboFilterInvoker.getInputObj();
        return AjaxObject.newOk("上传合同印章成功！", contractStamperService.saveAddOwnStamper(contractStamper, anOriginFileId))
                .toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStamperService#webSaveAddStamper(java.util.Map, java.lang.String, java.lang.String)
     */
    @Override
    public String webSaveAddStamper(final Map<String, Object> anParam, final String anOriginFileId,
            final String anFileId) {
        final ContractStamper contractStamper = RuleServiceDubboFilterInvoker.getInputObj();
        return AjaxObject
                .newOk("上传合同印章成功！", contractStamperService.saveAddStamper(contractStamper, anOriginFileId, anFileId))
                .toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStamperService#webSaveMakeStamper(java.lang.Long, java.lang.String)
     */
    @Override
    public String webSaveMakeStamper(final Long anId, final String anFileId) {
        return AjaxObject.newOk("制作合同印章成功！", contractStamperService.saveMakeStamper(anId, anFileId)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStamperService#webQueryAllStamper(java.util.Map)
     */
    @Override
    public String webQueryAllStamper(final Map<String, Object> anParam, final int anFlag, final int anPageNum,
            final int anPageSize) {
        final Map<String, Object> param = RuleServiceDubboFilterInvoker.getInputObj();
        return AjaxObject
                .newOkWithPage("印章查询成功！", contractStamperService.queryAllStamper(param, anFlag, anPageNum, anPageSize))
                .toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStamperService#webFindStamper(java.lang.Long)
     */
    @Override
    public String webFindStamper(final Long anId) {
        return AjaxObject.newOk("查询合同印章成功！", contractStamperService.findStamper(anId)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStamperService#webFindCheckStamper(java.lang.Long)
     */
    @Override
    public String webFindCheckStamper(final Long anCustNo) {
        return AjaxObject.newOk("检查合同印章成功！", contractStamperService.findCheckStamper(anCustNo)).toJson();
    }

}
