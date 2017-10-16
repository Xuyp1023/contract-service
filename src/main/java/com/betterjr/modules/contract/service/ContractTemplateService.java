// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.3 : 2017年4月17日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.exception.BytterException;
import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.BetterDateUtils;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.common.utils.Collections3;
import com.betterjr.common.utils.UserUtils;
import com.betterjr.mapper.pagehelper.Page;
import com.betterjr.modules.account.entity.CustOperatorInfo;
import com.betterjr.modules.contract.dao.ContractTemplateMapper;
import com.betterjr.modules.contract.entity.ContractStandardType;
import com.betterjr.modules.contract.entity.ContractTemplate;
import com.betterjr.modules.contract.entity.ContractTemplateLog;
import com.betterjr.modules.contract.entity.ContractTemplateStampPlace;
import com.betterjr.modules.customer.ICustMechBaseService;
import com.betterjr.modules.customer.entity.CustMechBase;
import com.betterjr.modules.document.ICustFileService;
import com.betterjr.modules.document.entity.CustFileItem;
import com.betterjr.modules.generator.SequenceFactory;

/**
 * @author liuwl
 *
 */
@Service
public class ContractTemplateService extends BaseService<ContractTemplateMapper, ContractTemplate> {
    private static final Logger logger = LoggerFactory.getLogger(ContractTemplateService.class);

    private static final Pattern COMMA_PATTERN = Pattern.compile(",");

    @Reference(interfaceClass = ICustFileService.class)
    private ICustFileService custFileService;

    @Reference(interfaceClass = ICustMechBaseService.class)
    private ICustMechBaseService custMechBaseService;

    @Resource
    private ContractTemplateLogService contractTemplateLogService;

    @Resource
    private ContractStandardTypeService contractStandardTypeService;

    @Resource
    private ContractTempStampPlaceService contractTemplateStampPlaceService;

