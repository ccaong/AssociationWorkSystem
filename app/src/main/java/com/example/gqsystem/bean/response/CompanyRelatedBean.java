package com.example.gqsystem.bean.response;

/**
 * @author : devel
 * @date : 2020/3/23 8:42
 * @desc :
 */
public class CompanyRelatedBean {

    /**
     * id : 15832206458030
     * createBy : developer002
     * createTime : 2020-03-09 13:42:35
     * updateBy : null
     * updateTime : null
     * sysOrgCode : A01A05
     * relationComName : 2
     * relationComType : 4
     * companyId : 1
     * relationComResult :
     */

    private String id;
    private String createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private String sysOrgCode;
    private String relationComName;
    private String relationComType;
    private int companyId;
    private String relationComResult;

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

    public String getRelationComName() {
        return relationComName;
    }

    public void setRelationComName(String relationComName) {
        this.relationComName = relationComName;
    }

    public String getRelationComType() {
        return relationComType;
    }

    public void setRelationComType(String relationComType) {
        this.relationComType = relationComType;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getRelationComResult() {
        return relationComResult;
    }

    public void setRelationComResult(String relationComResult) {
        this.relationComResult = relationComResult;
    }
}
