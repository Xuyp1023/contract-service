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
@Table(name = "t_sys_contract_stub")
public class ContractStub implements BetterjrEntity {
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
    @Column(name = "L_CONTRACT_ID",  columnDefinition="INTEGER" )
    @MetaData( value="电子合同编号", comments = "电子合同编号")
    private Long contractId;

    /**
     * 签署方
     */
    @Column(name = "C_SIGNATORY",  columnDefinition="CHAR" )
    @MetaData( value="签署方", comments = "签署方")
    private String signatory;

    /**
     * 签署顺序
     */
    @Column(name = "N_SEQUENCE",  columnDefinition="INTEGER" )
    @MetaData( value="签署顺序", comments = "签署顺序")
    private Long sequence;

    /**
     * 操作员编号
     */
    @Column(name = "L_OPERID",  columnDefinition="INTEGER" )
    @MetaData( value="操作员编号", comments = "操作员编号")
    private Long operId;

    /**
     * 操作员名称
     */
    @Column(name = "L_OPERNAME",  columnDefinition="VARCHAR" )
    @MetaData( value="操作员名称", comments = "操作员名称")
    private String lOpername;

    /**
     * 企业帐号
     */
    @Column(name = "L_CORP_ACCOUNT_ID",  columnDefinition="INTEGER" )
    @MetaData( value="企业帐号", comments = "企业帐号")
    private Long corpAccountId;

    /**
     * 签署人帐号
     */
    @Column(name = "L_SIGNER_ACCOUNT_ID",  columnDefinition="INTEGER" )
    @MetaData( value="签署人帐号", comments = "签署人帐号")
    private Long signerAccountId;

    /**
     * 公司签署人编号
     */
    @Column(name = "L_CORP_SIGNER_ID",  columnDefinition="INTEGER" )
    @MetaData( value="公司签署人编号", comments = "公司签署人编号")
    private Long corpSignerId;

    /**
     * 位置类型
     */
    @Column(name = "C_POSITION_TYPE",  columnDefinition="CHAR" )
    @MetaData( value="位置类型", comments = "位置类型")
    private String positionType;

    /**
     * 页码
     */
    @Column(name = "N_PAGINATION",  columnDefinition="INTEGER" )
    @MetaData( value="页码", comments = "页码")
    private Long pagination;

    /**
     * X轴
     */
    @Column(name = "N_AXIS_X",  columnDefinition="INTEGER" )
    @MetaData( value="X轴", comments = "X轴")
    private Long axisX;

    /**
     * Y轴
     */
    @Column(name = "N_AXIS_Y",  columnDefinition="INTEGER" )
    @MetaData( value="Y轴", comments = "Y轴")
    private Long axisY;

    /**
     * 关键字
     */
    @Column(name = "C_KEY_WORD",  columnDefinition="VARCHAR" )
    @MetaData( value="关键字", comments = "关键字")
    private String keyWord;

    /**
     * 签署日期
     */
    @Column(name = "D_SIGN_DATE",  columnDefinition="VARCHAR" )
    @MetaData( value="签署日期", comments = "签署日期")
    private String signDate;

    /**
     * 签署时间
     */
    @Column(name = "T_SIGN_TIME",  columnDefinition="VARCHAR" )
    @MetaData( value="签署时间", comments = "签署时间")
    private String signTime;

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

    private static final long serialVersionUID = 1492408575311L;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(final Long contractId) {
        this.contractId = contractId;
    }

    public String getSignatory() {
        return signatory;
    }

    public void setSignatory(final String signatory) {
        this.signatory = signatory;
    }

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(final Long sequence) {
        this.sequence = sequence;
    }

    public Long getOperId() {
        return operId;
    }

    public void setOperId(final Long operId) {
        this.operId = operId;
    }

    public String getlOpername() {
        return lOpername;
    }

    public void setlOpername(final String lOpername) {
        this.lOpername = lOpername == null ? null : lOpername.trim();
    }

    public Long getCorpAccountId() {
        return corpAccountId;
    }

    public void setCorpAccountId(final Long corpAccountId) {
        this.corpAccountId = corpAccountId;
    }

    public Long getSignerAccountId() {
        return signerAccountId;
    }

    public void setSignerAccountId(final Long signerAccountId) {
        this.signerAccountId = signerAccountId;
    }

    public Long getCorpSignerId() {
        return corpSignerId;
    }