    /**
     * 获取本公司 标准合同列表
     *
     * @param anCustNo
     * @param anPageSize
     * @param anPageNum
     * @param anFlag
     * @return
     */
    public Page<ContractTemplate> queryStandardType(final Long anCustNo, final String anBusinStatus, final int anFlag,
            final int anPageNum, final int anPageSize) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");

        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anCustNo);
        BTAssert.notNull(custMechBase, "没有找到公司信息！");
        BTAssert.isTrue(StringUtils.equals(UserUtils.getOperatorInfo().getOperOrg(), custMechBase.getOperOrg()),
                "操作失败！");
        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("custNo", anCustNo);
        conditionMap.put("operOrg", UserUtils.getOperatorInfo().getOperOrg());

        if (StringUtils.equals(anBusinStatus, "00")) {
            conditionMap.put("businStatus", "00");
        } else { // 除了 未使用的
            conditionMap.remove("businStatus");
            conditionMap.put("NEbusinStatus", "00");
        }

        return this.selectPropertyByPage(conditionMap, anPageNum, anPageSize, anFlag == 1);
    }

    /**
     * 获取尚未 使用的标准合同类型列表
     *
     * @param anCustNo
     * @return
     */
    public List<ContractStandardType> queryUnusedStandardType(final Long anCustNo, final Long anTypeId) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");

        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anCustNo);
        BTAssert.notNull(custMechBase, "没有找到公司信息！");
        BTAssert.isTrue(StringUtils.equals(UserUtils.getOperatorInfo().getOperOrg(), custMechBase.getOperOrg()),
                "操作失败！");

        return contractStandardTypeService.queryUnusedStandardType(anTypeId, anCustNo);
    }

    /**
     * @param contractTemplates
     * @param contractStandardType
     * @return
     */
    public boolean checkExists(final List<ContractTemplate> contractTemplates,
            final ContractStandardType contractStandardType) {
        for (final ContractTemplate contractTemplate : contractTemplates) {
            if (contractTemplate.getStandardTypeId().equals(contractStandardType.getId())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param anCustNo
     * @param anStandardTypeId
     * @return
     */
    private ContractTemplate findContractTemplate(final Long anCustNo, final Long anStandardTypeId) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");

        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anCustNo);
        BTAssert.notNull(custMechBase, "没有找到公司信息！");

        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("custNo", anCustNo);
        conditionMap.put("standardTypeId", anStandardTypeId);

        final ContractTemplate contractTemplate = Collections3.getFirst(this.selectByProperty(conditionMap));

        return contractTemplate;
    }

    /**
     * @param anTemplateId
     * @return
     */
    private ContractTemplate findContractTemplate(final Long anTemplateId) {
        BTAssert.notNull(anTemplateId, "模板编号不允许为空！");
        final ContractTemplate contractTemplate = this.selectByPrimaryKey(anTemplateId);

        BTAssert.notNull(contractTemplate, "没有找到模板信息！");

        if (contractTemplate.getOriginTemplate() != null) {
            final CustFileItem originTemplate = custFileService.findOneByBatchNo(contractTemplate.getOriginTemplate());
            if (originTemplate != null) {
                contractTemplate.setOriginTemplateId(originTemplate.getId());
                contractTemplate.setOriginTemplateName(originTemplate.getFileName());
            }
        }

        if (contractTemplate.getOriginSimple() != null) {
            final CustFileItem originSimple = custFileService.findOneByBatchNo(contractTemplate.getOriginSimple());
            if (originSimple != null) {
                contractTemplate.setOriginSimpleId(originSimple.getId());
                contractTemplate.setOriginSimpleName(originSimple.getFileName());
            }
        }

        if (contractTemplate.getTemplate() != null) {
            final CustFileItem template = custFileService.findOneByBatchNo(contractTemplate.getTemplate());
            if (template != null) {
                contractTemplate.setTemplateId(template.getId());
                contractTemplate.setTemplateName(template.getFileName());
            }
        }

        // 取位置
        final List<ContractTemplateStampPlace> stampPlaces = contractTemplateStampPlaceService
                .queryStampPlace(anTemplateId);

        if (Collections3.isEmpty(stampPlaces)) {

            if (contractTemplate.getOriginSignerCount() != null) {
                final List<Map<String, Object>> tempStampPlaces = new ArrayList<>();

                for (int i = 0; i < contractTemplate.getOriginSignerCount().intValue(); i++) {
                    final Map<String, Object> stampPlace = new HashMap<>();
                    stampPlace.put("key", "");
                    stampPlace.put("signatory", ContractTempStampPlaceService.signatorys[i]);
                    stampPlace.put("axisX", 0L);
                    stampPlace.put("axisY", 0L);
                    stampPlace.put("sequence", i);
                    tempStampPlaces.add(stampPlace);
                }
                contractTemplate.setStampPlaces(tempStampPlaces);
            }
        } else {
            contractTemplate.setStampPlaces(stampPlaces.stream().map(contractTemplateStampPlace -> {
                final Map<String, Object> stampPlace = new HashMap<>();
                stampPlace.put("key", contractTemplateStampPlace.getKeyWord());
                stampPlace.put("signatory", contractTemplateStampPlace.getSignatory());
                stampPlace.put("axisX", contractTemplateStampPlace.getAxisX());
                stampPlace.put("axisY", contractTemplateStampPlace.getAxisY());
                stampPlace.put("sequence", contractTemplateStampPlace.getSequence());
                return stampPlace;
            }).collect(Collectors.toList()));
        }

        if (UserUtils.platformUser()
                || StringUtils.equals(contractTemplate.getOperOrg(), UserUtils.getOperatorInfo().getOperOrg())) {
            return contractTemplate;
        }
        throw new BytterException("操作失败!");
    }

    /**
     * 按批次启用
     *
     * @param anCustNo
     * @param anStandardTypeIds
     * @return
     */
    public boolean saveEnableStandardType(final Long anCustNo, final String anStandardTypeIds) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");
        final List<Long> standardTypeIds = COMMA_PATTERN.splitAsStream(anStandardTypeIds).map(Long::valueOf)
                .collect(Collectors.toList());
        for (final Long typeId : standardTypeIds) {
            final ContractTemplate template = saveEnableStandardType(anCustNo, typeId);
            BTAssert.notNull(template, "标准合同启用失败！");
        }

        return true;
    }

    /**
     * 保存选择的标准合同类型 为 未使用的 合同模板
     *
     * @param anStandardTypeId
     * @return
     */
    public ContractTemplate saveEnableStandardType(final Long anCustNo, final Long anStandardTypeId) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");
        final CustOperatorInfo operator = UserUtils.getOperatorInfo();

        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anCustNo);
        BTAssert.notNull(custMechBase, "没有找到公司信息！");

        final ContractStandardType standardType = contractStandardTypeService.findStandardType(anStandardTypeId);
        BTAssert.notNull(standardType, "没有找到标准合同类型信息！");

        BTAssert.isTrue(StringUtils.equals(operator.getOperOrg(), custMechBase.getOperOrg()), "操作失败！");

        final ContractTemplate contractTemplate = findContractTemplate(anCustNo, anStandardTypeId);
        BTAssert.isNull(contractTemplate, "选择的标准合同模板已经存在！");

        final ContractTemplate contractTemplateNew = new ContractTemplate();

        contractTemplateNew.setName(standardType.getName());
        contractTemplateNew.setTypeIdName(standardType.getTypeIdName());
        contractTemplateNew.setTypeId(standardType.getTypeId());
        contractTemplateNew.setStandardTypeId(standardType.getId());
        contractTemplateNew.setCustNo(custMechBase.getCustNo());
        contractTemplateNew.setCustName(custMechBase.getCustName());
        contractTemplateNew.setOperOrg(custMechBase.getOperOrg());
        contractTemplateNew.setBusinStatus("00"); // 启用状态 进入 未使用
        contractTemplateNew.init(operator);

        final int result = this.insert(contractTemplateNew);

        BTAssert.isTrue(result == 1, "启用标准合同类型失败！");

        return contractTemplateNew;
    }

    /**
     * 删除未使用的标准合同类型列表
     *
     * @param anCustNo
     * @param anStandardTypeId
     * @return
     */
    public boolean saveRemoveStandardType(final Long anCustNo, final Long anStandardTypeId) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");
        final CustOperatorInfo operator = UserUtils.getOperatorInfo();

        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anCustNo);
        BTAssert.notNull(custMechBase, "没有找到公司信息！");

        BTAssert.isTrue(StringUtils.equals(operator.getOperOrg(), custMechBase.getOperOrg()), "操作失败！");

        final ContractTemplate contractTemplate = findContractTemplate(anCustNo, anStandardTypeId);
        BTAssert.notNull(contractTemplate, "选择的标准合同模板不存在！");

        BTAssert.isTrue(StringUtils.equals("00", contractTemplate.getBusinStatus()), "该标准合同模板已经被使用，不允许删除！");

        final int result = this.delete(contractTemplate);

        return result == 1;
    }

    /**
     * 获取启用的模板
     *
     * @param anCustNo
     * @return
     */
    public Page<ContractTemplate> queryUnusedContractTemplate(final Long anCustNo, final Long anTypeId,
            final int anFlag, final int anPageNum, final int anPageSize) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");

        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anCustNo);
        BTAssert.notNull(custMechBase, "没有找到公司信息！");

        BTAssert.isTrue(StringUtils.equals(UserUtils.getOperatorInfo().getOperOrg(), custMechBase.getOperOrg()),
                "操作失败！");

        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("businStatus", "00");// 从未使用过的
        if (anTypeId != null) {
            conditionMap.put("typeId", anTypeId);
        }
        conditionMap.put("custNo", anCustNo);
        return this.selectPropertyByPage(conditionMap, anPageNum, anPageSize, anFlag == 1);
    }

    /**
     * 查询电子合同文本
     *
     * @param anCustNo
     * @return
     */
    public Page<ContractTemplate> queryText(final Map<String, Object> anParam, final int anFlag, final int anPageNum,
            final int anPageSize) {

        if (StringUtils.isBlank((String) anParam.get("custNo"))) {
            anParam.remove("custNo");
        } else {
            anParam.put("custNo", Long.valueOf((String) anParam.get("custNo")));
        }
        if (StringUtils.isBlank((String) anParam.get("textAuditStatus"))) {
            anParam.remove("textAuditStatus");
        }
        anParam.put("operOrg", UserUtils.getOperatorInfo().getOperOrg()); // 查询自己机构的数据
        anParam.put("businStatus", new String[] { "01", "02" });// 查询 电子合同文本, 包括进入电子合同模板阶段的数据
        return this.selectPropertyByPage(anParam, anPageNum, anPageSize, anFlag == 1, "originApplyNo Desc");
    }

    /**
     * 上传原始模板
     *
     * @param anCustNo
     * @param anStandardTypeId
     * @return
     */
    public ContractTemplate saveUploadText(final Long anTemplateId, final String anOriginTemplateId,
            final String anOrginSimpleId, final Long anOriginSignerCount, final String anOriginNoPattern,
            final String anOriginComment) {

        final ContractTemplate contractTemplate = findContractTemplate(anTemplateId);
        BTAssert.notNull(contractTemplate, "没有找到合同模板！");

        BTAssert.isTrue(
                StringUtils.equals(contractTemplate.getOperOrg(), UserUtils.getOperatorInfo().getOperOrg()),
                "操作失败！");

        BTAssert.isTrue(
                StringUtils.equals("00", contractTemplate.getBusinStatus())
                        || (StringUtils.equals("01", contractTemplate.getBusinStatus())
                                && StringUtils.equals("02", contractTemplate.getTextAuditStatus())),
                "该标准合同模板已经被上传模板，不允许重复上传！");

        if (StringUtils.equals("00", contractTemplate.getBusinStatus())) {
            contractTemplate
                    .setOriginApplyNo(SequenceFactory.generate("PLAT_COMMON", "#{Date:yyyyMMdd}#{Seq:12}", "D"));
        }
        contractTemplate.setOriginTemplate(custFileService.updateCustFileItemInfo(anOriginTemplateId, null));
        contractTemplate.setOriginSimple(custFileService.updateCustFileItemInfo(anOrginSimpleId, null));
        contractTemplate.setOriginDate(BetterDateUtils.getNumDate());
        contractTemplate.setOriginTime(BetterDateUtils.getNumTime());
        contractTemplate.setOriginSignerCount(anOriginSignerCount);
        contractTemplate.setOriginNoPattern(anOriginNoPattern);
        contractTemplate.setOriginComment(anOriginComment);

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        contractTemplate.setOriginOperId(operator.getId());
        contractTemplate.setOriginOperName(operator.getName());

        contractTemplate.setBusinStatus("01");
        contractTemplate.setTextAuditStatus("00"); // 回到未审核状态
        contractTemplate.modify(UserUtils.getOperatorInfo());

        final int result = this.updateByPrimaryKeySelective(contractTemplate);

        BTAssert.isTrue(result == 1, "上传标准合同模板失败！");
        // 根据文本审核状态，插入相应的日志
        if ("00".equals(contractTemplate.getTextAuditStatus())) {
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "00",
                    "上传标准合同文本" + "“" + custFileService.findOne(Long.valueOf(anOriginTemplateId)).getFileName() + "”");
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "00",
                    "上传标准合同样例" + "“" + custFileService.findOne(Long.valueOf(anOrginSimpleId)).getFileName() + "”");
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "00", "提交申请");
        } else {
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "00", "重新上传申请");
        }
        return contractTemplate;
    }

    /**
     * 查看详情
     *
     * @param anId
     * @return
     */
    public ContractTemplate findTemplateDetail(final Long anId) {
        return findContractTemplate(anId);
    }

    /**
     * 获取待审核的文本
     *
     * @param anCustNo
     * @return
     */
    public Page<ContractTemplate> queryAuditText(final Map<String, Object> anParam, final int anFlag,
            final int anPageNum, final int anPageSize) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败！");

        if (StringUtils.isBlank((String) anParam.get("custNo"))) {
            anParam.remove("custNo");
        } else {
            anParam.put("custNo", Long.valueOf((String) anParam.get("custNo")));
        }
        if (StringUtils.isBlank((String) anParam.get("textAuditStatus"))) {
            anParam.remove("textAuditStatus");
        }
        anParam.put("businStatus", new String[] { "01", "02" });// 查询 电子合同文本, 包括进入电子合同模板阶段的数据

        return this.selectPropertyByPage(anParam, anPageNum, anPageSize, anFlag == 1, "originApplyNo Desc");
    }

    /**
     * 审核原始模板
     *
     * @param anId
     * @param anAuditStatus
     * @param anAuditComment
     * @return
     */
    public ContractTemplate saveAuditText(final Long anTemplateId, final String anAuditStatus,
            final String anAuditRemark) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败!");

        BTAssert.isTrue(StringUtils.isNotBlank(anAuditStatus) || StringUtils.equals(anAuditStatus, "01")
                || StringUtils.equals(anAuditStatus, "02"), "审核状态不正确！");

        final ContractTemplate contractTemplate = findContractTemplate(anTemplateId);

        BTAssert.notNull(contractTemplate, "没有找到电子合同模板！");
        BTAssert.isTrue(StringUtils.equals("01", contractTemplate.getBusinStatus()), "电子合同模板状态错误！");

        if (StringUtils.equals(anAuditStatus, "01")) { // 审核通过
            contractTemplate.setTextAuditStatus("01"); // 电子合同文本审核通过
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "01", "审核标准合同文本模板，审核结果：审核通过");
        } else { // 审核不通过
            contractTemplate.setTextAuditStatus("02");// 电子合同文本审核驳回
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "03", "审核标准合同文本模板，审核结果：审核不通过");
        }

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        contractTemplate.setTextAuditRemark(anAuditRemark);
        contractTemplate.setTextAuditOperId(operator.getId());
        contractTemplate.setTextAuditOperName(operator.getName());
        contractTemplate.setTextAuditDate(BetterDateUtils.getNumDate());
        contractTemplate.setTextAuditTime(BetterDateUtils.getNumTime());
        contractTemplate.modify(operator);

        final int result = this.updateByPrimaryKeySelective(contractTemplate);

        BTAssert.isTrue(result == 1, "审核标准合同模板失败！");

        return contractTemplate;
    }

    /**
     * 查询所有未上传模板
     *
     * @param anCustNo
     * @return
     */
    public Page<ContractTemplate> queryUnusedText(final Long anCustNo, final int anFlag, final int anPageNum,
            final int anPageSize) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败！");

        final Map<String, Object> conditionMap = new HashMap<>();
        if (anCustNo != null) {
            conditionMap.put("custNo", anCustNo);
        }
        conditionMap.put("businStatus", "01");// 查询 电子合同文本, 包括进入电子合同模板阶段的数据
        conditionMap.put("textAuditStatus", "01"); // 查询标准合同文本 状态为 已审核
        return this.selectPropertyByPage(conditionMap, anPageNum, anPageSize, anFlag == 1);
    }

    /**
     * 查询电子合同模板
     *
     * @param anCustNo
     * @return
     */
    public Page<ContractTemplate> queryTemplate(final Map<String, Object> anParam, final int anFlag,
            final int anPageNum, final int anPageSize) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败！");

        if (StringUtils.isBlank((String) anParam.get("custNo"))) {
            anParam.remove("custNo");
        } else {
            anParam.put("custNo", Long.valueOf((String) anParam.get("custNo")));
        }
        if (StringUtils.isBlank((String) anParam.get("templateAuditStatus"))) {
            anParam.remove("templateAuditStatus");
        }
        anParam.put("businStatus", "02");// 查询 电子合同文本, 包括进入电子合同模板阶段的数据

        return this.selectPropertyByPage(anParam, anPageNum, anPageSize, anFlag == 1, "originApplyNo Desc");
    }

    /**
     * 查询电子合同模板
     *
     * @param anCustNo
     * @return
     */
    public Page<ContractTemplate> queryAuditTemplate(final Map<String, Object> anParam, final int anFlag,
            final int anPageNum, final int anPageSize) {

        if (StringUtils.isBlank((String) anParam.get("custNo"))) {
            anParam.remove("custNo");
        } else {
            anParam.put("custNo", Long.valueOf((String) anParam.get("custNo")));
        }
        if (StringUtils.isBlank((String) anParam.get("templateAuditStatus"))) {
            anParam.remove("templateAuditStatus");
        }
        anParam.put("operOrg", UserUtils.getOperatorInfo().getOperOrg()); // 查询自己机构的数据
        anParam.put("businStatus", "02");// 查询 电子合同文本, 包括进入电子合同模板阶段的数据

        return this.selectPropertyByPage(anParam, anPageNum, anPageSize, anFlag == 1, "originApplyNo Desc");
    }

    /**
     * 保存模板文件
     *
     * @param anId
     * @param anFileId
     * @return
     */
    public ContractTemplate saveUploadTemplate(final Long anTemplateId, final String anTemplateFileId,
            final String anCommon) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败!");

        BTAssert.notNull(StringUtils.isNotBlank(anTemplateFileId), "模板文件不允许为空！");

        final ContractTemplate contractTemplate = findContractTemplate(anTemplateId);

        BTAssert.isTrue(
                StringUtils.equals("02", contractTemplate.getBusinStatus())
                        || (StringUtils.equals("01", contractTemplate.getBusinStatus())
                                && StringUtils.equals("01", contractTemplate.getTextAuditStatus())),
                "标准合同模板状态不正确！");

        // 检查印章位置 contractTemplateStampPlaceService
        final List<ContractTemplateStampPlace> stampPlaces = contractTemplateStampPlaceService
                .queryStampPlace(anTemplateId);
        if (stampPlaces.isEmpty()) {
            throw new BytterException("印章位置未设置！");
        }
        if (!contractTemplate.getOriginSignerCount().equals(Long.valueOf(stampPlaces.size()))) {
            throw new BytterException("印章位置设置不正确！");
        }

        contractTemplate.setTemplate(custFileService.updateCustFileItemInfo(anTemplateFileId, null));
        contractTemplate.setBusinStatus("02"); // 进入 印章位置处理状态
        contractTemplate.setTemplateAuditStatus("00"); // 进入模板未审核状态
        contractTemplate.setMakeDate(BetterDateUtils.getNumDate());
        contractTemplate.setMakeTime(BetterDateUtils.getNumTime());
        contractTemplate.setCommon(anCommon);

        contractTemplate.modify(UserUtils.getOperatorInfo());

        final int result = this.updateByPrimaryKeySelective(contractTemplate);

        BTAssert.isTrue(result == 1, "上传模板失败！");

        if ("00".equals(contractTemplate.getTemplateAuditStatus())) {
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "04",
                    "上传电子合同模板" + "“" + custFileService.findOne(Long.valueOf(anTemplateFileId)).getFileName() + "”");
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "04", "提交申请");
        } else {
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "04", "重新发起申请");

        }
        return contractTemplate;
    }

    /**
     * 保存印章位置
     *
     * @param anId
     * @return
     */
    public ContractTemplate saveStampPlace(final Long anTemplateId, final List<Map<String, Object>> anStampPlaceList) {
        // 最终确定印章位置 进入下一步
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败!");

        final ContractTemplate contractTemplate = findContractTemplate(anTemplateId);

        BTAssert.notNull(contractTemplate, "标准合同模板未找到！");

        BTAssert.isTrue(
                StringUtils.equals("02", contractTemplate.getBusinStatus())
                        || (StringUtils.equals("01", contractTemplate.getBusinStatus())
                                && StringUtils.equals("01", contractTemplate.getTextAuditStatus())),
                "标准合同模板状态不正确！");

        BTAssert.isTrue(contractTemplate.getOriginSignerCount().equals(Long.valueOf(anStampPlaceList.size())),
                "印章数量不正确！");

        // anStampPlaceList 分解处理
        for (int i = 0; i < anStampPlaceList.size(); i++) {
            final Map<String, Object> stampPlace = anStampPlaceList.get(i);
            contractTemplateStampPlaceService.saveKeyWordStampPlace(anTemplateId, contractTemplate.getName(), i,
                    (String) stampPlace.get("key"), Long.valueOf((Integer) stampPlace.get("axisX")),
                    Long.valueOf((Integer) stampPlace.get("axisY")));
        }

        // 检查所有 印章位置是否已确认
        return contractTemplate;
    }

    /**
     * 审核合同模板
     *
     * @param anId
     * @param anAuditStatus
     * @param anAuditComment
     * @return
     */
    public ContractTemplate saveAuditTemplate(final Long anCustNo, final Long anTemplateId, final String anAuditStatus,
            final String anAuditRemark) {
        BTAssert.notNull(anCustNo, "公司编号不允许为空！");

        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anCustNo);
        BTAssert.notNull(custMechBase, "没有找到公司信息！");

        BTAssert.isTrue(StringUtils.equals(UserUtils.getOperatorInfo().getOperOrg(), custMechBase.getOperOrg()),
                "操作失败！");

        BTAssert.isTrue(StringUtils.isNotBlank(anAuditStatus) || StringUtils.equals(anAuditStatus, "01")
                || StringUtils.equals(anAuditStatus, "02"), "审核状态不正确！");

        final ContractTemplate contractTemplate = findContractTemplate(anTemplateId);

        BTAssert.notNull(contractTemplate, "没有找到电子合同模板！");
        BTAssert.isTrue(anCustNo.equals(contractTemplate.getCustNo()), "操作失败！");
        BTAssert.isTrue(StringUtils.equals("02", contractTemplate.getBusinStatus()), "电子合同模板状态错误！");

        if (StringUtils.equals(anAuditStatus, "01")) { // 审核通过
            contractTemplate.setTemplateAuditStatus("01"); // 电子合同文本审核通过
            // 记录 log
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "05", "审核电子合同模板，审核结果:审核通过");
        } else { // 审核不通过
            contractTemplate.setTemplateAuditStatus("02");// 电子合同文本审核驳回
            // 记录 log
            contractTemplateLogService.saveAddTemplateLog(anTemplateId, contractTemplate.getCustNo(),
                    contractTemplate.getCustName(), "06", "审核电子合同模板，审核结果:审核不通过");
        }

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        contractTemplate.setTemplateAuditOperId(operator.getId());
        contractTemplate.setTemplateAuditOperName(operator.getName());
        contractTemplate.setTemplateAuditDate(BetterDateUtils.getNumDate());
        contractTemplate.setTemplateAuditTime(BetterDateUtils.getNumTime());
        contractTemplate.modify(operator);
        contractTemplate.setTemplateAuditRemark(anAuditRemark);

        final int result = this.updateByPrimaryKeySelective(contractTemplate);

        BTAssert.isTrue(result == 1, "审核标准合同模板失败！");

        return contractTemplate;
    }

    /**
     * @param anTemplateId
     * @return
     */
    public List<ContractTemplateLog> queryTemplateLog(final Long anTemplateId) {
        final ContractTemplate contractTemplate = findContractTemplate(anTemplateId);

        BTAssert.notNull(contractTemplate, "没有找到电子合同模板！");

        BTAssert.isTrue(UserUtils.platformUser()
                || StringUtils.equals(contractTemplate.getOperOrg(), UserUtils.getOperatorInfo().getOperOrg()),
                "操作失败！");

        return contractTemplateLogService.queryTemplateLog(anTemplateId);
    }

}
