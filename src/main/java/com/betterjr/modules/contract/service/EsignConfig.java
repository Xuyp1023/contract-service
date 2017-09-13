package com.betterjr.modules.contract.service;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("EsignConfig")
public class EsignConfig {

    @Value("${esign.project.projectId}")
    private String projectId;

    @Value("${esign.project.projectSecret}")
    private String projectSecret;

    @Value("${esign.project.apisUrl}")
    private String apisUrl;

    @Value("${esign.http.httpType}")
    private String httpType;

    @Value("${esign.http.retry}")
    private Integer retry;

    @Value("${esign.sign.algorithm}")
    private String algorithm;

    private String privateKey;

    private String publicKey;

    public String getProjectId() {
        return this.projectId;
    }

    public void setProjectId(final String anProjectId) {
        this.projectId = anProjectId;
    }

    public String getProjectSecret() {
        return this.projectSecret;
    }

    public void setProjectSecret(final String anProjectSecret) {
        this.projectSecret = anProjectSecret;
    }

    public String getApisUrl() {
        return this.apisUrl;
    }

    public void setApisUrl(final String anApisUrl) {
        this.apisUrl = anApisUrl;
    }

    public String getHttpType() {
        return this.httpType;
    }

    public void setHttpType(final String anHttpType) {
        this.httpType = anHttpType;
    }

    public Integer getRetry() {
        return this.retry;
    }

    public void setRetry(final Integer anRetry) {
        this.retry = anRetry;
    }

    public String getAlgorithm() {
        return this.algorithm;
    }

    public void setAlgorithm(final String anAlgorithm) {
        this.algorithm = anAlgorithm;
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(final String anPrivateKey) {
        this.privateKey = anPrivateKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(final String anPublicKey) {
        this.publicKey = anPublicKey;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
