package com.betterjr.modules;

import java.io.FileNotFoundException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Log4jConfigurer;

public abstract class BasicServiceTest<T> {

    private static ClassPathXmlApplicationContext ctx;

    @BeforeClass
    public static void startSpring() {

        try {
            final URL url = BasicServiceTest.class.getClassLoader().getSystemResource("log4j-test.properties");
            System.out.println(url.toString());
            Log4jConfigurer.initLogging(url.getFile());
        }
        catch (final FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        final String[] configFiles = new String[] { "spring-context-workflow-dubbo-provider-test.xml" };
        ctx = new ClassPathXmlApplicationContext(configFiles);

        ctx.start();
    }

    @AfterClass
    public static void closeSpring() {
        if (ctx != null) {
            ctx.close();
        }
    }

    public T getServiceObject() {
        final T service = this.getCtx().getBean(this.getTargetServiceClass());
        return service;
    }

    public abstract Class<T> getTargetServiceClass();

    public ClassPathXmlApplicationContext getCtx() {
        return ctx;
    }

}
