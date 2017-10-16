// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.3 : 2017年4月17日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.betterjr.common.data.SimpleDataEntity;
import com.betterjr.common.exception.BytterTradeException;
import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.BetterDateUtils;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.common.utils.QueryTermBuilder;
import com.betterjr.common.utils.UserUtils;
import com.betterjr.mapper.pagehelper.Page;
import com.betterjr.modules.contract.dao.ContractStandardTypeMapper;
import com.betterjr.modules.contract.entity.ContractStandardType;

/**
 * @author liuwl
 *
 */
@Service
public class ContractStandardTypeService extends BaseService<ContractStandardTypeMapper, ContractStandardType> {

    /**
     * 获取标准合同类型 已生效
     *
     * @param anTypeId
     *            为空标识全部
     *
     * @return
     */
    public List<ContractStandardType> queryStandardType(final Long anTypeId) {
        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("businStatus", "01");
        if (anTypeId != null) {
            conditionMap.put("typeId", anTypeId);
        }

        return this.selectByProperty(conditionMap);
    }

    /**
     * 获取标准合同类型 未使用
     *
     * @param anTypeId
     *            为空标识全部
     * @param anCustNo
     *
     * @return
     */
    protected List<ContractStandardType> queryUnusedStandardType(final Long anTypeId, final Long anCustNo) {
        if (anTypeId != null) {
            return this.mapper.queryCustUnusedStandardType2(anTypeId, anCustNo);
        } else {
            return this.mapper.queryCustUnusedStandardType1(anCustNo);
        }

    }

    /**
     *
     * @param anId
     * @return
     */
    public ContractStandardType findStandardType(final Long anId) {
        BTAssert.notNull(anId, "标准合同编号不允许为空！");

        return this.selectByPrimaryKey(anId);
    }

