package com.betterjr.modules.contract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import com.betterjr.common.annotation.BetterjrMapper;
import com.betterjr.mapper.common.Mapper;
import com.betterjr.modules.contract.entity.ContractStandardType;

@BetterjrMapper
public interface ContractStandardTypeMapper extends Mapper<ContractStandardType> {
    @Select("SELECT * FROM t_sys_contract_standard_type t1 WHERE t1.C_BUSIN_STATUS = '01' AND NOT EXISTS(SELECT 1 FROM t_sys_contract_template t2 WHERE t2.L_CUSTNO = #{custNo} AND t2.L_STANDARD_TYPE_ID = t1.ID)")
    @ResultType(ContractStandardType.class)
    public List<ContractStandardType> queryCustUnusedStandardType1(@Param("custNo") Long custNo);

    @Select("SELECT * FROM t_sys_contract_standard_type t1 WHERE t1.L_TYPE_ID = #{typeId} AND t1.C_BUSIN_STATUS = '01' AND NOT EXISTS(SELECT 1 FROM t_sys_contract_template t2 WHERE t2.L_CUSTNO = #{custNo} AND t2.L_STANDARD_TYPE_ID = t1.ID)")
    @ResultType(ContractStandardType.class)
    public List<ContractStandardType> queryCustUnusedStandardType2(@Param("typeId") Long anTypeId,
            @Param("custNo") Long custNo);
}