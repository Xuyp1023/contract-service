package com.betterjr.modules.contract.dubbo;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.betterjr.modules.contract.IEsignSingerService;
import com.betterjr.modules.contract.data.ContractStubData;
import com.betterjr.modules.contract.service.ContractCorpAccountService;
import com.betterjr.modules.contract.service.ContractSignerAccountService;
import com.betterjr.modules.contract.service.ContractStamperService;
import com.betterjr.modules.contract.service.EsignFactory;

@Service(interfaceClass = IEsignSingerService.class)
public class EsignSingerDubboService implements IEsignSingerService {

    @Autowired
    private EsignFactory signService;

    @Autowired
    private ContractCorpAccountService corpAccountService;

    @Autowired
    private ContractSignerAccountService signerAccountService;

    @Autowired
    private ContractStamperService stamperService;

    @Override
    public boolean sendSMS(final Long anCustNo, final Boolean anPerson) {

        final String tmpAccountId = findAccountId(anCustNo, anPerson);
        return signService.sendSMS(tmpAccountId);
    }

    private String findAccountId(final Long anCustNo, final Boolean anPerson) {
        if (anPerson) {
            return signerAccountService.findSignAccountId(anCustNo, null);
        }
        else {
            return corpAccountService.findSignAccountId(anCustNo, null);
        }
    }

    @Override
    public ContractStubData signData(final Long anCustNo, final ContractStubData anStub, final byte[] anData, final String anVcode,
            final Boolean anPerson) {
        final String tmpAccountId = findAccountId(anCustNo, anPerson);
        final String tmpStamperData = stamperService.findMakerStamper(anCustNo, anPerson.booleanValue() ? 1 : 0);
        return signService.signData(tmpAccountId, tmpStamperData, anStub, anData, anVcode);
    }
}