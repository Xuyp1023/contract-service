// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月19日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.dubbo;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.modules.contract.IContractTemplateService;
import com.betterjr.modules.contract.data.ContractTempStampPlaceData;
import com.betterjr.modules.contract.service.ContractTempStampPlaceService;
import com.betterjr.modules.contract.service.ContractTemplateService;
import com.betterjr.modules.rule.service.RuleServiceDubboFilterInvoker;

/**
 * @author liuwl
 *
 */
@Service(interfaceClass = IContractTemplateService.class)
public class ContractTemplateDubboService implements IContractTemplateService {
    @Resource
    private ContractTemplateService contractTemplateService;

    @Resource
    private ContractTempStampPlaceService tempStampPlaceService;

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryStandardType(java.lang.Long, java.lang.Long)
     */
    @Override
    public String webQueryStandardType(final Long anCustNo, final String anBusinStatus, final int anFlag,
            final int anPageNum, final int anPageSize) {
        return AjaxObject.newOkWithPage("标准合同查询成功！",
                contractTemplateService.queryStandardType(anCustNo, anBusinStatus, anFlag, anPageNum, anPageSize))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryUnusedStandardType(java.lang.Long, java.lang.Long)
     */
    @Override
    public String webQueryUnusedStandardType(final Long anCustNo, final Long anTypeId) {
        return AjaxObject.newOk("未启用标准合同查询成功！", contractTemplateService.queryUnusedStandardType(anCustNo, anTypeId))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webSaveEnableStandardType(java.lang.Long, java.lang.String)
     */
    @Override
    public String webSaveEnableStandardType(final Long anCustNo, final String anStandardTypeIds) {
        return AjaxObject.newOk("启用标准合同成功", contractTemplateService.saveEnableStandardType(anCustNo, anStandardTypeIds))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webSaveRemoveStandardType(java.lang.Long, java.lang.Long)
     */
    @Override
    public String webSaveRemoveStandardType(final Long anCustNo, final Long anStandardTypeId) {
        return AjaxObject.newOk("移除标准合同成功", contractTemplateService.saveRemoveStandardType(anCustNo, anStandardTypeId))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryUnusedConstractTemplate(java.lang.Long, java.lang.Long, int, int, int)
     */
    @Override
    public String webQueryUnusedConstractTemplate(final Long anCustNo, final Long anTypeId, final int anFlag,
            final int anPageNum, final int anPageSize) {
        return AjaxObject.newOkWithPage("未使用标准合同查询成功！",
                contractTemplateService.queryUnusedContractTemplate(anCustNo, anTypeId, anFlag, anPageNum, anPageSize))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryContractTemplate(java.lang.Long, java.lang.String, java.lang.String,
     * java.lang.String, int, int, int)
     */
    @Override
    public String webQueryText(final Map<String, Object> anParam, final int anFlag, final int anPageNum,
            final int anPageSize) {
        final Map<String, Object> param = RuleServiceDubboFilterInvoker.getInputObj();
        return AjaxObject
                .newOkWithPage("标准合同查询成功！", contractTemplateService.queryText(param, anFlag, anPageNum, anPageSize))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryContractTemplate(java.lang.Long, java.lang.String, java.lang.String,
     * java.lang.String, int, int, int)
     */
    @Override
    public String webQueryAuditText(final Map<String, Object> anParam, final int anFlag, final int anPageNum,
            final int anPageSize) {
        final Map<String, Object> param = RuleServiceDubboFilterInvoker.getInputObj();
        return AjaxObject.newOkWithPage("审核标准合同文本查询成功！",
                contractTemplateService.queryAuditText(param, anFlag, anPageNum, anPageSize)).toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webSaveUploadOriginTemplate(java.lang.Long, java.lang.String, java.lang.String,
     * java.lang.Long, java.lang.String)
     */
    @Override
    public String webSaveUploadText(final Map<String, Object> anParam) {
        final Map<String, Object> param = RuleServiceDubboFilterInvoker.getInputObj();
        final Long templateId = Long.valueOf((String) param.get("templateId"));
        final String originTemplateId = (String) param.get("originTemplateId");
        final String originSimpleId = (String) param.get("originSimpleId");
        final Long originSignerCount = Long.valueOf((String) param.get("originSignerCount"));
        final String originNoPattern = (String) param.get("originNoPattern");
        final String originComment = (String) param.get("originComment");

        return AjaxObject.newOk("上传标准合同文本成功", contractTemplateService.saveUploadText(templateId, originTemplateId,
                originSimpleId, originSignerCount, originNoPattern, originComment)).toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webSaveAuditText(java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public String webSaveAuditText(final Long anTemplateId, final String anAuditStatus, final String anAuditRemark) {
        return AjaxObject
                .newOk("审核标准合同文本成功", contractTemplateService.saveAuditText(anTemplateId, anAuditStatus, anAuditRemark))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryUnusedText(java.lang.Long, int, int, int)
     */
    @Override
    public String webQueryUnusedText(final Long anCustNo, final int anFlag, final int anPageNum, final int anPageSize) {
        return AjaxObject.newOkWithPage("查询已审核标准合同文本查询成功！",
                contractTemplateService.queryUnusedText(anCustNo, anFlag, anPageNum, anPageSize)).toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webFindTemplateDetail(java.lang.Long)
     */
    @Override
    public String webFindTemplateDetail(final Long anId) {
        return AjaxObject.newOk("查看标准合同详情成功", contractTemplateService.findTemplateDetail(anId)).toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryTemplate(java.util.Map, int, int, int)
     */
    @Override
    public String webQueryTemplate(final Map<String, Object> anParam, final int anFlag, final int anPageNum,
            final int anPageSize) {
        final Map<String, Object> param = RuleServiceDubboFilterInvoker.getInputObj();
        return AjaxObject
                .newOkWithPage("标准合同查询成功！", contractTemplateService.queryTemplate(param, anFlag, anPageNum, anPageSize))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryAuditTemplate(java.util.Map, int, int, int)
     */
    @Override
    public String webQueryAuditTemplate(final Map<String, Object> anParam, final int anFlag, final int anPageNum,
            final int anPageSize) {
        final Map<String, Object> param = RuleServiceDubboFilterInvoker.getInputObj();
        return AjaxObject.newOkWithPage("审核标准合同文本查询成功！",
                contractTemplateService.queryAuditTemplate(param, anFlag, anPageNum, anPageSize)).toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webSaveUploadTemplate(java.util.Map)
     */
    @Override
    public String webSaveUploadTemplate(final Long anTemplateId, final String anTemplateFileId, final String anCommon) {
        return AjaxObject.newOk("上传标准合同模板成功",
                contractTemplateService.saveUploadTemplate(anTemplateId, anTemplateFileId, anCommon)).toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webSaveStampPlace(java.lang.Long, java.util.List)
     */
    @Override
    public String webSaveStampPlace(final Long anTemplateId, final List<Map<String, Object>> anStampPlaces) {
        return AjaxObject.newOk("上传标准合同模板成功", contractTemplateService.saveStampPlace(anTemplateId, anStampPlaces))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webSaveAuditTemplate(java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public String webSaveAuditTemplate(final Long anCustNo, final Long anTemplateId, final String anAuditStatus,
            final String anAuditRemark) {
        return AjaxObject
                .newOk("审核标准合同模板成功",
                        contractTemplateService.saveAuditTemplate(anCustNo, anTemplateId, anAuditStatus, anAuditRemark))
                .toJson();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.betterjr.modules.contract.IContractTemplateService#webQueryTemplateLog(java.lang.Long)
     */
    @Override
    public String webQueryTemplateLog(final Long anTemplateId) {
        return AjaxObject.newOk("标准合同操作记录查询成功", contractTemplateService.queryTemplateLog(anTemplateId)).toJson();
    }

    @Override
    public ContractTempStampPlaceData findStampPlaceData(final Long anTemplateId, final Integer anOrder) {

        return tempStampPlaceService.findStampPlaceData(anTemplateId, anOrder);
    }

}
