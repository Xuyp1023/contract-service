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
import com.betterjr.modules.contract.dao.ContractSignerAccountMapper;
import com.betterjr.modules.contract.entity.ContractSignerAccount;

/**
 * @author liuwl
 *
 */
@Service
public class ContractSignerAccountService extends BaseService<ContractSignerAccountMapper, ContractSignerAccount> {

    /**
     *
     * @param anOperId
     * @return
     */
    public boolean checkSignerAccount(final Long anOperId) {

        return false;
    }

    /**
     *
     * @param anSignerAccount
     * @return
     */
    public ContractSignerAccount saveRegistSignerAccount(final ContractSignerAccount anSignerAccount) {

        return null;
    }

    /**
     * @param anSignerAccountId
     * @return
     */
    public ContractSignerAccount findSignerAccount(final Long anSignerAccountId) {
        return null;
    }
}
