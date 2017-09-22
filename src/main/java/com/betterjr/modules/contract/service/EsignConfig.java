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

    @Value("${esign.sign.privateKey}")
    private String privateKey;

    @Value("${esign.sign.publicKey}")
    private String publicKey;

    @Value("${esign.seal.templateOrgType}")
    private String sealtempOrgType;

    @Value("${esign.seal.templatePersonType}")
    private String sealPersonTempType;

    @Value("${esign.seal.color}")
    private String sealColor;

    @Value("${esign.seal.hText}")
    private String sealHText;

    @Value("${esign.seal.qText}")
    private String sealQText;

    @Value("${esign.sign.validcode}")
    private boolean validCode = true;

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

    public String getSealtempOrgType() {
        return this.sealtempOrgType;
    }

    public void setSealtempOrgType(final String anSealtempOrgType) {
        this.sealtempOrgType = anSealtempOrgType;
    }

    public String getSealPersonTempType() {
        return this.sealPersonTempType;
    }

    public void setSealPersonTempType(final String anSealPersonTempType) {
        this.sealPersonTempType = anSealPersonTempType;
    }

    public String getSealColor() {
        return this.sealColor;
    }

    public void setSealColor(final String anSealColor) {
        this.sealColor = anSealColor;
    }

    public String getSealHText() {
        return this.sealHText;
    }

    public void setSealHText(final String anSealHText) {
        this.sealHText = anSealHText;
    }

    public String getSealQText() {
        return this.sealQText;
    }

    public void setSealQText(final String anSealQText) {
        this.sealQText = anSealQText;
    }

    public boolean isValidCode() {
        return this.validCode;
    }

    public void setValidCode(final boolean anValidCode) {
        this.validCode = anValidCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
