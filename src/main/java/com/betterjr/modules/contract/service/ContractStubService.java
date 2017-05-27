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
import com.betterjr.modules.contract.dao.ContractStubMapper;
import com.betterjr.modules.contract.entity.ContractStub;

/**
 * @author liuwl
 *
 */
@Service
public class ContractStubService extends BaseService<ContractStubMapper, ContractStub> {
    /**
     * 添加合同存根
     * @param anContractId
     * @param anContractStub
     * @return
     */
    public ContractStub addContractStub(final Long anContractId, final ContractStub anContractStub) {

        return null;
    }

    /**
     *
     * @return
     */
    public ContractStub saveContractStub() {

        return null;
    }
}
