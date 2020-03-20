package com.example.gqsystem.bean.response;

import java.io.Serializable;

/**
 * @author : devel
 * @date : 2020/3/6 11:05
 * @desc : 公司开票信息
 */
public class InvoiceInfoBean implements Serializable {

    /**
     * id : 1234743061137969154
     * createBy : developer002
     * createTime : 2020-03-03 15:31:01
     * updateBy : null
     * updateTime : null
     * sysOrgCode : A01A05
     * comInvoiceType : 2
     * comAccountName : 山东得益乳业股份有限公司
     * comInvoiceBill : 91370300267105370H
     * comInvoiceAddress : 淄博开发区裕民路135号
     * comInvoicePhone : 0533-3915879
     * comBank : 齐商银行柳泉路支行
     * comBankAccount : 034110001301018
     * comanyId : 1
     */

    private String id;
    private String createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private String sysOrgCode;
    private String comInvoiceType;
    private String comAccountName;
    private String comInvoiceBill;
    private String comInvoiceAddress;
    private String comInvoicePhone;
    private String comBank;
    private String comBankAccount;
    private int comanyId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    public String getSysOrgCode() {
        return sysOrgCode;
    }

    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode;
    }

    public String getComInvoiceType() {
        return comInvoiceType;
    }

    public void setComInvoiceType(String comInvoiceType) {
        this.comInvoiceType = comInvoiceType;
    }

    public String getComAccountName() {
        return comAccountName;
    }

    public void setComAccountName(String comAccountName) {
        this.comAccountName = comAccountName;
    }

    public String getComInvoiceBill() {
        return comInvoiceBill;
    }

    public void setComInvoiceBill(String comInvoiceBill) {
        this.comInvoiceBill = comInvoiceBill;
    }

    public String getComInvoiceAddress() {
        return comInvoiceAddress;
    }

    public void setComInvoiceAddress(String comInvoiceAddress) {
        this.comInvoiceAddress = comInvoiceAddress;
    }

    public String getComInvoicePhone() {
        return comInvoicePhone;
    }

    public void setComInvoicePhone(String comInvoicePhone) {
        this.comInvoicePhone = comInvoicePhone;
    }

    public String getComBank() {
        return comBank;
    }

    public void setComBank(String comBank) {
        this.comBank = comBank;
    }

    public String getComBankAccount() {
        return comBankAccount;
    }

    public void setComBankAccount(String comBankAccount) {
        this.comBankAccount = comBankAccount;
    }

    public int getComanyId() {
        return comanyId;
    }

    public void setComanyId(int comanyId) {
        this.comanyId = comanyId;
    }
}