    /**
     * 获取标准合同类型 已生效
     *
     * @return
     */
    public List<SimpleDataEntity> querySimpleStandardType(final Long anTypeId) {
        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("businStatus", "01");
        if (anTypeId != null) {
            conditionMap.put("typeId", anTypeId);
        }

        return this.selectByProperty(conditionMap).stream()
                .map(standardType -> new SimpleDataEntity(standardType.getId().toString(), standardType.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 获取标准合同类型 全部
     *
     * @return
     */
    public List<ContractStandardType> queryAllStandardType() {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败!");
        return this.selectAll();
    }

    /**
     * 启用标准合同
     *
     * @param anId
     * @return
     */
    public ContractStandardType saveEnableStandardType(final String anId) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败!");
        BTAssert.notNull(anId, "标准合同编号不允许为空！");

        final ContractStandardType contractStandardType = this.selectByPrimaryKey(anId);

        BTAssert.notNull(contractStandardType, "启用标准合同失败！");

        contractStandardType.setBusinStatus("01");

        final int result = this.updateByPrimaryKeySelective(contractStandardType);

        BTAssert.isTrue(result == 1, "启用标准合同失败！");

        return contractStandardType;
    }

    /**
    * 标准合同类型登记
    */
    public ContractStandardType addContractStandards(Map<String, Object> anMap,
            ContractStandardType contractStandardType) {
        // TODO Auto-generated method stub
        logger.info("标准合同类型登记");
        BTAssert.isTrue(UserUtils.platformUser(), "无权限进行操作！");
        contractStandardType.initAddValue();
        if (anMap.containsKey("description")) {
            contractStandardType.setDescription(anMap.get("description").toString());
        }
        if (anMap.containsKey("typeIdName")) {
            contractStandardType.setTypeIdName(anMap.get("typeIdName").toString());
        }
        contractStandardType.setTypeId(Long.valueOf(anMap.get("typeId").toString()));
        contractStandardType.setBusinTypeId(Long.valueOf(anMap.get("typeId").toString()));
        this.insert(contractStandardType);
        return contractStandardType;
    }

    /**
    * 已启用标准合同类型查询
    */
    public Page<ContractStandardType> queryContractStandardtType(int anPageNum, int anPageSize, String anFlag) {
        Map<String, Object> anMap = QueryTermBuilder.newInstance().build();
        // 状态 00登记 01生效
        anMap.put("businStatus", "01");
        return this.selectPropertyByPage(anMap, anPageNum, anPageSize, "1".equals(anFlag), "id");
    }

    /**
     * 编辑标准合同类型
     */
    public ContractStandardType saveModifyContractType(ContractStandardType anContractStandardType, Long anId) {
        ContractStandardType contractStandardType = this.selectByPrimaryKey(anId);
        contractStandardType.setVersion(contractStandardType.getVersion() + 1L);
        BTAssert.notNull(contractStandardType, "无法获取对应标准合同类型！");
        // 仅未启用状态下可以编辑
        if (!StringUtils.equals("00", contractStandardType.getBusinStatus())) {
            throw new BytterTradeException(40001, "无权限进行操作！！");
        }
        contractStandardType.initModifyValue(anContractStandardType);
        this.updateByPrimaryKeySelective(contractStandardType);
        return contractStandardType;
    }

    /**
     * 删除标准合同类型
     */
    public int saveDeleteContractStandardType(Long anId) {
        ContractStandardType contractStandardType = this.selectByPrimaryKey(anId);
        BTAssert.notNull(contractStandardType, "无法获取对标准应合同类型！");
        // 仅未启用状态下可以编辑
        if (!StringUtils.equals("00", contractStandardType.getBusinStatus())) {
            throw new BytterTradeException(40001, "无权限进行操作！");
        }
        return this.delete(contractStandardType);
    }

    /**
     * 标准合同类型待审核查询
     */
    public Page<ContractStandardType> queryUnEnableContractStandardType(int anPageNum, int anPageSize, String anFlag) {
        Map<String, Object> anMap = QueryTermBuilder.newInstance().build();
        // 状态 00登记 01生效
        anMap.put("businStatus", "00");
        return this.selectPropertyByPage(anMap, anPageNum, anPageSize, "1".equals(anFlag), "id");
    }

    /**
     * 启用标准合同类型
     */
    public ContractStandardType saveEnableContractStandardTyp(Long anId) {
        ContractStandardType contractStandardType = this.selectByPrimaryKey(anId);
        BTAssert.notNull(contractStandardType, "无法获取对应合同类型！");
        // 设置状态启用, 00登记 01启动
        contractStandardType.setBusinStatus("01");
        BetterDateUtils betterDateUtils = new BetterDateUtils();
        contractStandardType.setEnableDate(BetterDateUtils.getNumDate());
        this.updateByPrimaryKeySelective(contractStandardType);
        return contractStandardType;
    }

    /**
     * 查询所有标准合同类型
     */
    public Page<ContractStandardType> queryAllContractStandardTyp(Map<String, Object> anParam, int anPageNum,
            int anPageSize, String anFlag) {
        Map<String, Object> anMap = QueryTermBuilder.newInstance().build();
        if (StringUtils.isBlank((String) anParam.get("businStatus"))) {
            anMap.put("docStatus", "01");
        } else {
            anMap.put("businStatus", anParam.get("businStatus").toString());
        }
        return this.selectPropertyByPage(anMap, anPageNum, anPageSize, "1".equals(anFlag), "id desc");
    }

    /**
     * 停用标准合同类型
     */
    public ContractStandardType saveStopContractStandardTyp(Long anId) {
        ContractStandardType contractStandardType = this.selectByPrimaryKey(anId);
        BTAssert.notNull(contractStandardType, "无法获取对应合同类型！");
        // 设置状态启用, 00登记 01启动 02停用
        contractStandardType.setBusinStatus("02");
        BetterDateUtils betterDateUtils = new BetterDateUtils();
        contractStandardType.setEnableDate(BetterDateUtils.getNumDate());
        this.updateByPrimaryKeySelective(contractStandardType);
        return contractStandardType;
    }

}
