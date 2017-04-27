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

import org.springframework.stereotype.Service;

import com.betterjr.common.data.SimpleDataEntity;
import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.UserUtils;
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
                .map(standardType -> new SimpleDataEntity(standardType.getId().toString(), standardType.getName())).collect(Collectors.toList());
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
}
