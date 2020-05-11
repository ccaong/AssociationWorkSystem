package com.example.gqsystem.bean.response;

/**
 * @author : devel
 * @date : 2020/4/2 9:06
 * @desc : 成本核算
 */
public class ProjectCostAccountBean {

    /**
     * id : 1245517715825012737
     * createBy : admin
     * createTime : 2020-04-02 09:05:39
     * updateBy : null
     * updateTime : null
     * sysOrgCode : A01
     * carFare : 2000
     * hotelExpense : 1000
     * subsidy : 1000
     * expertFee : 10000
     * expertMealFee : 1
     * printingFee : 20
     * restsFee : 0.2
     * feeSum : 1000000
     * accessory : temp/账号_1585789510348.txt
     * remark : 成本核算备注
     * proId : 1245239905789153282
     */

    private String id;
    private String createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private String sysOrgCode;
    private int carFare;
    private int hotelExpense;
    private int subsidy;
    private int expertFee;
    private int expertMealFee;
    private int printingFee;
    private int restsFee;
    private int feeSum;
    private String accessory;
    private String remark;
    private String proId;

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

    public int getCarFare() {
        return carFare;
    }

    public void setCarFare(int carFare) {
        this.carFare = carFare;
    }

    public int getHotelExpense() {
        return hotelExpense;
    }

    public void setHotelExpense(int hotelExpense) {
        this.hotelExpense = hotelExpense;
    }

    public int getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(int subsidy) {
        this.subsidy = subsidy;
    }

    public int getExpertFee() {
        return expertFee;
    }

    public void setExpertFee(int expertFee) {
        this.expertFee = expertFee;
    }

    public int getExpertMealFee() {
        return expertMealFee;
    }

    public void setExpertMealFee(int expertMealFee) {
        this.expertMealFee = expertMealFee;
    }

    public int getPrintingFee() {
        return printingFee;
    }

    public void setPrintingFee(int printingFee) {
        this.printingFee = printingFee;
    }

    public int getRestsFee() {
        return restsFee;
    }

    public void setRestsFee(int restsFee) {
        this.restsFee = restsFee;
    }

    public int getFeeSum() {
        return feeSum;
    }

    public void setFeeSum(int feeSum) {
        this.feeSum = feeSum;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}
