package com.example.gqsystem.bean.response;

import java.util.List;

/**
 * @author : devel
 * @date : 2020/3/19 10:43
 * @desc :
 */
public class PersonListBean {

    /**
     * current : 0
     * pages : 0
     * records : [{"activitiSync":"","avatar":"","birthday":"","createBy":"","createTime":"","delFlag":"","departIds":"","email":"","id":"","identity":0,"orgCode":"","phone":"","post":"","realname":"","sex":0,"status":0,"telephone":"","updateBy":"","updateTime":"","username":"","workNo":""}]
     * searchCount : true
     * size : 0
     * total : 0
     */

    private int current;
    private int pages;
    private boolean searchCount;
    private int size;
    private int total;
    private List<RecordsBean> records;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public static class RecordsBean {
        /**
         * activitiSync :
         * avatar :
         * birthday :
         * createBy :
         * createTime :
         * delFlag :
         * departIds :
         * email :
         * id :
         * identity : 0
         * orgCode :
         * phone :
         * post :
         * realname :
         * sex : 0
         * status : 0
         * telephone :
         * updateBy :
         * updateTime :
         * username :
         * workNo :
         */

        private String activitiSync;
        private String avatar;
        private String birthday;
        private String createBy;
        private String createTime;
        private String delFlag;
        private String departIds;
        private String email;
        private String id;
        private int identity;
        private String orgCode;
        private String phone;
        private String post;
        private String realname;
        private int sex;
        private int status;
        private String telephone;
        private String updateBy;
        private String updateTime;
        private String username;
        private String workNo;

        public String getActivitiSync() {
            return activitiSync;
        }

        public void setActivitiSync(String activitiSync) {
            this.activitiSync = activitiSync;
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

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getDepartIds() {
            return departIds;
        }

        public void setDepartIds(String departIds) {
            this.departIds = departIds;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIdentity() {
            return identity;
        }

        public void setIdentity(int identity) {
            this.identity = identity;
        }

        public String getOrgCode() {
            return orgCode;
        }

        public void setOrgCode(String orgCode) {
            this.orgCode = orgCode;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        public String getRealname() {
            return realname;
        }

        public void setRealname(String realname) {
            this.realname = realname;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
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

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getWorkNo() {
            return workNo;
        }

        public void setWorkNo(String workNo) {
            this.workNo = workNo;
        }
    }
}
