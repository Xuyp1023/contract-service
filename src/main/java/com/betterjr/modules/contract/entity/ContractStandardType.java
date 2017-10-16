package com.betterjr.modules.contract.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.betterjr.common.annotation.MetaData;
import com.betterjr.common.entity.BetterjrEntity;
import com.betterjr.common.mapper.CustDateJsonSerializer;
import com.betterjr.common.selectkey.SerialGenerator;
import com.betterjr.common.utils.BetterDateUtils;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Access(AccessType.FIELD)
@Entity
@Table(name = "t_sys_contract_standard_type")
public class ContractStandardType implements BetterjrEntity {
    /**
     * 编号
     */
    @Id
    @Column(name = "ID", columnDefinition = "INTEGER")
    @MetaData(value = "编号", comments = "编号")
    private Long id;

    /**
     * 类型编码
     */
    @Column(name = "C_CODE", columnDefinition = "VARCHAR")
    @MetaData(value = "类型编码", comments = "类型编码")
    private String code;

    /**
     * 类型名称
     */
    @Column(name = "C_NAME", columnDefinition = "VARCHAR")
    @MetaData(value = "类型名称", comments = "类型名称")
    private String name;

    /**
     * 合同类型
     */
    @Column(name = "L_TYPE_ID", columnDefinition = "INTEGER")
    @MetaData(value = "合同类型", comments = "合同类型")
    private Long typeId;

    /**
     * 业务类型
     */
    @Column(name = "L_BUSIN_TYPE_ID", columnDefinition = "INTEGER")
    @MetaData(value = "业务类型", comments = "业务类型")
    private Long businTypeId;
    /**
     * 启用日期
     */
    @JsonSerialize(using = CustDateJsonSerializer.class)
    @Column(name = "D_ENABLE_DATE", columnDefinition = "CHAR")
    @MetaData(value = "启用日期", comments = "启用日期")
    private String enableDate;

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
     * 数据版本
     */
    @Column(name = "N_VERSION", columnDefinition = "INTEGER")
    @MetaData(value = "数据版本", comments = "数据版本")
    private Long version;

    /**
     * 备注
     */
    @Column(name = "C_DESCRIPTION", columnDefinition = "CHAR")
    @MetaData(value = "备注", comments = "备注")
    private String description;

    /**
     * 登记时间
     */
    @Column(name = "D_REG_DATE", columnDefinition = "CHAR")
    @MetaData(value = "登记时间", comments = "登记时间")
    private String regDate;

    /**
     * 合同类型名
     */
    @Column(name = "C_TYPEID_NAME", columnDefinition = "CHAR")
    @MetaData(value = "合同类型名", comments = "合同类型名")
    private String typeIdName;

    private static final long serialVersionUID = 1492408575297L;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(final Long typeId) {
        this.typeId = typeId;
    }

    public Long getBusinTypeId() {
        return businTypeId;
    }

    public void setBusinTypeId(final Long businTypeId) {
        this.businTypeId = businTypeId;
    }

    public String getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(final String enableDate) {
        this.enableDate = enableDate;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
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
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", typeId=").append(typeId);
        sb.append(", businTypeId=").append(businTypeId);
        sb.append(", enableDate=").append(enableDate);
        sb.append(", businStatus=").append(businStatus);
        sb.append(", docStatus=").append(docStatus);
        sb.append(", version=").append(version);
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
        final ContractStandardType other = (ContractStandardType) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getTypeId() == null ? other.getTypeId() == null : this.getTypeId().equals(other.getTypeId()))
                && (this.getBusinTypeId() == null ? other.getBusinTypeId() == null
                        : this.getBusinTypeId().equals(other.getBusinTypeId()))
                && (this.getEnableDate() == null ? other.getEnableDate() == null
                        : this.getEnableDate().equals(other.getEnableDate()))
                && (this.getBusinStatus() == null ? other.getBusinStatus() == null
                        : this.getBusinStatus().equals(other.getBusinStatus()))
                && (this.getDocStatus() == null ? other.getDocStatus() == null
                        : this.getDocStatus().equals(other.getDocStatus()))
                && (this.getVersion() == null ? other.getVersion() == null
                        : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTypeId() == null) ? 0 : getTypeId().hashCode());
        result = prime * result + ((getBusinTypeId() == null) ? 0 : getBusinTypeId().hashCode());
        result = prime * result + ((getEnableDate() == null) ? 0 : getEnableDate().hashCode());
        result = prime * result + ((getBusinStatus() == null) ? 0 : getBusinStatus().hashCode());
        result = prime * result + ((getDocStatus() == null) ? 0 : getDocStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    public void initAddValue() {
        this.id = SerialGenerator.getLongValue("ContractStandardType.id");
        this.code = this.id.toString();
        // 状态 00未启用 01启用 02停用
        this.businStatus = "00";
        this.docStatus = "01";
        this.version = 1L;
        this.regDate = BetterDateUtils.getNumDate();

    }

    public void initModifyValue(ContractStandardType contractStandardType) {
        this.name = contractStandardType.getName();
        this.description = contractStandardType.getDescription();
        this.enableDate = BetterDateUtils.getNumDate();
    }
}