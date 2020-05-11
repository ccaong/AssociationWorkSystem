package com.example.gqsystem.bean.response;

import java.util.List;

/**
 * @author : devel
 * @date : 2020/3/27 11:34
 * @desc :
 */
public class MeetingListBean {
    /**
     * records : [{"updateTime":null,"meetingType":1,"meetingTime":"2020-04-05","meetingNotice":"temp/ztree_1585277529408.txt","createBy":"admin","meetingType_dictText":"内部会议","meetingName":"亚运会","meetingData":"temp/登录_1585277548769.txt","createTime":"2020-03-27 10:52:50","updateBy":null,"meetingPic":null,"sysOrgCode":"A01","id":"1243370363685994497","meetingSummary":"temp/老版本换到新版上需要需改的数据_1585277559306.txt,temp/8d6751f7331a63e7f0785a590ab1f75_1585277559317.png"}]
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

    public static class RecordsBean {
        /**
         * updateTime : null
         * meetingType : 1
         * meetingTime : 2020-04-05
         * meetingNotice : temp/ztree_1585277529408.txt
         * createBy : admin
         * meetingType_dictText : 内部会议
         * meetingName : 亚运会
         * meetingData : temp/登录_1585277548769.txt
         * createTime : 2020-03-27 10:52:50
         * updateBy : null
         * meetingPic : null
         * sysOrgCode : A01
         * id : 1243370363685994497
         * meetingSummary : temp/老版本换到新版上需要需改的数据_1585277559306.txt,temp/8d6751f7331a63e7f0785a590ab1f75_1585277559317.png
         */

        private Object updateTime;
        private int meetingType;
        private String meetingTime;
        private String meetingNotice;
        private String createBy;
        private String meetingType_dictText;
        private String meetingName;
        private String meetingData;
        private String createTime;
        private Object updateBy;
        private String meetingPic;
        private String sysOrgCode;
        private String id;
        private String meetingSummary;
        private int downloadProgress;

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public int getMeetingType() {
            return meetingType;
        }

        public void setMeetingType(int meetingType) {
            this.meetingType = meetingType;
        }

        public String getMeetingTime() {
            return meetingTime;
        }

        public void setMeetingTime(String meetingTime) {
            this.meetingTime = meetingTime;
        }

        public String getMeetingNotice() {
            return meetingNotice;
        }

        public void setMeetingNotice(String meetingNotice) {
            this.meetingNotice = meetingNotice;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getMeetingType_dictText() {
            return meetingType_dictText;
        }

        public void setMeetingType_dictText(String meetingType_dictText) {
            this.meetingType_dictText = meetingType_dictText;
        }

        public String getMeetingName() {
            return meetingName;
        }

        public void setMeetingName(String meetingName) {
            this.meetingName = meetingName;
        }

        public String getMeetingData() {
            return meetingData;
        }

        public void setMeetingData(String meetingData) {
            this.meetingData = meetingData;
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

        public String getMeetingPic() {
            return meetingPic;
        }

        public void setMeetingPic(String meetingPic) {
            this.meetingPic = meetingPic;
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

        public String getMeetingSummary() {
            return meetingSummary;
        }

        public void setMeetingSummary(String meetingSummary) {
            this.meetingSummary = meetingSummary;
        }

        public int getDownloadProgress() {
            return downloadProgress;
        }

        public void setDownloadProgress(int downloadProgress) {
            this.downloadProgress = downloadProgress;
        }
    }
}
