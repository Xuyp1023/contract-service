// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.0 : 2017年4月19日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.dubbo;

import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.dubbo.config.annotation.Service;
import com.betterjr.common.web.AjaxObject;
import com.betterjr.modules.contract.IContractStandardTypeService;
import com.betterjr.modules.contract.entity.ContractStandardType;
import com.betterjr.modules.contract.service.ContractStandardTypeService;
import com.betterjr.modules.rule.service.RuleServiceDubboFilterInvoker;

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
    
    
  	@Override
  	public String webContractStandards(Map<String, Object> anMap) {
  		// TODO Auto-generated method stub
  		ContractStandardType contractStandardType = RuleServiceDubboFilterInvoker.getInputObj();
  		 return AjaxObject.newOk("标准合同登记成功！", contractStandardTypeService.addContractStandards(anMap,contractStandardType)).toJson();
  	}
      

  	@Override
  	public String webSaveModifyContractType(Map<String, Object> anMap, Long id) {
  		// TODO Auto-generated method stub
  		ContractStandardType contractStandardType = RuleServiceDubboFilterInvoker.getInputObj();
  	    return AjaxObject.newOk("标准合同类型编辑成功！", contractStandardTypeService.saveModifyContractType(contractStandardType, id)).toJson();
  	    }

	@Override
	public String webSaveDeleteContractStandardType(Long id) {
		// TODO Auto-generated method stub
        return AjaxObject.newOk("标准合同类型删除成功！", contractStandardTypeService.saveDeleteContractStandardType(id)).toJson();
	}

	@Override
	public String webQueryUnEnableContractStandardType(int anPageNum, int anPageSize, String anFlag) {
		// TODO Auto-generated method stub
        return AjaxObject.newOkWithPage("查询未启用标准合同类型成功！", contractStandardTypeService.queryUnEnableContractStandardType(anPageNum, anPageSize, anFlag)).toJson();

	}
	
  	@Override
  	public String webQueryContractStandardType(int anPageNum, int anPageSize, String anFlag) {
  		// TODO Auto-generated method stub
          return AjaxObject.newOkWithPage("查询已启用标准合同成功！", contractStandardTypeService.queryContractStandardtType(anPageNum, anPageSize, anFlag)).toJson();
  	}
  	
	@Override
	public String webSaveEnableContractStandardTyp(Long id) {
		// TODO Auto-generated method stub
        return AjaxObject.newOk("启用合标准同类型成功！", contractStandardTypeService.saveEnableContractStandardTyp(id)).toJson();
	}

	@Override
	public String webQueryAllContractStandardType(Map<String, Object> anMap, int anPageNum, int anPageSize, String anFlag) {
		// TODO Auto-generated method stub
        return AjaxObject.newOkWithPage("启用合标准同类型成功！", contractStandardTypeService.queryAllContractStandardTyp(anMap, anPageNum, anPageSize, anFlag)).toJson();

	}

	@Override
	public String webSaveStopContractStandardTyp(Long id) {
		// TODO Auto-generated method stub
	     return AjaxObject.newOk("停用合标准同类型成功！", contractStandardTypeService.saveStopContractStandardTyp(id)).toJson();
		}


}

