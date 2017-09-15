package com.betterjr.modules;

import java.net.URL;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

import com.betterjr.modules.contract.entity.ContractCorpAccount;
import com.betterjr.modules.contract.entity.ContractSignerAccount;
import com.betterjr.modules.contract.service.ContractCorpAccountService;
import com.betterjr.modules.contract.service.ContractSignerAccountService;
import com.betterjr.modules.contract.service.EsignFactory;

public class Provider {

    public static void main(final String[] args) throws Exception {
        final URL url = Provider.class.getClassLoader().getSystemResource("log4j.properties");
        System.out.println(url.toString());
        Log4jConfigurer.initLogging(url.getFile());
        final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "spring-context-contract-dubbo-provider.xml" });
        context.start();
        // testMOdifyCorpAccount(context);
        System.out.println("Provider working");
        System.in.read();
        context.close();
        System.exit(0);
    }

    public static void testSignPDF() {

    }

    public static void testRegistAccount(final ClassPathXmlApplicationContext anContext) {
        final ContractSignerAccountService accountService = anContext.getBean(ContractSignerAccountService.class);
        final ContractSignerAccount corpAccount = new ContractSignerAccount();
        corpAccount.setIdentNo("150924198008172050");
        corpAccount.setMobileNo("13828796911");
        corpAccount.setOperName("孔彦博");
        corpAccount.setOperId(1000704L);
        accountService.saveRegistSignerAccount(corpAccount);
    }

    public static void testRegistCorpAccount(final ClassPathXmlApplicationContext anContext) {
        final ContractCorpAccountService accountService = anContext.getBean(ContractCorpAccountService.class);
        final ContractCorpAccount corpAccount = new ContractCorpAccount();
        corpAccount.setCustNo(102209578L);
        corpAccount.setIdentNo("15092419810612487X");
        corpAccount.setMobileNo("13828796910");
        corpAccount.setName("孙俊伟");
        corpAccount.setCustName("中山市华莱登卫浴有限公司");
        corpAccount.setOrgCode("76414182-3");
        corpAccount.setType("1");

        accountService.saveRegistCorpAccount(corpAccount);
    }

    public static void testMOdifyCorpAccount(final ClassPathXmlApplicationContext anContext) {
        final EsignFactory accountService = anContext.getBean(EsignFactory.class);
        ContractCorpAccount corpAccount = new ContractCorpAccount();
        corpAccount.setAccount("E629EEEE6A6A400181E313052430FCED");
        corpAccount.setCustNo(102209578L);
        corpAccount.setIdentNo("15092419810612487X");
        corpAccount.setMobileNo("13828796910");
        corpAccount.setName("孙俊伟");
        corpAccount.setCustName("中山市华莱登卫浴有限公司");
        corpAccount.setOrgCode("76414182-3");
        corpAccount.setType("1");

        corpAccount = accountService.modifyCorpAccount(corpAccount);
        System.out.println(corpAccount);
    }

}
