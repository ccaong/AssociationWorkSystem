package com.example.gqsystem.bean.response;

import java.util.List;

/**
 * @author : devel
 * @date : 2020/3/30 17:07
 * @desc :
 */
public class DataShareListBean {

    /**
     * records : [{"share_dictText":"共享","dataType":"1","updateTime":"2020-03-30 11:47:16","remark":"工作方案","dataType_dictText":"项目资料","createBy":"admin","createTime":"2020-03-30 11:36:58","updateBy":"admin","proId":"1244468634709204994","sysOrgCode":"A01","dataFile":"temp/作业安全数据库设计_1585539353306.xlsx","share":"1","id":"1244471226566750210","proName":"测试资料共享2"},{"share_dictText":"共享","dataType":"1","updateTime":"2020-03-30 11:47:16","remark":"建设方案","dataType_dictText":"项目资料","createBy":"admin","createTime":"2020-03-30 11:36:58","updateBy":"admin","proId":"1244468634709204994","sysOrgCode":"A01","dataFile":"temp/自用系统数据库设计_1585539355751.xlsx","share":"1","id":"1244471226906488834","proName":"测试资料共享2"}]
     * total : 2
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

    public static class RecordsBean {
        /**
         * share_dictText : 共享
         * dataType : 1
         * updateTime : 2020-03-30 11:47:16
         * remark : 工作方案
         * dataType_dictText : 项目资料
         * createBy : admin
         * createTime : 2020-03-30 11:36:58
         * updateBy : admin
         * proId : 1244468634709204994
         * sysOrgCode : A01
         * dataFile : temp/作业安全数据库设计_1585539353306.xlsx
         * share : 1
         * id : 1244471226566750210
         * proName : 测试资料共享2
         * progress
         */

        private String share_dictText;
        private String dataType;
        private String updateTime;
        private String remark;
        private String dataType_dictText;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String proId;
        private String sysOrgCode;
        private String dataFile;
        private String share;
        private String id;
        private String proName;
        private Integer progress;

        public String getShare_dictText() {
            return share_dictText;
        }

        public void setShare_dictText(String share_dictText) {
            this.share_dictText = share_dictText;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getDataType_dictText() {
            return dataType_dictText;
        }

        public void setDataType_dictText(String dataType_dictText) {
            this.dataType_dictText = dataType_dictText;
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

        public String getProId() {
            return proId;
        }

        public void setProId(String proId) {
            this.proId = proId;
        }

        public String getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(String sysOrgCode) {
            this.sysOrgCode = sysOrgCode;
        }

        public String getDataFile() {
            return dataFile;
        }

        public void setDataFile(String dataFile) {
            this.dataFile = dataFile;
        }

        public String getShare() {
            return share;
        }

        public void setShare(String share) {
            this.share = share;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public Integer getProgress() {
            return progress;
        }

        public void setProgress(Integer progress) {
            this.progress = progress;
        }

        public void setRecordsBean(RecordsBean bean){
            this.share_dictText = bean.getShare_dictText();
            this.dataType = bean.getDataType();
            this.updateTime = bean.getUpdateTime();
            this.remark = bean.getRemark();
            this.dataType_dictText = bean.getDataType_dictText();
            this.createBy = bean.getCreateBy();
            this.createTime = bean.getCreateTime();
            this.updateBy = bean.getUpdateBy();
            this.proId = bean.getProId();
            this.sysOrgCode = bean.getSysOrgCode();
            this.dataFile = bean.getDataFile();
            this.share = bean.getShare();
            this.id = bean.getId();
            this.proName = bean.getProName();
            this.progress = bean.getProgress();

        }
    }
}
