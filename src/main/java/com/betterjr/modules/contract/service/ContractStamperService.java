// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.3 : 2017年4月17日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.service;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.betterjr.common.exception.BytterException;
import com.betterjr.common.service.BaseService;
import com.betterjr.common.utils.BTAssert;
import com.betterjr.common.utils.Base64Coder;
import com.betterjr.common.utils.BetterStringUtils;
import com.betterjr.common.utils.Collections3;
import com.betterjr.common.utils.QueryTermBuilder;
import com.betterjr.common.utils.UserUtils;
import com.betterjr.mapper.pagehelper.Page;
import com.betterjr.modules.account.entity.CustInfo;
import com.betterjr.modules.account.entity.CustOperatorInfo;
import com.betterjr.modules.account.service.CustAccountService;
import com.betterjr.modules.contract.dao.ContractStamperMapper;
import com.betterjr.modules.contract.entity.ContractStamper;
import com.betterjr.modules.document.ICustFileService;
import com.betterjr.modules.document.entity.CustFileItem;
import com.betterjr.modules.document.service.DataStoreService;

/**
 * @author liuwl
 *
 */
@Service
public class ContractStamperService extends BaseService<ContractStamperMapper, ContractStamper> {

    @Reference(interfaceClass = ICustFileService.class)
    private ICustFileService custFileService;

//    @Reference(interfaceClass = ICustMechBaseService.class)
//    private ICustMechBaseService custMechBaseService;
    
    @Autowired
    private CustAccountService custAccountService;    

    @Resource
    private DataStoreService dataStoreService;

    /**
     * 获取当前机构印章(所有状态)
     * 
     * @param anCustNo
     *
     * @return
     */
    public Page<ContractStamper> queryOwnStamper(final Long anCustNo, final int anFlag, final int anPageNum, final int anPageSize) {
        final String operOrg = UserUtils.getOperOrg();

        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("operOrg", operOrg);
        if (anCustNo != null) {
            conditionMap.put("custNo", anCustNo);
        }

        return this.selectPropertyByPage(conditionMap, anPageNum, anPageSize, anFlag == 1);
    }

    /**
     * 检查 公司是否已经上传过印章
     *
     * @param anCustNo
     * @return
     */
    public boolean findCheckStamper(final Long anCustNo) {
        final Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("custNo", anCustNo);

        return this.selectByProperty(conditionMap).size() == 1;
    }

    /**
     * 获取印章
     *
     * @param anId
     * @return
     */
    public ContractStamper findStamper(final Long anId) {
        BTAssert.notNull(anId, "编号不允许为空！");

        final ContractStamper contractStamper = this.selectByPrimaryKey(anId);

        BTAssert.notNull(contractStamper, "没有找到印章信息！");

        if (UserUtils.platformUser() || BetterStringUtils.equals(contractStamper.getOperOrg(), UserUtils.getOperOrg())) {
            if (contractStamper.getOriginStamper() != null) {
                final CustFileItem originStamper = custFileService.findOneByBatchNo(contractStamper.getOriginStamper());
                if (originStamper != null) {
                    contractStamper.setOriginStamperId(originStamper.getId());
                    contractStamper.setOriginStamperName(originStamper.getFileName());
                }
            }

            if (contractStamper.getStamper() != null) {
                final CustFileItem stamper = custFileService.findOneByBatchNo(contractStamper.getStamper());
                if (stamper != null) {
                    contractStamper.setStamperId(stamper.getId());
                    contractStamper.setStamperName(stamper.getFileName());
                }
            }
            return contractStamper;
        }

        throw new BytterException("操作失败!");
    }

    /**
     * 添加印章 用户
     *
     * @param anContractStamper
     * @return
     */
    public ContractStamper saveAddOwnStamper(final ContractStamper anContractStamper, final String anOrginFileId) {
        BTAssert.notNull(anContractStamper, "印章数据不允许为空！");
        BTAssert.isTrue(BetterStringUtils.isNotBlank(anOrginFileId), "原始印章文件不允许为空！");

//        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anContractStamper.getCustNo());
        
        final CustInfo custInfo = this.custAccountService.findCustInfo(anContractStamper.getCustNo());        
        BTAssert.notNull(custInfo, "没有找到公司信息！");

        final boolean checkResult = this.findCheckStamper(anContractStamper.getCustNo());
        BTAssert.isTrue(checkResult == false, "此公司已经上传过印章！");

        anContractStamper.setOperOrg(custInfo.getOperOrg());
        anContractStamper.setCustName(custInfo.getCustName());
        anContractStamper.setCustType(custInfo.getCustType());

        final Long batchNo = custFileService.updateCustFileItemInfo(anOrginFileId, null);
        anContractStamper.setOriginStamper(batchNo);

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();
        BTAssert.isTrue(BetterStringUtils.equals(operator.getOperOrg(), custInfo.getOperOrg()), "操作失败！");

        anContractStamper.setBusinStatus("00"); // 已上传待制作状态
        anContractStamper.init(operator);

        final int result = this.insert(anContractStamper);
        BTAssert.isTrue(result == 1, "原始印章创建失败！");

        return anContractStamper;
    }

