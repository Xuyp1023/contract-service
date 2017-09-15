package com.betterjr.modules.contract.service;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.betterjr.common.exception.BytterDeclareException;
import com.betterjr.modules.contract.data.ContractStubData;
import com.betterjr.modules.contract.entity.ContractCorpAccount;
import com.betterjr.modules.contract.entity.ContractSignerAccount;
import com.timevale.esign.sdk.tech.bean.OrganizeBean;
import com.timevale.esign.sdk.tech.bean.PersonBean;
import com.timevale.esign.sdk.tech.bean.PosBean;
import com.timevale.esign.sdk.tech.bean.SignPDFStreamBean;
import com.timevale.esign.sdk.tech.bean.UpdateOrganizeBean;
import com.timevale.esign.sdk.tech.bean.result.AddAccountResult;
import com.timevale.esign.sdk.tech.bean.result.FileDigestSignResult;
import com.timevale.esign.sdk.tech.bean.result.Result;
import com.timevale.esign.sdk.tech.impl.constants.OrganRegType;
import com.timevale.esign.sdk.tech.impl.constants.SignType;
import com.timevale.esign.sdk.tech.service.AccountService;
import com.timevale.esign.sdk.tech.service.EsignsdkService;
import com.timevale.esign.sdk.tech.service.MobileService;
import com.timevale.esign.sdk.tech.service.UserSignService;
import com.timevale.esign.sdk.tech.service.factory.AccountServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.EsignsdkServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.MobileServiceFactory;
import com.timevale.esign.sdk.tech.service.factory.UserSignServiceFactory;
import com.timevale.tech.sdk.bean.HttpConnectionConfig;
import com.timevale.tech.sdk.bean.ProjectConfig;
import com.timevale.tech.sdk.bean.SignatureConfig;
import com.timevale.tech.sdk.constants.AlgorithmType;
import com.timevale.tech.sdk.constants.HttpType;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EsignFactory {
    private final EsignsdkService SDK = EsignsdkServiceFactory.instance();
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private EsignConfig config;

    @PostConstruct
    public void init() {
        logger.info("Esign config :" + config);
        final ProjectConfig projectConfig = new ProjectConfig();
        projectConfig.setItsmApiUrl(config.getApisUrl());
        projectConfig.setProjectId(config.getProjectId());
        projectConfig.setProjectSecret(config.getProjectSecret());
        final HttpConnectionConfig httpConfig = new HttpConnectionConfig();
        if (HttpType.HTTP.type().equalsIgnoreCase(config.getHttpType())) {
            httpConfig.setHttpType(HttpType.HTTP);
        }
        httpConfig.setRetry(config.getRetry());
        final SignatureConfig signConfig = new SignatureConfig();
        if (AlgorithmType.RSA.type().equalsIgnoreCase(config.getAlgorithm())) {
            signConfig.setAlgorithm(AlgorithmType.RSA);
            signConfig.setPrivateKey(config.getPrivateKey());
            signConfig.setEsignPublicKey(config.getPublicKey());
        }

        final Result result = SDK.init(projectConfig, httpConfig, signConfig);
        if (result.getErrCode() == 0) {
            logger.info("init Esign success");
        }
        else {
            logger.error("init Esign has error ," + result.getMsg());
        }
    }

    // 注册电子合同签字人信息
    public ContractSignerAccount registPersonAccount(final ContractSignerAccount anSignerAccount) {
        final PersonBean psn = new PersonBean();
        psn.setName(anSignerAccount.getOperName());
        psn.setIdNo(anSignerAccount.getIdentNo());
        psn.setMobile(anSignerAccount.getMobileNo());
        psn.setPersonArea(0);
        final AccountService accountService = AccountServiceFactory.instance();
        final AddAccountResult result = accountService.addAccount(psn);
        if (result.getErrCode() == 0) {
            anSignerAccount.setAccount(result.getAccountId());
            anSignerAccount.setBusinStatus("1");
        }
        else {
            anSignerAccount.setBusinStatus("0");
        }
        return anSignerAccount;
    }

    // 更新企业在电子合同签署方的注册信息
    public ContractCorpAccount modifyCorpAccount(final ContractCorpAccount anCorpAccount) {
        final UpdateOrganizeBean org = new UpdateOrganizeBean();
        org.setName(anCorpAccount.getCustName());
        org.setMobile(anCorpAccount.getMobileNo());
        if ("1".equals(anCorpAccount.getType())) {
            org.setAgentIdNo(anCorpAccount.getIdentNo());
            org.setAgentName(anCorpAccount.getName());
        }
        else {
            org.setLegalIdNo(anCorpAccount.getIdentNo());
            org.setLegalName(anCorpAccount.getName());
        }
        final AccountService accountService = AccountServiceFactory.instance();
        final Result result = accountService.updateAccount(anCorpAccount.getAccount(), org, new ArrayList());
        if (result.getErrCode() == 0) {
            anCorpAccount.setBusinStatus("1");
        }
        else {
            anCorpAccount.setBusinStatus("0");
        }

        // 仅用于调试使用
        anCorpAccount.setSignerAccount(result.getMsg());
        return anCorpAccount;
    }

    // 注册电子合同签章企业信息
    public ContractCorpAccount registCorpAccount(final ContractCorpAccount anCorpAccount) {
        final OrganizeBean org = new OrganizeBean();
        org.setName(anCorpAccount.getCustName());
        org.setMobile(anCorpAccount.getMobileNo());
        org.setOrganCode(anCorpAccount.getOrgCode());
        org.setOrganType(0);
        org.setRegType(OrganRegType.NORMAL);
        org.setUserType(Integer.parseInt(anCorpAccount.getType()));
        if ("1".equals(anCorpAccount.getType())) {
            org.setAgentIdNo(anCorpAccount.getIdentNo());
            org.setAgentName(anCorpAccount.getName());
        }
        else {
            org.setLegalIdNo(anCorpAccount.getIdentNo());
            org.setLegalName(anCorpAccount.getName());
            org.setLegalArea(0);
        }
        final AccountService accountService = AccountServiceFactory.instance();
        final AddAccountResult result = accountService.addAccount(org);
        
        if (result.getErrCode() != 0) {
        	throw new java.lang.RuntimeException(result.getMsg());
        }
        
        //anCorpAccount.setSignerAccount(result.getMsg());
        anCorpAccount.setSignerAccount(result.getAccountId());
        
        return anCorpAccount;
    }

    public EsignConfig getConfig() {
        return this.config;
    }

    public void setConfig(final EsignConfig anConfig) {
        this.config = anConfig;
    }

    /**
     * 发送手机验证码
     * 
     * @param anAccountId
     *            在电子合同服务提供方的账户
     * @return
     */
    public boolean sendSMS(final String anAccountId) {
        final MobileService mobileFactory = MobileServiceFactory.instance();
        final Result duanxin = mobileFactory.sendSignMobileCode(anAccountId);
        final boolean isok = duanxin.getErrCode() == 0;
        if (!isok) {

            throw new BytterDeclareException("发送电子合同签署验证码出现异常，" + duanxin.getMsg());
        }
        return isok;
    }

    /**
     * 签署电子合同
     * 
     * @param anAccountId
     *            在电子合同服务提供方的账户
     * @param anStamperData
     *            印章图片信息，使用了BASE64编码
     * @param anStub
     *            签署方信息
     * @param anSourceFileName
     *            电子合同文件
     * @param anDestFileName
     *            签署后的电子合同文件
     * @return
     */
    public ContractStubData signData(final String anAccountId, final String anStamperData, final ContractStubData anStub, final byte[] anData,
            final String anVcode) {
        final UserSignService userSign = UserSignServiceFactory.instance();
        final PosBean pos = new PosBean();
        pos.setKey(anStub.getKeyWord());
        pos.setPosPage(anStub.getPagination());
        pos.setPosType(Integer.parseInt(anStub.getPositionType()));
        pos.setPosX(anStub.getAxisX());
        pos.setPosY(anStub.getAxisY());
        pos.setWidth(200);
        pos.setAddSignTime(true);
        final SignPDFStreamBean streamBean = new SignPDFStreamBean();
        streamBean.setStream(anData);

        final FileDigestSignResult signResult = userSign.localSignPDF(anAccountId, anStamperData, streamBean, pos, SignType.Single);
        final boolean isok = signResult.getErrCode() == 0;
        if (!isok) {
            throw new BytterDeclareException("签署电子合同出现异常，" + signResult.getMsg());
        }
        else {
            anStub.setResult(signResult.getStream());
            anStub.setBusinStatus("01");
            anStub.setSignServiceId(signResult.getSignServiceId());
        }
        return anStub;
    }
}
