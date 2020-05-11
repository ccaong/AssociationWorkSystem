package com.example.gqsystem.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author : devel
 * @date : 2020/3/19 10:25
 * @desc :领导动态
 */
public class LeaderActivityListBean implements Serializable{

    /**
     * records : [{"activityContent":"二级安全标准化","activityFile":"temp/11_1583979191834.txt","activityName":"二级安全标准化危险化学品和烟花爆竹单位的公告","activityTime":"2020-03-22","updateTime":"2020-03-12 10:18:52","addressDetails":null,"activityAddress":"山东省,济南市,历下区","createBy":"admin","createTime":"2020-03-12 10:13:25","updateBy":"admin","sysOrgCode":"A01","id":"1237924626563125250","invitationUnit":"山东省应急厅"}]
     * total : 1
     * size : 10
     * current : 1
     * orders : []
     * searchCount : true
     * pages : 1
     */

    private int total;
    private int size;
    private int current;
    private boolean searchCount;
    private int pages;
    private List<RecordsBean> records;
    private List<?> orders;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }

    public List<?> getOrders() {
        return orders;
    }

    public void setOrders(List<?> orders) {
        this.orders = orders;
    }

    public static class RecordsBean implements Serializable {
        /**
         * activityContent : 二级安全标准化
         * activityFile : temp/11_1583979191834.txt
         * activityName : 二级安全标准化危险化学品和烟花爆竹单位的公告
         * activityTime : 2020-03-22
         * updateTime : 2020-03-12 10:18:52
         * addressDetails : null
         * activityAddress : 山东省,济南市,历下区
         * createBy : admin
         * createTime : 2020-03-12 10:13:25
         * updateBy : admin
         * sysOrgCode : A01
         * id : 1237924626563125250
         * invitationUnit : 山东省应急厅
         */

        private String activityContent;
        private String activityFile;
        private String activityName;
        private String activityTime;
        private String updateTime;
        private String addressDetails;
        private String activityAddress;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String sysOrgCode;
        private String id;
        private String invitationUnit;
        private int downloadProgress;

        public String getActivityContent() {
            return activityContent;
        }

        public void setActivityContent(String activityContent) {
            this.activityContent = activityContent;
        }

        public String getActivityFile() {
            return activityFile;
        }

        public void setActivityFile(String activityFile) {
            this.activityFile = activityFile;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getActivityTime() {
            return activityTime;
        }

        public void setActivityTime(String activityTime) {
            this.activityTime = activityTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getAddressDetails() {
            return addressDetails;
        }

        public void setAddressDetails(String addressDetails) {
            this.addressDetails = addressDetails;
        }

        public String getActivityAddress() {
            return activityAddress;
        }

        public void setActivityAddress(String activityAddress) {
            this.activityAddress = activityAddress;
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

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(String sysOrgCode) {
            this.sysOrgCode = sysOrgCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getInvitationUnit() {
            return invitationUnit;
        }

        public void setInvitationUnit(String invitationUnit) {
            this.invitationUnit = invitationUnit;
        }

        public int getDownloadProgress() {
            return downloadProgress;
        }

        public void setDownloadProgress(int downloadProgress) {
            this.downloadProgress = downloadProgress;
        }
    }
}
