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

import org.springframework.stereotype.Service;

import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.UserUtils;
import com.betterjr.modules.account.entity.CustOperatorInfo;
import com.betterjr.modules.contract.dao.ContractTemplateLogMapper;
import com.betterjr.modules.contract.entity.ContractTemplateLog;

/**
 * @author liuwl
 *
 */
@Service
public class ContractTemplateLogService extends BaseService<ContractTemplateLogMapper, ContractTemplateLog> {
    /**
     * 获取模板的日志
     * @param anTemplateId
     * @return
     */
    public List<ContractTemplateLog> queryTemplateLog(final Long anTemplateId) {
        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("templateId", anTemplateId);

        return this.selectByProperty(conditionMap);
    }

    /**
     * 保存日志
     * @param anTemplateLog
     * @return
     */
    protected ContractTemplateLog saveAddTemplateLog(final Long anTemplateId, final Long anCustNo,
            final String anCustName, final String anOperType, final String anOperContent) {
        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        final ContractTemplateLog templateLog = new ContractTemplateLog();

        templateLog.setTemplateId(anTemplateId);
        templateLog.setCustNo(anCustNo);
        templateLog.setCustName(anCustName);
        templateLog.setOperType(anOperType); // 上传
        templateLog.setOperContent(anOperContent);
        templateLog.init(operator);

        final int result = this.insert(templateLog);

        BTAssert.isTrue(result == 1, "操作日志保存成功!");
        return templateLog;
    }
}
