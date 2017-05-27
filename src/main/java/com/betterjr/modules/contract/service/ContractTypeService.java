// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.3 : 2017年4月17日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.betterjr.common.data.SimpleDataEntity;
import com.betterjr.common.service.BaseService;
import com.betterjr.modules.contract.dao.ContractTypeMapper;
import com.betterjr.modules.contract.entity.ContractType;

/**
 * @author liuwl
 *
 */
@Service
public class ContractTypeService extends BaseService<ContractTypeMapper, ContractType> {
    /**
     * 查询所有合同类型
     *
     * @return
     */
    public List<ContractType> queryType() {
        return this.selectAll();
    }

    /**
     * 查询所有合同类型 simple方式
     *
     * @return
     */
    public List<SimpleDataEntity> querySimpleType() {
        return this.selectAll().stream()
                .map(contractType -> new SimpleDataEntity(contractType.getName(), contractType.getId().toString()))
                .collect(Collectors.toList());
    }
}
