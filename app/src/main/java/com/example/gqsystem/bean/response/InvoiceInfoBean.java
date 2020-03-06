package com.example.gqsystem.bean.response;

import java.io.Serializable;

/**
 * @author : devel
 * @date : 2020/3/6 11:05
 * @desc : 公司开票信息
 */
public class InvoiceInfoBean implements Serializable {

    private String companyName;

    private String type;

    private String account;

    private String bank;

    private String tax;

    private String tel;


    public InvoiceInfoBean(String companyName, String type, String account, String bank, String tax, String tel) {
        this.companyName = companyName;
        this.type = type;
        this.account = account;
        this.bank = bank;
        this.tax = tax;
        this.tel = tel;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
