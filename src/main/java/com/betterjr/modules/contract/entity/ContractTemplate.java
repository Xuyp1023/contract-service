package com.betterjr.modules.contract.entity;

import java.util.List;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.betterjr.common.annotation.MetaData;
import com.betterjr.common.entity.BetterjrEntity;
import com.betterjr.common.mapper.CustDateJsonSerializer;
import com.betterjr.common.mapper.CustTimeJsonSerializer;
import com.betterjr.common.selectkey.SerialGenerator;
import com.betterjr.common.utils.BetterDateUtils;
import com.betterjr.modules.account.entity.CustOperatorInfo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Access(AccessType.FIELD)
@Entity
@Table(name = "t_sys_contract_template")
public class ContractTemplate implements BetterjrEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "ID", columnDefinition = "INTEGER")
    @MetaData(value = "编号", comments = "编号")
    private Long id;

    /**
     * 合同类型
     */
    @Column(name = "L_TYPE_ID", columnDefinition = "INTEGER")
    @MetaData(value = "合同类型", comments = "合同类型")
    private Long typeId;

    /**
     * 标准合同类型
     */
    @Column(name = "L_STANDARD_TYPE_ID", columnDefinition = "INTEGER")
    @MetaData(value = "标准合同类型", comments = "标准合同类型")
    private Long standardTypeId;

    /**
     * 模板名称
     */
    @Column(name = "C_NAME", columnDefinition = "VARCHAR")
    @MetaData(value = "模板名称", comments = "模板名称")
    private String name;

    /**
     * 申请编号
     */
    @Column(name = "C_ORIGIN_APPLY_NO", columnDefinition = "VARCHAR")
    @MetaData(value = "申请编号", comments = "申请编号")
    private String originApplyNo;

    /**
     * 原始模板
     */
    @Column(name = "L_ORIGIN_TEMPLATE", columnDefinition = "INTEGER")
    @MetaData(value = "原始模板", comments = "原始模板")
    private Long originTemplate;

    @Transient
    private Long originTemplateId;
    @Transient
    private String originTemplateName;

    /**
     * 原始示例文件
     */
    @Column(name = "L_ORIGIN_SIMPLE", columnDefinition = "INTEGER")
    @MetaData(value = "原始示例文件", comments = "原始示例文件")
    private Long originSimple;

    @Transient
    private Long originSimpleId;
    @Transient
    private String originSimpleName;

    /**
     * 原始文件日期
     */
    @JsonSerialize(using = CustDateJsonSerializer.class)
    @Column(name = "D_ORIGIN_DATE", columnDefinition = "CHAR")
    @MetaData(value = "原始文件日期", comments = "原始文件日期")
    private String originDate;

    /**
     * 原始文件时间
     */
    @JsonSerialize(using = CustTimeJsonSerializer.class)
    @Column(name = "T_ORIGIN_TIME", columnDefinition = "CHAR")
    @MetaData(value = "原始文件时间", comments = "原始文件时间")
    private String originTime;

    /**
     * 原始备注
     */
    @Column(name = "C_ORIGIN_COMMENT", columnDefinition = "VARCHAR")
    @MetaData(value = "原始备注", comments = "原始备注")
    private String originComment;

    /**
     * 签署人数量
     */
    @Column(name = "N_ORIGIN_SIGNER_COUNT", columnDefinition = "INTEGER")
    @MetaData(value = "签署人数量", comments = "签署人数量")
    private Long originSignerCount;

    /**
     * 合同编号模式
     */
    @Column(name = "C_ORIGIN_NO_PATTERN", columnDefinition = "VARCHAR")
    @MetaData(value = "合同编号模式", comments = "合同编号模式")
    private String originNoPattern;

    /**
     * 申请人编号
     */
    @Column(name = "L_ORIGIN_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "申请人编号", comments = "申请人编号")
    private Long originOperId;

    /**
     * 申请人姓名
     */
    @Column(name = "C_ORIGIN_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "申请人姓名", comments = "申请人姓名")
    private String originOperName;

    /**
     * 模板文件
     */
    @Column(name = "L_TEMPLATE", columnDefinition = "INTEGER")
    @MetaData(value = "模板文件", comments = "模板文件")
    private Long template;

    @Transient
    private Long templateId;
    @Transient
    private String templateName;
    @Transient
    private List<Map<String, Object>> stampPlaces;

    /**
     * 制作日期
     */
    @JsonSerialize(using = CustDateJsonSerializer.class)
    @Column(name = "D_MAKE_DATE", columnDefinition = "CHAR")
    @MetaData(value = "制作日期", comments = "制作日期")
    private String makeDate;

    /**
     * 制作时间
     */
    @JsonSerialize(using = CustTimeJsonSerializer.class)
    @Column(name = "T_MAKE_TIME", columnDefinition = "CHAR")
    @MetaData(value = "制作时间", comments = "制作时间")
    private String makeTime;

    /**
     * 制作人
     */
    @Column(name = "L_MAKE_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "制作人", comments = "制作人")
    private Long makeOperId;

    /**
     * 制作人名称
     */
    @Column(name = "C_MAKE_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "制作人名称", comments = "制作人名称")
    private String makeOperName;

    /**
     * 备注
     */
    @Column(name = "C_COMMON", columnDefinition = "VARCHAR")
    @MetaData(value = "备注", comments = "备注")
    private String common;

    /**
     * 所属公司
     */
    @Column(name = "L_CUSTNO", columnDefinition = "INTEGER")
    @MetaData(value = "所属公司", comments = "所属公司")
    private Long custNo;

    /**
     * 所属公司名称
     */
    @Column(name = "C_CUSTNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "所属公司名称", comments = "所属公司名称")
    private String custName;

    /**
     * 所属机构
     */
    @Column(name = "C_OPERORG", columnDefinition = "VARCHAR")
    @MetaData(value = "所属机构", comments = "所属机构")
    private String operOrg;

    /**
     * 审核意见
     */
    @Column(name = "C_TEXT_AUDIT_REMARK", columnDefinition = "VARCHAR")
    @MetaData(value = "审核意见", comments = "审核意见")
    private String textAuditRemark;

    /**
     * 审核状态
     */
    @Column(name = "C_TEXT_AUDIT_STATUS", columnDefinition = "CHAR")
    @MetaData(value = "审核状态", comments = "审核状态")
    private String textAuditStatus;

    /**
     * 审核操作员
     */
    @Column(name = "L_TEXT_AUDIT_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "", comments = "")
    private Long textAuditOperId;

    /**
     * 审核操作员
     */
    @Column(name = "C_TEXT_AUDIT_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String textAuditOperName;

    /**
     * 审核日期
     */
    @JsonSerialize(using = CustDateJsonSerializer.class)
    @Column(name = "D_TEXT_AUDIT_DATE", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String textAuditDate;

    /**
     * 审核时间
     */
    @JsonSerialize(using = CustTimeJsonSerializer.class)
    @Column(name = "T_TEXT_AUDIT_TIME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String textAuditTime;

    /**
     * 审核操作员
     */
    @Column(name = "L_TEMPLATE_AUDIT_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "", comments = "")
    private Long templateAuditOperId;

    /**
     * 审核操作员
     */
    @Column(name = "C_TEMPLATE_AUDIT_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String templateAuditOperName;

    /**
     * 审核日期
     */
    @JsonSerialize(using = CustDateJsonSerializer.class)
    @Column(name = "D_TEMPLATE_AUDIT_DATE", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String templateAuditDate;

    /**
     * 审核时间
     */
    @JsonSerialize(using = CustTimeJsonSerializer.class)
    @Column(name = "T_TEMPLATE_AUDIT_TIME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String templateAuditTime;

    /**
     * 审核意见
     */
    @Column(name = "C_TEMPLATE_AUDIT_REMARK", columnDefinition = "VARCHAR")
    @MetaData(value = "审核意见", comments = "审核意见")
    private String templateAuditRemark;

    /**
     * 审核状态
     */
    @Column(name = "C_TEMPLATE_AUDIT_STATUS", columnDefinition = "CHAR")
    @MetaData(value = "审核状态", comments = "审核状态")
    private String templateAuditStatus;
    /**
     * 业务状态
     */
    @Column(name = "C_BUSIN_STATUS", columnDefinition = "CHAR")
    @MetaData(value = "业务状态", comments = "业务状态")
    private String businStatus;

    /**
     * 文档状态
     */
    @Column(name = "C_DOC_STATUS", columnDefinition = "CHAR")
    @MetaData(value = "文档状态", comments = "文档状态")
    private String docStatus;

    @Column(name = "L_REG_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "", comments = "")
    private Long regOperId;

    @Column(name = "C_REG_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String regOperName;

    @JsonSerialize(using = CustDateJsonSerializer.class)
    @Column(name = "D_REG_DATE", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String regDate;

    @Column(name = "T_REG_TIME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String regTime;

    @Column(name = "L_MODI_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "", comments = "")
    private Long modiOperId;

    @Column(name = "C_MODI_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String modiOperName;

    @JsonSerialize(using = CustDateJsonSerializer.class)
    @Column(name = "D_MODI_DATE", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String modiDate;

    @Column(name = "T_MODI_TIME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String modiTime;

    @Column(name = "C_TYPEID_NAME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String typeIdName;

    private static final long serialVersionUID = 1492408575302L;

    public List<Map<String, Object>> getStampPlaces() {
        return stampPlaces;
    }

    public void setStampPlaces(final List<Map<String, Object>> anStampPlaces) {
        stampPlaces = anStampPlaces;
    }

    public Long getOriginTemplateId() {
        return originTemplateId;
    }

    public void setOriginTemplateId(final Long anOriginTemplateId) {
        originTemplateId = anOriginTemplateId;
    }

    public Long getOriginSimpleId() {
        return originSimpleId;
    }

    public void setOriginSimpleId(final Long anOriginSimpleId) {
        originSimpleId = anOriginSimpleId;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(final Long anTemplateId) {
        templateId = anTemplateId;
    }

    public String getOriginTemplateName() {
        return originTemplateName;
    }

    public void setOriginTemplateName(final String anOriginTemplateName) {
        originTemplateName = anOriginTemplateName;
    }

    public String getOriginSimpleName() {
        return originSimpleName;
    }

    public void setOriginSimpleName(final String anOriginSimpleName) {
        originSimpleName = anOriginSimpleName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(final String anTemplateName) {
        templateName = anTemplateName;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(final Long typeId) {
        this.typeId = typeId;
    }

    public Long getStandardTypeId() {
        return standardTypeId;
    }

    public void setStandardTypeId(final Long standardTypeId) {
        this.standardTypeId = standardTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOriginApplyNo() {
        return originApplyNo;
    }

    public void setOriginApplyNo(final String originApplyNo) {
        this.originApplyNo = originApplyNo;
    }

    public Long getOriginTemplate() {
        return originTemplate;
    }

    public void setOriginTemplate(final Long originTemplate) {
        this.originTemplate = originTemplate;
    }

    public Long getOriginSimple() {
        return originSimple;
    }

    public void setOriginSimple(final Long originSimple) {
        this.originSimple = originSimple;
    }

    public String getOriginDate() {
        return originDate;
    }

    public void setOriginDate(final String originDate) {
        this.originDate = originDate;
    }

    public String getOriginTime() {
        return originTime;
    }

    public void setOriginTime(final String originTime) {
        this.originTime = originTime;
    }

    public String getOriginComment() {
        return originComment;
    }

    public void setOriginComment(final String originComment) {
        this.originComment = originComment;
    }

    public Long getOriginSignerCount() {
        return originSignerCount;
    }

    public void setOriginSignerCount(final Long originSignerCount) {
        this.originSignerCount = originSignerCount;
    }

    public String getOriginNoPattern() {
        return originNoPattern;
    }

    public void setOriginNoPattern(final String originNoPattern) {
        this.originNoPattern = originNoPattern;
    }

    public Long getOriginOperId() {
        return originOperId;
    }

    public void setOriginOperId(final Long originOperId) {
        this.originOperId = originOperId;
    }

    public String getOriginOperName() {
        return originOperName;
    }

    public void setOriginOperName(final String originOperName) {
        this.originOperName = originOperName == null ? null : originOperName.trim();
    }

    public Long getTemplate() {
        return template;
    }

    public void setTemplate(final Long template) {
        this.template = template;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(final String makeDate) {
        this.makeDate = makeDate;
    }

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(final String makeTime) {
        this.makeTime = makeTime;
    }

    public Long getMakeOperId() {
        return makeOperId;
    }

    public void setMakeOperId(final Long makeOperId) {
        this.makeOperId = makeOperId;
    }

    public String getMakeOperName() {
        return makeOperName;
    }

    public void setMakeOperName(final String makeOperName) {
        this.makeOperName = makeOperName;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(final String common) {
        this.common = common == null ? null : common.trim();
    }

    public Long getCustNo() {
        return custNo;
    }

    public void setCustNo(final Long custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(final String custName) {
        this.custName = custName;
    }

    public String getOperOrg() {
        return operOrg;
    }

    public void setOperOrg(final String operOrg) {
        this.operOrg = operOrg;
    }

    public String getTextAuditRemark() {
        return textAuditRemark;
    }

    public void setTextAuditRemark(final String textAuditRemark) {
        this.textAuditRemark = textAuditRemark;
    }

    public String getTextAuditStatus() {
        return textAuditStatus;
    }

    public void setTextAuditStatus(final String textAuditStatus) {
        this.textAuditStatus = textAuditStatus;
    }

    public String getTemplateAuditRemark() {
        return templateAuditRemark;
    }

    public void setTemplateAuditRemark(final String templateAuditRemark) {
        this.templateAuditRemark = templateAuditRemark;
    }

    public String getTemplateAuditStatus() {
        return templateAuditStatus;
    }

    public void setTemplateAuditStatus(final String templateAuditStatus) {
        this.templateAuditStatus = templateAuditStatus;
    }

    public Long getTextAuditOperId() {
        return textAuditOperId;
    }

    public void setTextAuditOperId(final Long anTextAuditOperId) {
        textAuditOperId = anTextAuditOperId;
    }

    public String getTextAuditOperName() {
        return textAuditOperName;
    }

    public void setTextAuditOperName(final String anTextAuditOperName) {
        textAuditOperName = anTextAuditOperName;
    }

    public String getTextAuditDate() {
        return textAuditDate;
    }

    public void setTextAuditDate(final String anTextAuditDate) {
        textAuditDate = anTextAuditDate;
    }

    public String getTextAuditTime() {
        return textAuditTime;
    }

    public void setTextAuditTime(final String anTextAuditTime) {
        textAuditTime = anTextAuditTime;
    }

    public Long getTemplateAuditOperId() {
        return templateAuditOperId;
    }

    public void setTemplateAuditOperId(final Long anTemplateAuditOperId) {
        templateAuditOperId = anTemplateAuditOperId;
    }

    public String getTemplateAuditOperName() {
        return templateAuditOperName;
    }

    public void setTemplateAuditOperName(final String anTemplateAuditOperName) {
        templateAuditOperName = anTemplateAuditOperName;
    }

    public String getTemplateAuditDate() {
        return templateAuditDate;
    }

    public void setTemplateAuditDate(final String anTemplateAuditDate) {
        templateAuditDate = anTemplateAuditDate;
    }

    public String getTemplateAuditTime() {
        return templateAuditTime;
    }

    public void setTemplateAuditTime(final String anTemplateAuditTime) {
        templateAuditTime = anTemplateAuditTime;
    }

    public String getBusinStatus() {
        return businStatus;
    }

    public void setBusinStatus(final String businStatus) {
        this.businStatus = businStatus;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(final String docStatus) {
        this.docStatus = docStatus;
    }

    public Long getRegOperId() {
        return regOperId;
    }

    public void setRegOperId(final Long regOperId) {
        this.regOperId = regOperId;
    }

    public String getRegOperName() {
        return regOperName;
    }

    public void setRegOperName(final String regOperName) {
        this.regOperName = regOperName == null ? null : regOperName.trim();
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(final String regDate) {
        this.regDate = regDate == null ? null : regDate.trim();
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(final String regTime) {
        this.regTime = regTime == null ? null : regTime.trim();
    }

    public Long getModiOperId() {
        return modiOperId;
    }

    public void setModiOperId(final Long modiOperId) {
        this.modiOperId = modiOperId;
    }

    public String getModiOperName() {
        return modiOperName;
    }

    public void setModiOperName(final String modiOperName) {
        this.modiOperName = modiOperName == null ? null : modiOperName.trim();
    }

    public String getModiDate() {
        return modiDate;
    }

    public void setModiDate(final String modiDate) {
        this.modiDate = modiDate == null ? null : modiDate.trim();
    }

    public String getModiTime() {
        return modiTime;
    }

    public void setModiTime(final String modiTime) {
        this.modiTime = modiTime == null ? null : modiTime.trim();
    }

    public String getTypeIdName() {
        return typeIdName;
    }

    public void setTypeIdName(String typeIdName) {
        this.typeIdName = typeIdName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", typeId=").append(typeId);
        sb.append(", standardTypeId=").append(standardTypeId);
        sb.append(", name=").append(name);
        sb.append(", originApplyNo=").append(originApplyNo);
        sb.append(", originTemplate=").append(originTemplate);
        sb.append(", originSimple=").append(originSimple);
        sb.append(", originDate=").append(originDate);
        sb.append(", originTime=").append(originTime);
        sb.append(", originComment=").append(originComment);
        sb.append(", originSignerCount=").append(originSignerCount);
        sb.append(", originNoPattern=").append(originNoPattern);
        sb.append(", originOperId=").append(originOperId);
        sb.append(", originOperName=").append(originOperName);
        sb.append(", template=").append(template);
        sb.append(", makeDate=").append(makeDate);
        sb.append(", makeTime=").append(makeTime);
        sb.append(", makeOperId=").append(makeOperId);
        sb.append(", makeOperName=").append(makeOperName);
        sb.append(", common=").append(common);
        sb.append(", custNo=").append(custNo);
        sb.append(", custName=").append(custName);
        sb.append(", operOrg=").append(operOrg);
        sb.append(", textAuditRemark=").append(textAuditRemark);
        sb.append(", textAuditStatus=").append(textAuditStatus);
        sb.append(", textAuditOperId=").append(textAuditOperId);
        sb.append(", textAuditOperName=").append(textAuditOperName);
        sb.append(", textAuditDate=").append(textAuditDate);
        sb.append(", textAuditTime=").append(textAuditTime);
        sb.append(", templateAuditRemark=").append(templateAuditRemark);
        sb.append(", templateAuditStatus=").append(templateAuditStatus);
        sb.append(", templateAuditOperId=").append(templateAuditOperId);
        sb.append(", templateAuditOperName=").append(templateAuditOperName);
        sb.append(", templateAuditDate=").append(templateAuditDate);
        sb.append(", templateAuditTime=").append(templateAuditTime);
        sb.append(", businStatus=").append(businStatus);
        sb.append(", docStatus=").append(docStatus);
        sb.append(", regOperId=").append(regOperId);
        sb.append(", regOperName=").append(regOperName);
        sb.append(", regDate=").append(regDate);
        sb.append(", regTime=").append(regTime);
        sb.append(", modiOperId=").append(modiOperId);
        sb.append(", modiOperName=").append(modiOperName);
        sb.append(", modiDate=").append(modiDate);
        sb.append(", modiTime=").append(modiTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(final Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        final ContractTemplate other = (ContractTemplate) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
                && (this.getStandardTypeId() == null ? other.getStandardTypeId() == null
                        : this.getStandardTypeId().equals(other.getStandardTypeId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getOriginApplyNo() == null ? other.getOriginApplyNo() == null
                        : this.getOriginApplyNo().equals(other.getOriginApplyNo()))
                && (this.getOriginTemplate() == null ? other.getOriginTemplate() == null
                        : this.getOriginTemplate().equals(other.getOriginTemplate()))
                && (this.getOriginSimple() == null ? other.getOriginSimple() == null
                        : this.getOriginSimple().equals(other.getOriginSimple()))
                && (this.getOriginDate() == null ? other.getOriginDate() == null
                        : this.getOriginDate().equals(other.getOriginDate()))
                && (this.getOriginTime() == null ? other.getOriginTime() == null
                        : this.getOriginTime().equals(other.getOriginTime()))
                && (this.getOriginComment() == null ? other.getOriginComment() == null
                        : this.getOriginComment().equals(other.getOriginComment()))
                && (this.getOriginSignerCount() == null ? other.getOriginSignerCount() == null
                        : this.getOriginSignerCount().equals(other.getOriginSignerCount()))
                && (this.getOriginNoPattern() == null ? other.getOriginNoPattern() == null
                        : this.getOriginNoPattern().equals(other.getOriginNoPattern()))
                && (this.getOriginOperId() == null ? other.getOriginOperId() == null
                        : this.getOriginOperId().equals(other.getOriginOperId()))
                && (this.getOriginOperName() == null ? other.getOriginOperName() == null
                        : this.getOriginOperName().equals(other.getOriginOperName()))
                && (this.getTemplate() == null ? other.getTemplate() == null
                        : this.getTemplate().equals(other.getTemplate()))
                && (this.getMakeDate() == null ? other.getMakeDate() == null
                        : this.getMakeDate().equals(other.getMakeDate()))
                && (this.getMakeTime() == null ? other.getMakeTime() == null
                        : this.getMakeTime().equals(other.getMakeTime()))
                && (this.getMakeOperId() == null ? other.getMakeOperId() == null
                        : this.getMakeOperId().equals(other.getMakeOperId()))
                && (this.getMakeOperName() == null ? other.getMakeOperName() == null
                        : this.getMakeOperName().equals(other.getMakeOperName()))
                && (this.getCommon() == null ? other.getCommon() == null : this.getCommon().equals(other.getCommon()))
                && (this.getCustNo() == null ? other.getCustNo() == null : this.getCustNo().equals(other.getCustNo()))
                && (this.getCustName() == null ? other.getCustName() == null
                        : this.getCustName().equals(other.getCustName()))
                && (this.getOperOrg() == null ? other.getOperOrg() == null
                        : this.getOperOrg().equals(other.getOperOrg()))
                && (this.getTextAuditRemark() == null ? other.getTextAuditRemark() == null
                        : this.getTextAuditRemark().equals(other.getTextAuditRemark()))
                && (this.getTextAuditStatus() == null ? other.getTextAuditStatus() == null
                        : this.getTextAuditStatus().equals(other.getTextAuditStatus()))
                && (this.getTextAuditOperId() == null ? other.getTextAuditOperId() == null
                        : this.getTextAuditOperId().equals(other.getTextAuditOperId()))
                && (this.getTextAuditOperName() == null ? other.getTextAuditOperName() == null
                        : this.getTextAuditOperName().equals(other.getTextAuditOperName()))
                && (this.getTextAuditDate() == null ? other.getTextAuditDate() == null
                        : this.getTextAuditDate().equals(other.getTextAuditDate()))
                && (this.getTextAuditTime() == null ? other.getTextAuditTime() == null
                        : this.getTextAuditTime().equals(other.getTextAuditTime()))
                && (this.getTemplateAuditRemark() == null ? other.getTemplateAuditRemark() == null
                        : this.getTemplateAuditRemark().equals(other.getTemplateAuditRemark()))
                && (this.getTemplateAuditStatus() == null ? other.getTemplateAuditStatus() == null
                        : this.getTemplateAuditStatus().equals(other.getTemplateAuditStatus()))
                && (this.getTemplateAuditOperId() == null ? other.getTemplateAuditOperId() == null
                        : this.getTemplateAuditOperId().equals(other.getTemplateAuditOperId()))
                && (this.getTemplateAuditOperName() == null ? other.getTemplateAuditOperName() == null
                        : this.getTemplateAuditOperName().equals(other.getTemplateAuditOperName()))
                && (this.getTemplateAuditDate() == null ? other.getTemplateAuditDate() == null
                        : this.getTemplateAuditDate().equals(other.getTemplateAuditDate()))
                && (this.getTemplateAuditTime() == null ? other.getTemplateAuditTime() == null
                        : this.getTemplateAuditTime().equals(other.getTemplateAuditTime()))
                && (this.getBusinStatus() == null ? other.getBusinStatus() == null
                        : this.getBusinStatus().equals(other.getBusinStatus()))
                && (this.getDocStatus() == null ? other.getDocStatus() == null
                        : this.getDocStatus().equals(other.getDocStatus()))
                && (this.getRegOperId() == null ? other.getRegOperId() == null
                        : this.getRegOperId().equals(other.getRegOperId()))
                && (this.getRegOperName() == null ? other.getRegOperName() == null
                        : this.getRegOperName().equals(other.getRegOperName()))
                && (this.getRegDate() == null ? other.getRegDate() == null
                        : this.getRegDate().equals(other.getRegDate()))
                && (this.getRegTime() == null ? other.getRegTime() == null
                        : this.getRegTime().equals(other.getRegTime()))
                && (this.getModiOperId() == null ? other.getModiOperId() == null
                        : this.getModiOperId().equals(other.getModiOperId()))
                && (this.getModiOperName() == null ? other.getModiOperName() == null
                        : this.getModiOperName().equals(other.getModiOperName()))
                && (this.getModiDate() == null ? other.getModiDate() == null
                        : this.getModiDate().equals(other.getModiDate()))
                && (this.getModiTime() == null ? other.getModiTime() == null
                        : this.getModiTime().equals(other.getModiTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getStandardTypeId() == null) ? 0 : getStandardTypeId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getOriginApplyNo() == null) ? 0 : getOriginApplyNo().hashCode());
        result = prime * result + ((getOriginTemplate() == null) ? 0 : getOriginTemplate().hashCode());
        result = prime * result + ((getOriginSimple() == null) ? 0 : getOriginSimple().hashCode());
        result = prime * result + ((getOriginDate() == null) ? 0 : getOriginDate().hashCode());
        result = prime * result + ((getOriginTime() == null) ? 0 : getOriginTime().hashCode());
        result = prime * result + ((getOriginComment() == null) ? 0 : getOriginComment().hashCode());
        result = prime * result + ((getOriginSignerCount() == null) ? 0 : getOriginSignerCount().hashCode());
        result = prime * result + ((getOriginNoPattern() == null) ? 0 : getOriginNoPattern().hashCode());
        result = prime * result + ((getOriginOperId() == null) ? 0 : getOriginOperId().hashCode());
        result = prime * result + ((getOriginOperName() == null) ? 0 : getOriginOperName().hashCode());
        result = prime * result + ((getTemplate() == null) ? 0 : getTemplate().hashCode());
        result = prime * result + ((getMakeDate() == null) ? 0 : getMakeDate().hashCode());
        result = prime * result + ((getMakeTime() == null) ? 0 : getMakeTime().hashCode());
        result = prime * result + ((getMakeOperId() == null) ? 0 : getMakeOperId().hashCode());
        result = prime * result + ((getMakeOperName() == null) ? 0 : getMakeOperName().hashCode());
        result = prime * result + ((getCommon() == null) ? 0 : getCommon().hashCode());
        result = prime * result + ((getCustNo() == null) ? 0 : getCustNo().hashCode());
        result = prime * result + ((getCustName() == null) ? 0 : getCustName().hashCode());
        result = prime * result + ((getOperOrg() == null) ? 0 : getOperOrg().hashCode());
        result = prime * result + ((getTextAuditRemark() == null) ? 0 : getTextAuditRemark().hashCode());
        result = prime * result + ((getTextAuditStatus() == null) ? 0 : getTextAuditStatus().hashCode());
        result = prime * result + ((getTextAuditOperId() == null) ? 0 : getTextAuditOperId().hashCode());
        result = prime * result + ((getTextAuditOperName() == null) ? 0 : getTextAuditOperName().hashCode());
        result = prime * result + ((getTextAuditDate() == null) ? 0 : getTextAuditDate().hashCode());
        result = prime * result + ((getTextAuditTime() == null) ? 0 : getTextAuditTime().hashCode());
        result = prime * result + ((getTemplateAuditRemark() == null) ? 0 : getTemplateAuditRemark().hashCode());
        result = prime * result + ((getTemplateAuditStatus() == null) ? 0 : getTemplateAuditStatus().hashCode());
        result = prime * result + ((getTemplateAuditOperId() == null) ? 0 : getTemplateAuditOperId().hashCode());
        result = prime * result + ((getTemplateAuditOperName() == null) ? 0 : getTemplateAuditOperName().hashCode());
        result = prime * result + ((getTemplateAuditDate() == null) ? 0 : getTemplateAuditDate().hashCode());
        result = prime * result + ((getTemplateAuditTime() == null) ? 0 : getTemplateAuditTime().hashCode());
        result = prime * result + ((getBusinStatus() == null) ? 0 : getBusinStatus().hashCode());
        result = prime * result + ((getDocStatus() == null) ? 0 : getDocStatus().hashCode());
        result = prime * result + ((getRegOperId() == null) ? 0 : getRegOperId().hashCode());
        result = prime * result + ((getRegOperName() == null) ? 0 : getRegOperName().hashCode());
        result = prime * result + ((getRegDate() == null) ? 0 : getRegDate().hashCode());
        result = prime * result + ((getRegTime() == null) ? 0 : getRegTime().hashCode());
        result = prime * result + ((getModiOperId() == null) ? 0 : getModiOperId().hashCode());
        result = prime * result + ((getModiOperName() == null) ? 0 : getModiOperName().hashCode());
        result = prime * result + ((getModiDate() == null) ? 0 : getModiDate().hashCode());
        result = prime * result + ((getModiTime() == null) ? 0 : getModiTime().hashCode());
        return result;
    }

    /**
     *
     */
    public void init(final CustOperatorInfo anOperator) {
        this.setId(SerialGenerator.getLongValue("ContractTemplate.id"));

        this.setRegDate(BetterDateUtils.getNumDate());
        this.setRegTime(BetterDateUtils.getNumTime());

        this.setModiDate(BetterDateUtils.getNumDate());
        this.setModiTime(BetterDateUtils.getNumTime());

        if (anOperator != null) {
            this.setRegOperId(anOperator.getId());
            this.setRegOperName(anOperator.getName());

            this.setModiOperId(anOperator.getId());
            this.setModiOperName(anOperator.getName());
        }

    }

    /**
     *
     */
    public void modify(final CustOperatorInfo anOperator) {
        this.setModiDate(BetterDateUtils.getNumDate());
        this.setModiTime(BetterDateUtils.getNumTime());
        if (anOperator != null) {
            this.setModiOperId(anOperator.getId());
            this.setModiOperName(anOperator.getName());
        }
    }
}