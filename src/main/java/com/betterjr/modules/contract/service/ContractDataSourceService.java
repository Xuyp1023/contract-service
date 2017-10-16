// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.3 : 2017年4月17日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.modules.contract.dao.ContractDataSourceMapper;
import com.betterjr.modules.contract.entity.ContractDataSource;

/**
 * @author liuwl
 *
 */
@Service
public class ContractDataSourceService extends BaseService<ContractDataSourceMapper, ContractDataSource> {

    /**
     * 添加一个数据源
     * @param anDataSource
     * @return
     */
    public ContractDataSource saveAddDataSource(final ContractDataSource anDataSource) {
        BTAssert.notNull(anDataSource, "数据不允许为空！");

        final int result = this.insert(anDataSource);

        BTAssert.isTrue(result == 1, "数据源添加失败！");

        return anDataSource;
    }

    /**
     * 添加外部数据
     * @param anDataSourceId
     * @param anExternalData
     * @return
     */
    public ContractDataSource saveAddExternalData(final Long anDataSourceId, final String anExternalData) {
        BTAssert.notNull(anDataSourceId, "数据源编号不允许为空！");
        BTAssert.isTrue(StringUtils.isNotBlank(anExternalData), "外部数据不允许为空！");

        final ContractDataSource dataSource = this.selectByPrimaryKey(anDataSourceId);
        BTAssert.notNull(dataSource, "没有找到数据源！");

        dataSource.setExternalData(anExternalData);

        final int result = this.updateByPrimaryKeySelective(dataSource);

        BTAssert.isTrue(result == 1, "外部数据源添加失败！");

        return dataSource;
    }

    /**
     * 获取数据源
     * @param anDataSourceId
     * @return
     */
    public ContractDataSource findDataSource(final Long anDataSourceId) {
        BTAssert.notNull(anDataSourceId, "数据源编号不允许为空！");

        return this.selectByPrimaryKey(anDataSourceId);
    }
}