    /**
     * 添加印章 平台
     *
     * @param anContractStamper
     * @return
     */
    public ContractStamper saveAddStamper(final ContractStamper anContractStamper, final String anOrginFileId, final String anFileId) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败!");
        BTAssert.notNull(anContractStamper, "印章数据不允许为空！");
        BTAssert.notNull(anFileId, "印章文件不允许为空！");

//        final CustMechBase custMechBase = custMechBaseService.findBaseInfo(anContractStamper.getCustNo());

        final CustInfo custInfo = this.custAccountService.findCustInfo(anContractStamper.getCustNo());        
        BTAssert.notNull(custInfo, "没有找到公司信息！");

        final boolean checkResult = this.findCheckStamper(anContractStamper.getCustNo());
        BTAssert.isTrue(checkResult == false, "此公司已经上传过印章！");

        // 这里需要根据 所选择公司添加 operOrg
        anContractStamper.setOperOrg(custInfo.getOperOrg());
        anContractStamper.setCustName(custInfo.getCustName());
        anContractStamper.setCustType(custInfo.getCustType());

        anContractStamper.setOriginStamper(custFileService.updateCustFileItemInfo(anOrginFileId, null));

        // dataStoreService
        final BufferedInputStream inputStream = new BufferedInputStream(dataStoreService.loadFromStore(Long.valueOf(anFileId)));

        final int mb2 = 2 * 1024 * 1024;
        final byte[] picBytes = new byte[mb2];

        int count = 0;
        try {
            count = inputStream.read(picBytes, 0, mb2);
            if (count == mb2) {
                throw new BytterException("印章文件需要小于2MB!");
            }
        }
        catch (final IOException e) {
            throw new BytterException("印章文件读取失败!");
        }
        final String data = new String(Base64Coder.encode(picBytes, 0, count));

        anContractStamper.setStamperData(data);
        anContractStamper.setStamper(custFileService.updateCustFileItemInfo(anFileId, null));

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();

        anContractStamper.setBusinStatus("01"); // 已制作状态
        anContractStamper.init(operator);

        final int result = this.insert(anContractStamper);
        BTAssert.isTrue(result == 1, "印章创建失败！");

        return anContractStamper;
    }

    /**
     * 制作印章
     *
     * @return
     */
    public ContractStamper saveMakeStamper(final Long anId, final String anFileId) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败!");
        final ContractStamper contractStamper = this.selectByPrimaryKey(anId);

        BTAssert.notNull(contractStamper, "未找到该印章");

        final BufferedInputStream inputStream = new BufferedInputStream(dataStoreService.loadFromStore(Long.valueOf(anFileId)));
        final int mb2 = 2 * 1024 * 1024;
        final byte[] picBytes = new byte[mb2];

        int count = 0;
        try {
            count = inputStream.read(picBytes, 0, mb2);
            if (count == mb2) {
                throw new BytterException("印章文件需要小于2MB!");
            }
        }
        catch (final IOException e) {
            throw new BytterException("印章文件读取失败!");
        }

        final String data = new String(Base64Coder.encode(picBytes, 0, count));

        // 保存 印章文件
        final Long batchNo = custFileService.updateCustFileItemInfo(anFileId, null);

        contractStamper.setStamperData(data);
        contractStamper.setStamper(batchNo);

        final CustOperatorInfo operator = UserUtils.getOperatorInfo();

        contractStamper.setBusinStatus("01"); // 已制作状态
        contractStamper.modify(operator);

        final int result = this.updateByPrimaryKeySelective(contractStamper);
        BTAssert.isTrue(result == 1, "印章制作失败！");

        return contractStamper;
    }

    /**
     * 平台获取所有的印章
     * 
     * @param anPageSize
     * @param anPageNum
     * @param anFlag
     * @param anParam
     *
     * @return
     */
    public Page<ContractStamper> queryAllStamper(final Map<String, Object> anParam, final int anFlag, final int anPageNum, final int anPageSize) {
        BTAssert.isTrue(UserUtils.platformUser(), "操作失败!");

        if (BetterStringUtils.isBlank((String) anParam.get("LIKEcustName"))) {
            anParam.remove("LIKEcustName");
        }
        else {
            anParam.put("LIKEcustName", "%" + (String) anParam.get("LIKEcustName") + "%");
        }
        if (BetterStringUtils.isBlank((String) anParam.get("businStatus"))) {
            anParam.remove("businStatus");
        }

        return this.selectPropertyByPage(anParam, anPageNum, anPageSize, anFlag == 1);
    }

    /**
     * 查找已经制作好的印章数据，具体条件是客户编号
     * 
     * @param anCustNo
     *            机构客户编号或操作员编号
     * @param anCustType
     *            机构客户
     * @return
     */
    public String findMakerStamper(final Long anCustNo, final int anCustType) {
        final Map<String, Object> queryTerm = QueryTermBuilder.newInstance().put("custNo", anCustNo).put("custType", anCustType)
                .put("businStatus", "01").build();
        final List<ContractStamper> tmpList = this.selectByProperty(queryTerm);
        if (Collections3.isEmpty(tmpList)) {
            return "";
        }
        else {
            return Collections3.getFirst(tmpList).getStamperData();
        }
    }
}
