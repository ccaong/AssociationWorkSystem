package com.example.gqsystem.bean.response;

/**
 * @author : devel
 * @date : 2020/4/1 16:37
 * @desc : 合同信息
 */
public class ProjectContractInfoBean {

    /**
     * id : 1244917623996821505
     * createBy : admin
     * createTime : 2020-03-31 17:21:06
     * updateBy : null
     * updateTime : null
     * sysOrgCode : A01
     * proId : 1244917623480922113
     * conTime : 2020-03-31
     * conAmount : 1000
     * paymentNum : 1
     * conAccessory : temp/账号_1585646462433.txt
     * remark : 没有备注
     */

    private String id;
    private String createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private String sysOrgCode;
    private String proId;
    private String conTime;
    private int conAmount;
    private String paymentNum;
    private String conAccessory;
    private String remark;

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

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getConTime() {
        return conTime;
    }

    public void setConTime(String conTime) {
        this.conTime = conTime;
    }

    public String getConAmount() {
        return conAmount + "";
    }

    public void setConAmount(int conAmount) {
        this.conAmount = conAmount;
    }

    public String getPaymentNum() {
        return paymentNum;
    }

    public void setPaymentNum(String paymentNum) {
        this.paymentNum = paymentNum;
    }

    public String getConAccessory() {
        return conAccessory;
    }

    public void setConAccessory(String conAccessory) {
        this.conAccessory = conAccessory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
