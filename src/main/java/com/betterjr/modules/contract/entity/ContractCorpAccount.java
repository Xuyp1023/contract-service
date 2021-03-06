package com.betterjr.modules.contract.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.betterjr.common.annotation.MetaData;
import com.betterjr.common.entity.BetterjrEntity;
import com.betterjr.common.selectkey.SerialGenerator;
import com.betterjr.common.utils.BetterDateUtils;
import com.betterjr.modules.account.entity.CustOperatorInfo;

@Access(AccessType.FIELD)
@Entity
@Table(name = "t_sys_contract_corp_account")
public class ContractCorpAccount implements BetterjrEntity {

    /**
     * 编号
     */
    @Id
    @Column(name = "ID", columnDefinition = "INTEGER")
    @MetaData(value = "编号", comments = "编号")
    private Long id;

    /**
     * 人员姓名
     */
    @Column(name = "C_NAME", columnDefinition = "VARCHAR")
    @MetaData(value = "人员姓名", comments = "人员姓名")
    private String name;

    /**
     * 人员身份证号
     */
    @Column(name = "C_IDENT_NO", columnDefinition = "VARCHAR")
    @MetaData(value = "人员身份证号", comments = "人员身份证号")
    private String identNo;

    /**
     * 手机号码
     */
    @Column(name = "C_MOBILENO", columnDefinition = "VARCHAR")
    @MetaData(value = "手机号码", comments = "手机号码")
    private String mobileNo;

    /**
     * 注册类型
     */
    @Column(name = "C_TYPE", columnDefinition = "VARCHAR")
    @MetaData(value = "注册类型", comments = "注册类型")
    private String type;

    /**
     * 组织机构代码
     */
    @Column(name = "C_ORG_CODE", columnDefinition = "VARCHAR")
    @MetaData(value = "组织机构代码", comments = "组织机构代码")
    private String orgCode;

    /**
     * 帐号
     */
    @Column(name = "C_ACCOUNT", columnDefinition = "VARCHAR")
    @MetaData(value = "帐号", comments = "帐号")
    private String account;

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

    /**
     * 公司编号
     */
    @Column(name = "L_CUSTNO", columnDefinition = "INTEGER")
    @MetaData(value = "公司编号", comments = "公司编号")
    private Long custNo;

