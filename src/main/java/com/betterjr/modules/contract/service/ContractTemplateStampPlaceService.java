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
import com.betterjr.common.utils.Collections3;
import com.betterjr.common.utils.UserUtils;
import com.betterjr.modules.account.entity.CustOperatorInfo;
import com.betterjr.modules.contract.dao.ContractTemplateStampPlaceMapper;
import com.betterjr.modules.contract.entity.ContractTemplateStampPlace;

/**
 * @author liuwl
 *
 */
@Service
public class ContractTemplateStampPlaceService extends BaseService<ContractTemplateStampPlaceMapper, ContractTemplateStampPlace> {
    public static String[] signatorys = {"甲","乙","丙","丁","戊","己","庚","辛","壬","癸"};

    /**
     *
     * @param templateId
     * @param anSequence
     * @return
     */
    private ContractTemplateStampPlace findStampPlaceBySeq(final Long anTemplateId, final Long anSequence) {
        BTAssert.notNull(anTemplateId, "模板编号不允许为空！");
        BTAssert.notNull(anSequence, "签章位置序号不允许为空！");

        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("templateId", anTemplateId);
        conditionMap.put("sequence", anSequence);

        return Collections3.getFirst(this.selectByProperty(conditionMap));
    }

    /**
     * 保存印章位置
     * @param ContractTemplateId
     * @param anStampPlace
     * @return
     */
    protected void saveKeyWordStampPlace(final Long anTemplateId, final String anTemplateName, final Long anSequence, final String anKeyWord, final Long anAxisX, final Long anAxisY) {
        final ContractTemplateStampPlace stampPlace = findStampPlaceBySeq(anTemplateId, anSequence);

        if (stampPlace == null) {
            saveInitStampPlace(anTemplateId, anTemplateName, anSequence, anKeyWord, anAxisX, anAxisY);
        }
        else {
            stampPlace.setKeyWord(anKeyWord);

            stampPlace.setAxisX(anAxisX);
            stampPlace.setAxisY(anAxisY);

            stampPlace.modify(UserUtils.getOperatorInfo());

            final int result = this.updateByPrimaryKeySelective(stampPlace);
            BTAssert.isTrue(result == 1, "设置印章位置失败！");
        }
    }

    /**
     * 删除印章位置
     * @param ContractTemplateId
     * @param anStampPlaceId
     * @return
     */
    private void saveInitStampPlace(final Long anTemplateId, final String anTemplateName, final Long anIndex, final String anKeyWord, final Long anAxisX, final Long anAxisY) {
        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        final ContractTemplateStampPlace stampPlace = new ContractTemplateStampPlace();

        stampPlace.setTemplateId(anTemplateId);
        stampPlace.setTemplateName(anTemplateName);
        stampPlace.setSignatory(signatorys[anIndex.intValue()]);
        stampPlace.setSequence(anIndex);
        stampPlace.setKeyWord(anKeyWord);
        stampPlace.setAxisX(anAxisX);
        stampPlace.setAxisY(anAxisY);
        stampPlace.init(operator);

        final int result = this.insert(stampPlace);
        BTAssert.isTrue(result == 1, "初始化印章位置失败！");
    }

    /**
     * 获取模板 印章 位置列表
     * @param ContractTemplateId
     * @return
     */
    protected List<ContractTemplateStampPlace> queryStampPlace(final Long anTemplateId) {
        BTAssert.notNull(anTemplateId, "模板编号不允许为空！");

        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("templateId", anTemplateId);

        return this.selectByProperty(conditionMap, "sequence");
    }
}
