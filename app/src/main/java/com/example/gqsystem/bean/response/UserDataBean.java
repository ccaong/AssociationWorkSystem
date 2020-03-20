package com.example.gqsystem.bean.response;

import java.util.List;

/**
 * @author : devel
 * @date : 2020/3/18 17:10
 * @desc :
 */
public class UserDataBean {


    /**
     * multi_depart : 1
     * userInfo : {"id":"e9ca23d68d884d4ebb19d07889727dae","username":"admin","realname":"管理员","avatar":"temp/11_1582468686154.jpg","birthday":"2018-12-05","sex":1,"email":"","phone":"","orgCode":"A01","status":1,"delFlag":"0","workNo":"111","post":"","telephone":null,"createBy":null,"createTime":"2038-06-21 17:54:10","updateBy":"developer002","updateTime":"2020-03-05 09:39:12","activitiSync":"1","identity":2,"departIds":"10e59eadd6bb43abb9714597f6488334"}
     * departs : [{"id":"10e59eadd6bb43abb9714597f6488334","parentId":"","departName":"山东省轻工业安全生产管理协会","departNameEn":null,"departNameAbbr":null,"departOrder":0,"description":null,"orgCategory":"1","orgType":"1","orgCode":"A01","mobile":null,"fax":null,"address":null,"memo":null,"status":null,"delFlag":"0","createBy":"admin","createTime":"2020-02-24 13:49:42","updateBy":null,"updateTime":null}]
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1ODQ1MjMyMzUsInVzZXJuYW1lIjoiYWRtaW4ifQ.szJ5NZPFn8IxiIlc5NdDpT1jYXf-rJqK5dEaOW0Ygi8
     */

    private int multi_depart;
    private UserInfoBean userInfo;
    private String token;
    private List<DepartsBean> departs;

    public int getMulti_depart() {
        return multi_depart;
    }

    public void setMulti_depart(int multi_depart) {
        this.multi_depart = multi_depart;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<DepartsBean> getDeparts() {
        return departs;
    }

    public void setDeparts(List<DepartsBean> departs) {
        this.departs = departs;
    }

    public static class UserInfoBean {
        /**
         * id : e9ca23d68d884d4ebb19d07889727dae
         * username : admin
         * realname : 管理员
         * avatar : temp/11_1582468686154.jpg
         * birthday : 2018-12-05
         * sex : 1
         * email :
         * phone :
         * orgCode : A01
         * status : 1
         * delFlag : 0
         * workNo : 111
         * post :
         * telephone : null
         * createBy : null
         * createTime : 2038-06-21 17:54:10
         * updateBy : developer002
         * updateTime : 2020-03-05 09:39:12
         * activitiSync : 1
         * identity : 2
         * departIds : 10e59eadd6bb43abb9714597f6488334
         */

        private String id;
        private String username;
        private String userpwd;
        private String realname;
        private String avatar;
        private String birthday;
        private int sex;
        private String email;
        private String phone;
        private String orgCode;
        private int status;
        private String delFlag;
        private String workNo;
        private String post;
        private Object telephone;
        private Object createBy;
        private String createTime;
        private String updateBy;
        private String updateTime;
        private String activitiSync;
        private int identity;
        private String departIds;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getWorkNo() {
            return workNo;
        }

        public void setWorkNo(String workNo) {
            this.workNo = workNo;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public Object getTelephone() {
            return telephone;
        }

        public void setTelephone(Object telephone) {
            this.telephone = telephone;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getActivitiSync() {
            return activitiSync;
        }

        public void setActivitiSync(String activitiSync) {
            this.activitiSync = activitiSync;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public String getDepartIds() {
            return departIds;
        }

        public void setDepartIds(String departIds) {
            this.departIds = departIds;
        }

        public String getUserpwd() {
            return userpwd;
        }

        public void setUserpwd(String userpwd) {
            this.userpwd = userpwd;
        }
    }

    public static class DepartsBean {
        /**
         * id : 10e59eadd6bb43abb9714597f6488334
         * parentId :
         * departName : 山东省轻工业安全生产管理协会
         * departNameEn : null
         * departNameAbbr : null
         * departOrder : 0
         * description : null
         * orgCategory : 1
         * orgType : 1
         * orgCode : A01
         * mobile : null
         * fax : null
         * address : null
         * memo : null
         * status : null
         * delFlag : 0
         * createBy : admin
         * createTime : 2020-02-24 13:49:42
         * updateBy : null
         * updateTime : null
         */

        private String id;
        private String parentId;
        private String departName;
        private Object departNameEn;
        private Object departNameAbbr;
        private int departOrder;
        private Object description;
        private String orgCategory;
        private String orgType;
        private String orgCode;
        private Object mobile;
        private Object fax;
        private Object address;
        private Object memo;
        private Object status;
        private String delFlag;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public String getDepartName() {
            return departName;
        }

        public void setDepartName(String departName) {
            this.departName = departName;
        }

        public Object getDepartNameEn() {
            return departNameEn;
        }

        public void setDepartNameEn(Object departNameEn) {
            this.departNameEn = departNameEn;
        }

        public Object getDepartNameAbbr() {
            return departNameAbbr;
        }

        public void setDepartNameAbbr(Object departNameAbbr) {
            this.departNameAbbr = departNameAbbr;
        }

        public int getDepartOrder() {
            return departOrder;
        }

        public void setDepartOrder(int departOrder) {
            this.departOrder = departOrder;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getOrgCategory() {
            return orgCategory;
        }

        public void setOrgCategory(String orgCategory) {
            this.orgCategory = orgCategory;
        }

        public String getOrgType() {
            return orgType;
        }

        public void setOrgType(String orgType) {
            this.orgType = orgType;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public Object getMobile() {
            return mobile;
        }

        public void setMobile(Object mobile) {
            this.mobile = mobile;
        }

        public Object getFax() {
            return fax;
        }

        public void setFax(Object fax) {
            this.fax = fax;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getMemo() {
            return memo;
        }

        public void setMemo(Object memo) {
            this.memo = memo;
        }

        public Object getStatus() {
            return status;
        }

        public void setStatus(Object status) {
            this.status = status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
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
    }
}
