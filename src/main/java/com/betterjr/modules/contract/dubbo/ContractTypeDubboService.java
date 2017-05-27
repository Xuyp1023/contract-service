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
import com.betterjr.modules.contract.IContractTypeService;
import com.betterjr.modules.contract.service.ContractTypeService;

/**
 * @author liuwl
 *
 */
@Service(interfaceClass = IContractTypeService.class)
public class ContractTypeDubboService implements IContractTypeService {
    @Resource
    private ContractTypeService contractTypeService;

    /* (non-Javadoc)
     * @see com.betterjr.modules.contract.IContractType#webQueryTypeList()
     */
    @Override
    public String webQueryTypeList() {
        return AjaxObject.newOk("合同类型查询成功！", contractTypeService.queryType()).toJson();
    }

    @Override
    public String webQuerySimpleTypeList() {
        return AjaxObject.newOk("合同类型查询成功！", contractTypeService.querySimpleType()).toJson();
    }

}