    /**
     * 公司名称
     */
    @Column(name = "C_CUSTNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "公司名称", comments = "公司名称")
    private String custName;

    /**
     * 机构
     */
    @Column(name = "C_OPERORG", columnDefinition = "VARCHAR")
    @MetaData(value = "机构", comments = "机构")
    private String operOrg;

    /**
     * 电子合同服务商编号
     */
    @Column(name = "L_SERVICE_CUSTNO", columnDefinition = "INTEGER")
    @MetaData(value = "电子合同服务商编号", comments = "电子合同服务商编号")
    private Long serviceCustNo;

    /**
     * 电子合同服务商名称
     */
    @Column(name = "C_SERVICE_CUSTNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "电子合同服务商名称", comments = "电子合同服务商名称")
    private String serviceCustName;

    /**
     * 合同签署人编号
     */
    @Column(name = "L_SIGNER_ID", columnDefinition = "INTEGER")
    @MetaData(value = "合同签署人编号", comments = "合同签署人编号")
    private Long signerId;

    /**
     * 合同签署人编号
     */
    @Column(name = "L_SIGNER_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "合同签署人编号", comments = "合同签署人编号")
    private Long signerOperId;

    /**
     * 合同签署人名称
     */
    @Column(name = "C_SIGNER_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "合同签署人名称", comments = "合同签署人名称")
    private String signerOperName;

    /**
     * 合同签署人帐号
     */
    @Column(name = "C_SIGNER_ACCOUNT", columnDefinition = "VARCHAR")
    @MetaData(value = "合同签署人帐号", comments = "合同签署人帐号")
    private String signerAccount;

    /**
     * 合同签署人手机
     */
    @Column(name = "C_SIGNER_MOBILE_NO", columnDefinition = "VARCHAR")
    @MetaData(value = "合同签署人手机", comments = "合同签署人手机")
    private String signerMobileNo;

    /**
     * 证件形式，0：三证合一，1：一证一码
     */
    @Column(name = "C_ORG_REGTYPE", columnDefinition = "VARCHAR")
    @MetaData(value = "证件形式", comments = "证件形式")
    private String orgRegType;

    @Column(name = "D_MODI_DATE", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String modiDate;

    @Column(name = "T_MODI_TIME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String modiTime;

    @Column(name = "C_MODI_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String modiOperName;

    @Column(name = "L_MODI_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "", comments = "")
    private Long modiOperId;

    @Column(name = "T_REG_TIME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String regTime;

    @Column(name = "D_REG_DATE", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String regDate;

    @Column(name = "C_REG_OPERNAME", columnDefinition = "VARCHAR")
    @MetaData(value = "", comments = "")
    private String regOperName;

    @Column(name = "L_REG_OPERID", columnDefinition = "INTEGER")
    @MetaData(value = "", comments = "")
    private Long regOperId;

    private static final long serialVersionUID = 1492408575313L;

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

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(final String orgCode) {
        this.orgCode = orgCode;
    }

    public String getIdentNo() {
        return identNo;
    }

    public void setIdentNo(final String identNo) {
        this.identNo = identNo;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(final String account) {
        this.account = account;
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

    public Long getServiceCustNo() {
        return serviceCustNo;
    }

    public void setServiceCustNo(final Long serviceCustNo) {
        this.serviceCustNo = serviceCustNo;
    }

    public String getServiceCustName() {
        return serviceCustName;
    }

    public void setServiceCustName(final String serviceCustName) {
        this.serviceCustName = serviceCustName;
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

    public Long getSignerId() {
        return signerId;
    }

    public void setSignerId(final Long anSignerId) {
        signerId = anSignerId;
    }

    public Long getSignerOperId() {
        return signerOperId;
    }

    public void setSignerOperId(final Long anSignerOperId) {
        signerOperId = anSignerOperId;
    }

    public String getSignerOperName() {
        return signerOperName;
    }

    public void setSignerOperName(final String anSignerOperName) {
        signerOperName = anSignerOperName;
    }

    public String getSignerAccount() {
        return signerAccount;
    }

    public void setSignerAccount(final String anSignerAccount) {
        signerAccount = anSignerAccount;
    }

    public String getSignerMobileNo() {
        return signerMobileNo;
    }

    public void setSignerMobileNo(final String anSignerMobileNo) {
        signerMobileNo = anSignerMobileNo;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public void setMobileNo(final String anMobileNo) {
        this.mobileNo = anMobileNo;
    }

    public String getOrgRegType() {
        return this.orgRegType;
    }

    public void setOrgRegType(final String anOrgRegType) {
        this.orgRegType = anOrgRegType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", identNo=").append(identNo);
        sb.append(", account=").append(account);
        sb.append(", businStatus=").append(businStatus);
        sb.append(", docStatus=").append(docStatus);
        sb.append(", custNo=").append(custNo);
        sb.append(", custName=").append(custName);
        sb.append(", operOrg=").append(operOrg);
        sb.append(", serviceCustNo=").append(serviceCustNo);
        sb.append(", serviceCustName=").append(serviceCustName);
        sb.append(", signerId=").append(signerId);
        sb.append(", signerOperId=").append(signerOperId);
        sb.append(", signerOperName=").append(signerOperName);
        sb.append(", signerAccount=").append(signerAccount);
        sb.append(", signerMobileNo=").append(signerMobileNo);
        sb.append(", modiDate=").append(modiDate);
        sb.append(", modiTime=").append(modiTime);
        sb.append(", modiOperName=").append(modiOperName);
        sb.append(", modiOperId=").append(modiOperId);
        sb.append(", regTime=").append(regTime);
        sb.append(", regDate=").append(regDate);
        sb.append(", regOperName=").append(regOperName);
        sb.append(", regOperId=").append(regOperId);
        sb.append(", mobileNo=").append(mobileNo);
        sb.append(", orgRegType=").append(orgRegType);
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
        final ContractCorpAccount other = (ContractCorpAccount) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
                && (this.getOrgCode() == null ? other.getOrgCode() == null
                        : this.getOrgCode().equals(other.getOrgCode()))
                && (this.getIdentNo() == null ? other.getIdentNo() == null
                        : this.getIdentNo().equals(other.getIdentNo()))
                && (this.getAccount() == null ? other.getAccount() == null
                        : this.getAccount().equals(other.getAccount()))
                && (this.getBusinStatus() == null ? other.getBusinStatus() == null
                        : this.getBusinStatus().equals(other.getBusinStatus()))
                && (this.getDocStatus() == null ? other.getDocStatus() == null
                        : this.getDocStatus().equals(other.getDocStatus()))
                && (this.getCustNo() == null ? other.getCustNo() == null : this.getCustNo().equals(other.getCustNo()))
                && (this.getCustName() == null ? other.getCustName() == null
                        : this.getCustName().equals(other.getCustName()))
                && (this.getOperOrg() == null ? other.getOperOrg() == null
                        : this.getOperOrg().equals(other.getOperOrg()))
                && (this.getServiceCustNo() == null ? other.getServiceCustNo() == null
                        : this.getServiceCustNo().equals(other.getServiceCustNo()))
                && (this.getServiceCustName() == null ? other.getServiceCustName() == null
                        : this.getServiceCustName().equals(other.getServiceCustName()))
                && (this.getSignerId() == null ? other.getSignerId() == null
                        : this.getSignerId().equals(other.getSignerId()))
                && (this.getSignerOperId() == null ? other.getSignerOperId() == null
                        : this.getSignerOperId().equals(other.getSignerOperId()))
                && (this.getSignerOperName() == null ? other.getSignerOperName() == null
                        : this.getSignerOperName().equals(other.getSignerOperName()))
                && (this.getSignerAccount() == null ? other.getSignerAccount() == null
                        : this.getSignerAccount().equals(other.getSignerAccount()))
                && (this.getSignerMobileNo() == null ? other.getSignerMobileNo() == null
                        : this.getSignerMobileNo().equals(other.getSignerMobileNo()))
                && (this.getModiDate() == null ? other.getModiDate() == null
                        : this.getModiDate().equals(other.getModiDate()))
                && (this.getModiTime() == null ? other.getModiTime() == null
                        : this.getModiTime().equals(other.getModiTime()))
                && (this.getModiOperName() == null ? other.getModiOperName() == null
                        : this.getModiOperName().equals(other.getModiOperName()))
                && (this.getModiOperId() == null ? other.getModiOperId() == null
                        : this.getModiOperId().equals(other.getModiOperId()))
                && (this.getRegTime() == null ? other.getRegTime() == null
                        : this.getRegTime().equals(other.getRegTime()))
                && (this.getRegDate() == null ? other.getRegDate() == null
                        : this.getRegDate().equals(other.getRegDate()))
                && (this.getRegOperName() == null ? other.getRegOperName() == null
                        : this.getRegOperName().equals(other.getRegOperName()))
                && (this.getRegOperId() == null ? other.getRegOperId() == null
                        : this.getRegOperId().equals(other.getRegOperId()))
                && (this.getOrgRegType() == null ? other.getOrgRegType() == null
                        : this.getOrgRegType().equals(other.getOrgRegType()))
                && (this.getMobileNo() == null ? other.getMobileNo() == null
                        : this.getMobileNo().equals(other.getMobileNo()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getIdentNo() == null) ? 0 : getIdentNo().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getBusinStatus() == null) ? 0 : getBusinStatus().hashCode());
        result = prime * result + ((getDocStatus() == null) ? 0 : getDocStatus().hashCode());
        result = prime * result + ((getCustNo() == null) ? 0 : getCustNo().hashCode());
        result = prime * result + ((getCustName() == null) ? 0 : getCustName().hashCode());
        result = prime * result + ((getOperOrg() == null) ? 0 : getOperOrg().hashCode());
        result = prime * result + ((getServiceCustNo() == null) ? 0 : getServiceCustNo().hashCode());
        result = prime * result + ((getServiceCustName() == null) ? 0 : getServiceCustName().hashCode());
        result = prime * result + ((getSignerId() == null) ? 0 : getSignerId().hashCode());
        result = prime * result + ((getSignerOperId() == null) ? 0 : getSignerOperId().hashCode());
        result = prime * result + ((getSignerOperName() == null) ? 0 : getSignerOperName().hashCode());
        result = prime * result + ((getSignerAccount() == null) ? 0 : getSignerAccount().hashCode());
        result = prime * result + ((getSignerMobileNo() == null) ? 0 : getSignerMobileNo().hashCode());
        result = prime * result + ((getModiDate() == null) ? 0 : getModiDate().hashCode());
        result = prime * result + ((getModiTime() == null) ? 0 : getModiTime().hashCode());
        result = prime * result + ((getModiOperName() == null) ? 0 : getModiOperName().hashCode());
        result = prime * result + ((getModiOperId() == null) ? 0 : getModiOperId().hashCode());
        result = prime * result + ((getRegTime() == null) ? 0 : getRegTime().hashCode());
        result = prime * result + ((getRegDate() == null) ? 0 : getRegDate().hashCode());
        result = prime * result + ((getRegOperName() == null) ? 0 : getRegOperName().hashCode());
        result = prime * result + ((getRegOperId() == null) ? 0 : getRegOperId().hashCode());
        result = prime * result + ((getMobileNo() == null) ? 0 : getMobileNo().hashCode());
        result = prime * result + ((getOrgRegType() == null) ? 0 : getOrgRegType().hashCode());
        return result;
    }

    /**
     *
     */
    public void init(final CustOperatorInfo anOperator) {
        this.setId(SerialGenerator.getLongValue("ContractCorpAccount.id"));
        this.setRegDate(BetterDateUtils.getNumDate());
        this.setRegTime(BetterDateUtils.getNumTime());

        this.setModiDate(BetterDateUtils.getNumDate());
        this.setModiTime(BetterDateUtils.getNumTime());

        if (anOperator != null) {
            this.setRegOperId(anOperator.getId());
            this.setRegOperName(anOperator.getName());

            this.setModiOperId(anOperator.getId());
            this.setModiOperName(anOperator.getName());

            this.setOperOrg(anOperator.getOperOrg());
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