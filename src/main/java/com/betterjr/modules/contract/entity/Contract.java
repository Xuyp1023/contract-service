package com.betterjr.modules.contract.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.betterjr.common.annotation.MetaData;
import com.betterjr.common.entity.BetterjrEntity;

@Access(AccessType.FIELD)
@Entity
@Table(name = "t_sys_contract")
public class Contract implements BetterjrEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "ID",  columnDefinition="INTEGER" )
    @MetaData( value="编号", comments = "编号")
    private Long id;

    /**
     * 电子合同编号
     */
    @Column(name = "C_NO",  columnDefinition="VARCHAR" )
    @MetaData( value="电子合同编号", comments = "电子合同编号")
    private String no;

    /**
     * 电子合同名称
     */
    @Column(name = "C_NAME",  columnDefinition="VARCHAR" )
    @MetaData( value="电子合同名称", comments = "电子合同名称")
    private String name;

    /**
     * 类型
     */
    @Column(name = "C_TYPE",  columnDefinition="VARCHAR" )
    @MetaData( value="类型", comments = "类型")
    private String type;

    /**
     * 合同类型
     */
    @Column(name = "L_TYPE_ID",  columnDefinition="INTEGER" )
    @MetaData( value="合同类型", comments = "合同类型")
    private Long typeId;

    /**
     * 标准合同类型
     */
    @Column(name = "L_STANDARD_TYPE_ID",  columnDefinition="INTEGER" )
    @MetaData( value="标准合同类型", comments = "标准合同类型")
    private Long standardTypeId;

    /**
     * 数据源编号
     */
    @Column(name = "L_DATA_SOURCE_ID",  columnDefinition="INTEGER" )
    @MetaData( value="数据源编号", comments = "数据源编号")
    private Long dataSourceId;

    /**
     * 合同原始html
     */
    @Column(name = "L_ORIGIN_HTML",  columnDefinition="INTEGER" )
    @MetaData( value="合同原始html", comments = "合同原始html")
    private Long originHtml;

    /**
     * 合同原始pdf
     */
    @Column(name = "L_ORIGIN_PDF",  columnDefinition="INTEGER" )
    @MetaData( value="合同原始pdf", comments = "合同原始pdf")
    private Long originPdf;

    /**
     * 签署开始日期
     */
    @Column(name = "D_SIGN_START_DATE",  columnDefinition="VARCHAR" )
    @MetaData( value="签署开始日期", comments = "签署开始日期")
    private String signStartDate;

    /**
     * 签署开始时间
     */
    @Column(name = "T_SIGN_START_TIME",  columnDefinition="VARCHAR" )
    @MetaData( value="签署开始时间", comments = "签署开始时间")
    private String signStartTime;

    /**
     * 签署结束日期
     */
    @Column(name = "D_SIGN_END_DATE",  columnDefinition="VARCHAR" )
    @MetaData( value="签署结束日期", comments = "签署结束日期")
    private String signEndDate;

    /**
     * 签署结束时间
     */
    @Column(name = "T_SIGN_END_TIME",  columnDefinition="VARCHAR" )
    @MetaData( value="签署结束时间", comments = "签署结束时间")
    private String signEndTime;

    /**
     * 签署后pdf
     */
    @Column(name = "L_SIGN_PDF",  columnDefinition="INTEGER" )
    @MetaData( value="签署后pdf", comments = "签署后pdf")
    private Long signPdf;

    /**
     * 业务状态
     */
    @Column(name = "C_BUSIN_STATUS",  columnDefinition="CHAR" )
    @MetaData( value="业务状态", comments = "业务状态")
    private String businStatus;

    /**
     * 文档状态
     */
    @Column(name = "C_DOC_STATUS",  columnDefinition="CHAR" )
    @MetaData( value="文档状态", comments = "文档状态")
    private String docStatus;

    /**
     * 公司
     */
    @Column(name = "L_CUSTNO",  columnDefinition="INTEGER" )
    @MetaData( value="公司", comments = "公司")
    private Long custNo;

    /**
     * 公司名称
     */
    @Column(name = "C_CUSTNAME",  columnDefinition="VARCHAR" )
    @MetaData( value="公司名称", comments = "公司名称")
    private String custName;

    /**
     * 机构
     */
    @Column(name = "C_OPERORG",  columnDefinition="VARCHAR" )
    @MetaData( value="机构", comments = "机构")
    private String operOrg;

    @Column(name = "D_MODI_DATE",  columnDefinition="VARCHAR" )
    @MetaData( value="", comments = "")
    private String modiDate;

    @Column(name = "T_MODI_TIME",  columnDefinition="VARCHAR" )
    @MetaData( value="", comments = "")
    private String modiTime;

    @Column(name = "C_MODI_OPERNAME",  columnDefinition="VARCHAR" )
    @MetaData( value="", comments = "")
    private String modiOperName;

    @Column(name = "L_MODI_OPERID",  columnDefinition="INTEGER" )
    @MetaData( value="", comments = "")
    private Long modiOperId;

    @Column(name = "T_REG_TIME",  columnDefinition="VARCHAR" )
    @MetaData( value="", comments = "")
    private String regTime;

    @Column(name = "D_REG_DATE",  columnDefinition="VARCHAR" )
    @MetaData( value="", comments = "")
    private String regDate;

    @Column(name = "C_REG_OPERNAME",  columnDefinition="VARCHAR" )
    @MetaData( value="", comments = "")
    private String regOperName;

    @Column(name = "L_REG_OPERID",  columnDefinition="INTEGER" )
    @MetaData( value="", comments = "")
    private Long regOperId;

    private static final long serialVersionUID = 1492408575309L;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(final String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
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

    public Long getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(final Long dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Long getOriginHtml() {
        return originHtml;
    }

    public void setOriginHtml(final Long originHtml) {
        this.originHtml = originHtml;
    }

    public Long getOriginPdf() {
        return originPdf;
    }

    public void setOriginPdf(final Long originPdf) {
        this.originPdf = originPdf;
    }

    public String getSignStartDate() {
        return signStartDate;
    }

    public void setSignStartDate(final String signStartDate) {
        this.signStartDate = signStartDate == null ? null : signStartDate.trim();
    }

    public String getSignStartTime() {
        return signStartTime;
    }

    public void setSignStartTime(final String signStartTime) {
        this.signStartTime = signStartTime == null ? null : signStartTime.trim();
    }

    public String getSignEndDate() {
        return signEndDate;
    }

    public void setSignEndDate(final String signEndDate) {
        this.signEndDate = signEndDate == null ? null : signEndDate.trim();
    }

    public String getSignEndTime() {
        return signEndTime;
    }

    public void setSignEndTime(final String signEndTime) {
        this.signEndTime = signEndTime == null ? null : signEndTime.trim();
    }

    public Long getSignPdf() {
        return signPdf;
    }

    public void setSignPdf(final Long signPdf) {
        this.signPdf = signPdf;
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

    public String getModiOperName() {
        return modiOperName;
    }

    public void setModiOperName(final String modiOperName) {
        this.modiOperName = modiOperName == null ? null : modiOperName.trim();
    }

    public Long getModiOperId() {
        return modiOperId;
    }

    public void setModiOperId(final Long modiOperId) {
        this.modiOperId = modiOperId;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(final String regTime) {
        this.regTime = regTime == null ? null : regTime.trim();
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(final String regDate) {
        this.regDate = regDate == null ? null : regDate.trim();
    }

    public String getRegOperName() {
        return regOperName;
    }

    public void setRegOperName(final String regOperName) {
        this.regOperName = regOperName == null ? null : regOperName.trim();
    }

    public Long getRegOperId() {
        return regOperId;
    }

    public void setRegOperId(final Long regOperId) {
        this.regOperId = regOperId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", no=").append(no);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", typeId=").append(typeId);
        sb.append(", standardTypeId=").append(standardTypeId);
        sb.append(", dataSourceId=").append(dataSourceId);
        sb.append(", originHtml=").append(originHtml);
        sb.append(", originPdf=").append(originPdf);
        sb.append(", signStartDate=").append(signStartDate);
        sb.append(", signStartTime=").append(signStartTime);
        sb.append(", signEndDate=").append(signEndDate);
        sb.append(", signEndTime=").append(signEndTime);
        sb.append(", signPdf=").append(signPdf);
        sb.append(", businStatus=").append(businStatus);
        sb.append(", docStatus=").append(docStatus);
        sb.append(", custNo=").append(custNo);
        sb.append(", custName=").append(custName);
        sb.append(", operOrg=").append(operOrg);
        sb.append(", modiDate=").append(modiDate);
        sb.append(", modiTime=").append(modiTime);
        sb.append(", modiOperName=").append(modiOperName);
        sb.append(", modiOperId=").append(modiOperId);
        sb.append(", regTime=").append(regTime);
        sb.append(", regDate=").append(regDate);
        sb.append(", regOperName=").append(regOperName);
        sb.append(", regOperId=").append(regOperId);
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
        final Contract other = (Contract) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getNo() == null ? other.getNo() == null : this.getNo().equals(other.getNo()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
                && (this.getStandardTypeId() == null ? other.getStandardTypeId() == null : this.getStandardTypeId().equals(other.getStandardTypeId()))
                && (this.getDataSourceId() == null ? other.getDataSourceId() == null : this.getDataSourceId().equals(other.getDataSourceId()))
                && (this.getOriginHtml() == null ? other.getOriginHtml() == null : this.getOriginHtml().equals(other.getOriginHtml()))
                && (this.getOriginPdf() == null ? other.getOriginPdf() == null : this.getOriginPdf().equals(other.getOriginPdf()))
                && (this.getSignStartDate() == null ? other.getSignStartDate() == null : this.getSignStartDate().equals(other.getSignStartDate()))
                && (this.getSignStartTime() == null ? other.getSignStartTime() == null : this.getSignStartTime().equals(other.getSignStartTime()))
                && (this.getSignEndDate() == null ? other.getSignEndDate() == null : this.getSignEndDate().equals(other.getSignEndDate()))
                && (this.getSignEndTime() == null ? other.getSignEndTime() == null : this.getSignEndTime().equals(other.getSignEndTime()))
                && (this.getSignPdf() == null ? other.getSignPdf() == null : this.getSignPdf().equals(other.getSignPdf()))
                && (this.getBusinStatus() == null ? other.getBusinStatus() == null : this.getBusinStatus().equals(other.getBusinStatus()))
                && (this.getDocStatus() == null ? other.getDocStatus() == null : this.getDocStatus().equals(other.getDocStatus()))
                && (this.getCustNo() == null ? other.getCustNo() == null : this.getCustNo().equals(other.getCustNo()))
                && (this.getCustName() == null ? other.getCustName() == null : this.getCustName().equals(other.getCustName()))
                && (this.getOperOrg() == null ? other.getOperOrg() == null : this.getOperOrg().equals(other.getOperOrg()))
                && (this.getModiDate() == null ? other.getModiDate() == null : this.getModiDate().equals(other.getModiDate()))
                && (this.getModiTime() == null ? other.getModiTime() == null : this.getModiTime().equals(other.getModiTime()))
                && (this.getModiOperName() == null ? other.getModiOperName() == null : this.getModiOperName().equals(other.getModiOperName()))
                && (this.getModiOperId() == null ? other.getModiOperId() == null : this.getModiOperId().equals(other.getModiOperId()))
                && (this.getRegTime() == null ? other.getRegTime() == null : this.getRegTime().equals(other.getRegTime()))
                && (this.getRegDate() == null ? other.getRegDate() == null : this.getRegDate().equals(other.getRegDate()))
                && (this.getRegOperName() == null ? other.getRegOperName() == null : this.getRegOperName().equals(other.getRegOperName()))
                && (this.getRegOperId() == null ? other.getRegOperId() == null : this.getRegOperId().equals(other.getRegOperId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNo() == null) ? 0 : getNo().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getStandardTypeId() == null) ? 0 : getStandardTypeId().hashCode());
        result = prime * result + ((getDataSourceId() == null) ? 0 : getDataSourceId().hashCode());
        result = prime * result + ((getOriginHtml() == null) ? 0 : getOriginHtml().hashCode());
        result = prime * result + ((getOriginPdf() == null) ? 0 : getOriginPdf().hashCode());
        result = prime * result + ((getSignStartDate() == null) ? 0 : getSignStartDate().hashCode());
        result = prime * result + ((getSignStartTime() == null) ? 0 : getSignStartTime().hashCode());
        result = prime * result + ((getSignEndDate() == null) ? 0 : getSignEndDate().hashCode());
        result = prime * result + ((getSignEndTime() == null) ? 0 : getSignEndTime().hashCode());
        result = prime * result + ((getSignPdf() == null) ? 0 : getSignPdf().hashCode());
        result = prime * result + ((getBusinStatus() == null) ? 0 : getBusinStatus().hashCode());
        result = prime * result + ((getDocStatus() == null) ? 0 : getDocStatus().hashCode());
        result = prime * result + ((getCustNo() == null) ? 0 : getCustNo().hashCode());
        result = prime * result + ((getCustName() == null) ? 0 : getCustName().hashCode());
        result = prime * result + ((getOperOrg() == null) ? 0 : getOperOrg().hashCode());
        result = prime * result + ((getModiDate() == null) ? 0 : getModiDate().hashCode());
        result = prime * result + ((getModiTime() == null) ? 0 : getModiTime().hashCode());
        result = prime * result + ((getModiOperName() == null) ? 0 : getModiOperName().hashCode());
        result = prime * result + ((getModiOperId() == null) ? 0 : getModiOperId().hashCode());
        result = prime * result + ((getRegTime() == null) ? 0 : getRegTime().hashCode());
        result = prime * result + ((getRegDate() == null) ? 0 : getRegDate().hashCode());
        result = prime * result + ((getRegOperName() == null) ? 0 : getRegOperName().hashCode());
        result = prime * result + ((getRegOperId() == null) ? 0 : getRegOperId().hashCode());
        return result;
    }
}