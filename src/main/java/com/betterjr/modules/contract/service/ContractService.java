// Copyright (c) 2014-2017 Bytter. All rights reserved.
// ============================================================================
// CURRENT VERSION
// ============================================================================
// CHANGE LOG
// V2.3 : 2017年4月17日, liuwl, creation
// ============================================================================
package com.betterjr.modules.contract.service;

import org.springframework.stereotype.Service;

import com.betterjr.common.service.BaseService;
import com.betterjr.modules.contract.dao.ContractMapper;
import com.betterjr.modules.contract.entity.Contract;

/**
 * @author liuwl
 *
 */
@Service
public class ContractService extends BaseService<ContractMapper, Contract> {
    /**
     * 添加合同
     * @param anContract
     * @return
     */
    public Contract addContract(final Contract anContract) {
        return null;
    }

    /**
     * 修改合同
     * @param anContract
     * @return
     */
    public Contract saveContract(final Contract anContract) {
        return null;
    }

    /**
     * 保存合同签署人
     * @return
     */
    public boolean saveContractSigner() {
        return false;
    }

    /**
     * 保存签署位置
     * @return
     */
    public boolean saveContractStampPlace() {
        return false;
    }

    /**
     * 开始签署
     * @return
     */
    public boolean saveStartContractSign() {
        return false;
    }

    /**
     * 审核合同签署
     */
    public boolean saveAuditContractSign() {
        return false;
    }

    /**
     * 签署合同
     * @return
     */
    public boolean saveContractSign() {
        return false;
    }
}
