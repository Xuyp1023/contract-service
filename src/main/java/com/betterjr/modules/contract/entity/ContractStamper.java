package com.betterjr.modules.contract.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.betterjr.common.annotation.MetaData;
import com.betterjr.common.entity.BetterjrEntity;
import com.betterjr.common.selectkey.SerialGenerator;
import com.betterjr.common.utils.BetterDateUtils;
import com.betterjr.modules.account.entity.CustOperatorInfo;

@Access(AccessType.FIELD)
@Entity
@Table(name = "t_sys_contract_stamper")
public class ContractStamper implements BetterjrEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "ID", columnDefinition = "INTEGER")
    @MetaData(value = "编号", comments = "编号")
    private Long id;

    /**
     * 名称
     */
    @Column(name = "C_NAME", columnDefinition = "VARCHAR")
    @MetaData(value = "名称", comments = "名称")
    private String name;

    /**
     * 原始文件(BATCHNO)
     */
    @Column(name = "L_ORIGIN_STAMPER", columnDefinition = "INTEGER")
    @MetaData(value = "原始文件(BATCHNO)", comments = "原始文件(BATCHNO)")
    private Long originStamper;

    @Transient
    private Long originStamperId;
    @Transient
    private String originStamperName;

    /**
     * 原始文件日期
     */
    @Column(name = "D_ORIGIN_DATE", columnDefinition = "CHAR")
    @MetaData(value = "原始文件日期", comments = "原始文件日期")
    private String originDate;

    /**
     * 原始文件时间
     */
    @Column(name = "T_ORIGIN_TIME", columnDefinition = "CHAR")
    @MetaData(value = "原始文件时间", comments = "原始文件时间")
    private String originTime;

    /**
     * 印章文件
     */
    @Column(name = "L_STAMPER", columnDefinition = "INTEGER")
    @MetaData(value = "印章文件", comments = "印章文件")
    private Long stamper;

    /**
     * 印章文件
     */
    @Column(name = "C_STAMPER_DATA", columnDefinition = "TEXT")
    @MetaData(value = "印章文件内容", comments = "印章文件内容")
    private String stamperData;

    @Transient
    private Long stamperId;
    @Transient
    private String stamperName;

    /**
     * 制作日期
     */
    @Column(name = "D_MAKE_DATE", columnDefinition = "CHAR")
    @MetaData(value = "制作日期", comments = "制作日期")
    private String makeDate;

    /**
     * 制作时间
     */
    @Column(name = "T_MAKE_TIME", columnDefinition = "CHAR")
    @MetaData(value = "制作时间", comments = "制作时间")
    private String makeTime;

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
     * 业务状态
     */
    @Column(name = "C_BUSIN_STATUS", columnDefinition = "CHAR")
    @MetaData(value = "业务状态", comments = "业务状态")
    private String businStatus;

    /**
     * 客户类型：0：机构；1：个人
     */
    @Column(name = "C_CUSTTYPE", columnDefinition = "CHAR")
    @MetaData(value = "客户类型：0：机构", comments = "客户类型：0：机构；1：个人")
    private String custType;

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

    @Column(name = "D_MODI_DATE", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String modiDate;

    @Column(name = "T_MODI_TIME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String modiTime;

    private static final long serialVersionUID = 1492408575299L;

    public Long getOriginStamperId() {
        return originStamperId;
    }

    public void setOriginStamperId(final Long anOriginStamperId) {
        originStamperId = anOriginStamperId;
    }

    public String getOriginStamperName() {
        return originStamperName;
    }

    public void setOriginStamperName(final String anOriginStamperName) {
        originStamperName = anOriginStamperName;
    }

    public Long getStamperId() {
        return stamperId;
    }

    public void setStamperId(final Long anStamperId) {
        stamperId = anStamperId;
    }

    public String getStamperName() {
        return stamperName;
    }

    public void setStamperName(final String anStamperName) {
        stamperName = anStamperName;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getOriginStamper() {
        return originStamper;
    }

    public void setOriginStamper(final Long originStamper) {
        this.originStamper = originStamper;
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

    public Long getStamper() {
        return stamper;
    }

    public void setStamper(final Long stamper) {
        this.stamper = stamper;
    }

    public String getStamperData() {
        return stamperData;
    }

    public void setStamperData(final String stamperData) {
        this.stamperData = stamperData;
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

    public String getCustType() {
        return this.custType;
    }

    public void setCustType(final String anCustType) {
        this.custType = anCustType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", originStamper=").append(originStamper);
        sb.append(", originDate=").append(originDate);
        sb.append(", originTime=").append(originTime);
        sb.append(", stamper=").append(stamper);
        sb.append(", stamperData=").append(stamperData);
        sb.append(", makeDate=").append(makeDate);
        sb.append(", makeTime=").append(makeTime);
        sb.append(", custNo=").append(custNo);
        sb.append(", custName=").append(custName);
        sb.append(", operOrg=").append(operOrg);
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
        sb.append(", custType=").append(custType);
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
        final ContractStamper other = (ContractStamper) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getOriginStamper() == null ? other.getOriginStamper() == null : this.getOriginStamper().equals(other.getOriginStamper()))
                && (this.getOriginDate() == null ? other.getOriginDate() == null : this.getOriginDate().equals(other.getOriginDate()))
                && (this.getOriginTime() == null ? other.getOriginTime() == null : this.getOriginTime().equals(other.getOriginTime()))
                && (this.getStamper() == null ? other.getStamper() == null : this.getStamper().equals(other.getStamper()))
                && (this.getStamperData() == null ? other.getStamperData() == null : this.getStamperData().equals(other.getStamperData()))
                && (this.getMakeDate() == null ? other.getMakeDate() == null : this.getMakeDate().equals(other.getMakeDate()))
                && (this.getMakeTime() == null ? other.getMakeTime() == null : this.getMakeTime().equals(other.getMakeTime()))
                && (this.getCustNo() == null ? other.getCustNo() == null : this.getCustNo().equals(other.getCustNo()))
                && (this.getCustName() == null ? other.getCustName() == null : this.getCustName().equals(other.getCustName()))
                && (this.getOperOrg() == null ? other.getOperOrg() == null : this.getOperOrg().equals(other.getOperOrg()))
                && (this.getBusinStatus() == null ? other.getBusinStatus() == null : this.getBusinStatus().equals(other.getBusinStatus()))
                && (this.getDocStatus() == null ? other.getDocStatus() == null : this.getDocStatus().equals(other.getDocStatus()))
                && (this.getRegOperId() == null ? other.getRegOperId() == null : this.getRegOperId().equals(other.getRegOperId()))
                && (this.getRegOperName() == null ? other.getRegOperName() == null : this.getRegOperName().equals(other.getRegOperName()))
                && (this.getRegDate() == null ? other.getRegDate() == null : this.getRegDate().equals(other.getRegDate()))
                && (this.getRegTime() == null ? other.getRegTime() == null : this.getRegTime().equals(other.getRegTime()))
                && (this.getModiOperId() == null ? other.getModiOperId() == null : this.getModiOperId().equals(other.getModiOperId()))
                && (this.getModiOperName() == null ? other.getModiOperName() == null : this.getModiOperName().equals(other.getModiOperName()))
                && (this.getModiDate() == null ? other.getModiDate() == null : this.getModiDate().equals(other.getModiDate()))
                && (this.getModiTime() == null ? other.getModiTime() == null : this.getModiTime().equals(other.getModiTime()))
                && (this.getCustType() == null ? other.getCustType() == null : this.getCustType().equals(other.getCustType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getOriginStamper() == null) ? 0 : getOriginStamper().hashCode());
        result = prime * result + ((getOriginDate() == null) ? 0 : getOriginDate().hashCode());
        result = prime * result + ((getOriginTime() == null) ? 0 : getOriginTime().hashCode());
        result = prime * result + ((getStamper() == null) ? 0 : getStamper().hashCode());
        result = prime * result + ((getStamperData() == null) ? 0 : getStamperData().hashCode());
        result = prime * result + ((getMakeDate() == null) ? 0 : getMakeDate().hashCode());
        result = prime * result + ((getMakeTime() == null) ? 0 : getMakeTime().hashCode());
        result = prime * result + ((getCustNo() == null) ? 0 : getCustNo().hashCode());
        result = prime * result + ((getCustName() == null) ? 0 : getCustName().hashCode());
        result = prime * result + ((getOperOrg() == null) ? 0 : getOperOrg().hashCode());
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
        result = prime * result + ((getCustType() == null) ? 0 : getCustType().hashCode());
        return result;
    }

    /**
     *
     */
    public void init(final CustOperatorInfo anOperator) {
        this.setId(SerialGenerator.getLongValue("ContractStamper.id"));
        this.setRegDate(BetterDateUtils.getNumDate());
        this.setRegTime(BetterDateUtils.getNumTime());

        this.setModiDate(BetterDateUtils.getNumDate());
        this.setModiTime(BetterDateUtils.getNumTime());

        this.setOriginDate(BetterDateUtils.getNumDate());
        this.setOriginTime(BetterDateUtils.getNumTime());

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