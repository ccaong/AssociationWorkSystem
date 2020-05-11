package com.example.gqsystem.bean.response;

/**
 * @author : devel
 * @date : 2020/4/1 16:39
 * @desc : 合同款项
 */
public class ProjectContractPaymentBean {

    /**
     * id : 15857300801960
     * createBy : admin
     * createTime : 2020-04-01 16:35:13
     * updateBy : null
     * updateTime : null
     * sysOrgCode : A01
     * payAmount : 2000
     * payCondition : 付款条件数据
     * planBackTime : 2020-04-02
     * realityBackTime : 2020-04-01
     * invoiceInfo : 1
     * proId : 1245239905789153282
     * remark : 备注信息
     */

    private String id;
    private String createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private String sysOrgCode;
    private int payAmount;
    private String payCondition;
    private String planBackTime;
    private String realityBackTime;
    private String invoiceInfo;
    private String proId;
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

    public String getPayAmount() {
        return payAmount+"元";
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayCondition() {
        return payCondition;
    }

    public void setPayCondition(String payCondition) {
        this.payCondition = payCondition;
    }

    public String getPlanBackTime() {
        return planBackTime;
    }

    public void setPlanBackTime(String planBackTime) {
        this.planBackTime = planBackTime;
    }

    public String getRealityBackTime() {
        return realityBackTime;
    }

    public void setRealityBackTime(String realityBackTime) {
        this.realityBackTime = realityBackTime;
    }

    public String getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(String invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
