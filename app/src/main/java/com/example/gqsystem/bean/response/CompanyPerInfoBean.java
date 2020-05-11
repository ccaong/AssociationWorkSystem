package com.example.gqsystem.bean.response;

import java.io.Serializable;

/**
 * @author : devel
 * @date : 2020/4/10 13:47
 * @desc :
 */
public class CompanyPerInfoBean implements Serializable {

    /**
     * id : 15864949117660
     * createBy : admin
     * createTime : 2020-04-10 13:03:38
     * updateBy : null
     * updateTime : null
     * sysOrgCode : A01
     * comUserName : 柏淑婷
     * comUserPhone : 18753115356
     * comUserEamil :
     * companyId : 12
     * comUserStatus : null
     * comUserResult :
     */

    private String id;
    private String createBy;
    private String createTime;
    private Object updateBy;
    private Object updateTime;
    private String sysOrgCode;
    private String comUserName;
    private String comUserPhone;
    private String comUserEamil;
    private int companyId;
    private Object comUserStatus;
    private String comUserResult;

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

    public String getComUserName() {
        return comUserName;
    }

    public void setComUserName(String comUserName) {
        this.comUserName = comUserName;
    }

    public String getComUserPhone() {
        return comUserPhone;
    }

    public void setComUserPhone(String comUserPhone) {
        this.comUserPhone = comUserPhone;
    }

    public String getComUserEamil() {
        return comUserEamil;
    }

    public void setComUserEamil(String comUserEamil) {
        this.comUserEamil = comUserEamil;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Object getComUserStatus() {
        return comUserStatus;
    }

    public void setComUserStatus(Object comUserStatus) {
        this.comUserStatus = comUserStatus;
    }

    public String getComUserResult() {
        return comUserResult;
    }

    public void setComUserResult(String comUserResult) {
        this.comUserResult = comUserResult;
    }
}