    public void setCorpSignerId(final Long corpSignerId) {
        this.corpSignerId = corpSignerId;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(final String positionType) {
        this.positionType = positionType;
    }

    public Long getPagination() {
        return pagination;
    }

    public void setPagination(final Long pagination) {
        this.pagination = pagination;
    }

    public Long getAxisX() {
        return axisX;
    }

    public void setAxisX(final Long axisX) {
        this.axisX = axisX;
    }

    public Long getAxisY() {
        return axisY;
    }

    public void setAxisY(final Long axisY) {
        this.axisY = axisY;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(final String keyWord) {
        this.keyWord = keyWord;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(final String signDate) {
        this.signDate = signDate == null ? null : signDate.trim();
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(final String signTime) {
        this.signTime = signTime == null ? null : signTime.trim();
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
        sb.append(", contractId=").append(contractId);
        sb.append(", signatory=").append(signatory);
        sb.append(", sequence=").append(sequence);
        sb.append(", operId=").append(operId);
        sb.append(", lOpername=").append(lOpername);
        sb.append(", corpAccountId=").append(corpAccountId);
        sb.append(", signerAccountId=").append(signerAccountId);
        sb.append(", corpSignerId=").append(corpSignerId);
        sb.append(", positionType=").append(positionType);
        sb.append(", pagination=").append(pagination);
        sb.append(", axisX=").append(axisX);
        sb.append(", axisY=").append(axisY);
        sb.append(", keyWord=").append(keyWord);
        sb.append(", signDate=").append(signDate);
        sb.append(", signTime=").append(signTime);
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
        final ContractStub other = (ContractStub) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getContractId() == null ? other.getContractId() == null : this.getContractId().equals(other.getContractId()))
                && (this.getSignatory() == null ? other.getSignatory() == null : this.getSignatory().equals(other.getSignatory()))
                && (this.getSequence() == null ? other.getSequence() == null : this.getSequence().equals(other.getSequence()))
                && (this.getOperId() == null ? other.getOperId() == null : this.getOperId().equals(other.getOperId()))
                && (this.getlOpername() == null ? other.getlOpername() == null : this.getlOpername().equals(other.getlOpername()))
                && (this.getCorpAccountId() == null ? other.getCorpAccountId() == null : this.getCorpAccountId().equals(other.getCorpAccountId()))
                && (this.getSignerAccountId() == null ? other.getSignerAccountId() == null : this.getSignerAccountId().equals(other.getSignerAccountId()))
                && (this.getCorpSignerId() == null ? other.getCorpSignerId() == null : this.getCorpSignerId().equals(other.getCorpSignerId()))
                && (this.getPositionType() == null ? other.getPositionType() == null : this.getPositionType().equals(other.getPositionType()))
                && (this.getPagination() == null ? other.getPagination() == null : this.getPagination().equals(other.getPagination()))
                && (this.getAxisX() == null ? other.getAxisX() == null : this.getAxisX().equals(other.getAxisX()))
                && (this.getAxisY() == null ? other.getAxisY() == null : this.getAxisY().equals(other.getAxisY()))
                && (this.getKeyWord() == null ? other.getKeyWord() == null : this.getKeyWord().equals(other.getKeyWord()))
                && (this.getSignDate() == null ? other.getSignDate() == null : this.getSignDate().equals(other.getSignDate()))
                && (this.getSignTime() == null ? other.getSignTime() == null : this.getSignTime().equals(other.getSignTime()))
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
        result = prime * result + ((getContractId() == null) ? 0 : getContractId().hashCode());
        result = prime * result + ((getSignatory() == null) ? 0 : getSignatory().hashCode());
        result = prime * result + ((getSequence() == null) ? 0 : getSequence().hashCode());
        result = prime * result + ((getOperId() == null) ? 0 : getOperId().hashCode());
        result = prime * result + ((getlOpername() == null) ? 0 : getlOpername().hashCode());
        result = prime * result + ((getCorpAccountId() == null) ? 0 : getCorpAccountId().hashCode());
        result = prime * result + ((getSignerAccountId() == null) ? 0 : getSignerAccountId().hashCode());
        result = prime * result + ((getCorpSignerId() == null) ? 0 : getCorpSignerId().hashCode());
        result = prime * result + ((getPositionType() == null) ? 0 : getPositionType().hashCode());
        result = prime * result + ((getPagination() == null) ? 0 : getPagination().hashCode());
        result = prime * result + ((getAxisX() == null) ? 0 : getAxisX().hashCode());
        result = prime * result + ((getAxisY() == null) ? 0 : getAxisY().hashCode());
        result = prime * result + ((getKeyWord() == null) ? 0 : getKeyWord().hashCode());
        result = prime * result + ((getSignDate() == null) ? 0 : getSignDate().hashCode());
        result = prime * result + ((getSignTime() == null) ? 0 : getSignTime().hashCode());
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