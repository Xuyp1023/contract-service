// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月19日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.dubbo;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.modules.contract.IContractStandardTypeService;
import com.betterjr.modules.contract.service.ContractStandardTypeService;

/**
 * @author liuwl
 *
 */
@Service(interfaceClass = IContractStandardTypeService.class)
public class ContractStandardTypeDubboService implements IContractStandardTypeService {
    @Resource
    private ContractStandardTypeService contractStandardTypeService;

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStandardTypeService#webQueryTypeList()
     */
    @Override
    public String webQueryTypeList(final Long anTypeId) {
        return AjaxObject.newOk("标准合同类型查询成功！", contractStandardTypeService.queryStandardType(anTypeId)).toJson();
    }

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractStandardTypeService#webQuerySimpleTypeList()
     */
    @Override
    public String webQuerySimpleTypeList(final Long anTypeId) {
        return AjaxObject.newOk("标准合同类型查询成功！", contractStandardTypeService.querySimpleStandardType(anTypeId)).toJson();
    }

}
