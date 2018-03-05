package com.yzb.lee.springsession.domain;

import java.math.BigDecimal;

public class ConsumerLoanInfo {
    /**
     * 合同号
     */
    private String appNo;
    /**
     * 借款金额
     */
    private BigDecimal contractLmt;
    /**
     * 借款利率
     */
    private BigDecimal interestRate;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品编号
     */
    private String productCd;
    /**
     * 放款金额
     */
    private BigDecimal orderLmtAmt;
    /**
     * 银行编号
     */
    private String bankCode;
    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行账户
     */
    private String bankCardNo;
    /**
     * 银行预留号码
     */
    private String bankCellPhone;
    /**
     * 银行卡持卡人姓名
     */
    private String bankCustName;
    /**
     * 银行卡持卡人身份证
     */
    private String bankCustId;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手机号
     */
    private String cellPhone;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 贷款期数
     */
    private int loanTerm;
    /**
     * 业务归属省份
     */
    private String affProvince;
    /**
     * 业务归属城市
     */
    private String affCity;

    public String getAppNo() {
        return appNo;
    }

    public void setAppNo(String appNo) {
        this.appNo = appNo;
    }

    public BigDecimal getContractLmt() {
        return contractLmt;
    }

    public void setContractLmt(BigDecimal contractLmt) {
        this.contractLmt = contractLmt;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCd() {
        return productCd;
    }

    public void setProductCd(String productCd) {
        this.productCd = productCd;
    }

    public BigDecimal getOrderLmtAmt() {
        return orderLmtAmt;
    }

    public void setOrderLmtAmt(BigDecimal orderLmtAmt) {
        this.orderLmtAmt = orderLmtAmt;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCellPhone() {
        return bankCellPhone;
    }

    public void setBankCellPhone(String bankCellPhone) {
        this.bankCellPhone = bankCellPhone;
    }

    public String getBankCustName() {
        return bankCustName;
    }

    public void setBankCustName(String bankCustName) {
        this.bankCustName = bankCustName;
    }

    public String getBankCustId() {
        return bankCustId;
    }

    public void setBankCustId(String bankCustId) {
        this.bankCustId = bankCustId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public String getAffProvince() {
        return affProvince;
    }

    public void setAffProvince(String affProvince) {
        this.affProvince = affProvince;
    }

    public String getAffCity() {
        return affCity;
    }

    public void setAffCity(String affCity) {
        this.affCity = affCity;
    }
}