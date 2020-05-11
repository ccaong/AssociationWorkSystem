package com.example.gqsystem.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * @author : devel
 * @date : 2020/3/31 16:13
 * @desc :
 */
public class ProjectListBean {


    /**
     * records : [{"proCategory":null,"proType":null,"remark":null,"workPlan":"temp/作业安全数据库设计_1585539353306.xlsx","buildPlan":"temp/自用系统数据库设计_1585539355751.xlsx","proAttribute":null,"proAttribute_dictText":null,"projectPer":null,"updateBy":"admin","planWorkNum":null,"marketPerId":null,"marketPer":null,"projectPerId":null,"id":"1244468634709204994","comId":2,"proName":"测试资料共享2","comPer":null,"planStartTime":null,"projectPer_dictText":null,"comPerId":null,"updateTime":"2020-03-30 11:47:16","createBy":"admin","createTime":"2020-03-30 11:36:58","marketPer_dictText":null,"sysOrgCode":"A01","planEndTime":null,"comName":"莱阳春雪食品有限公司"},{"proCategory":null,"proType":null,"remark":null,"workPlan":null,"buildPlan":null,"proAttribute":null,"proAttribute_dictText":null,"projectPer":null,"updateBy":null,"planWorkNum":null,"marketPerId":null,"marketPer":null,"projectPerId":null,"id":"1244797146443808769","comId":1,"proName":"11111","comPer":null,"planStartTime":null,"projectPer_dictText":null,"comPerId":null,"updateTime":null,"createBy":"developer001","createTime":"2020-03-31 09:22:22","marketPer_dictText":null,"sysOrgCode":"A01A05","planEndTime":null,"comName":"山东得益乳业股份有限公司"},{"proCategory":null,"proType":"标准化","remark":null,"workPlan":"temp/工贸企业有限空间作业安全管理与监督暂行规定_1585632928613.pdf","buildPlan":null,"proAttribute":null,"proAttribute_dictText":null,"projectPer":"开发者001","updateBy":null,"planWorkNum":"3","marketPerId":"admin","marketPer":"管理员","projectPerId":"developer001","id":"1244860867400736770","comId":1,"proName":"测试数据","comPer":null,"planStartTime":"2020-03-31","projectPer_dictText":"","comPerId":null,"updateTime":null,"createBy":"admin","createTime":"2020-03-31 13:35:34","marketPer_dictText":"","sysOrgCode":"A01","planEndTime":"2020-04-30","comName":"山东得益乳业股份有限公司"},{"proCategory":null,"proType":"双重预防机制","remark":null,"workPlan":null,"buildPlan":null,"proAttribute":"1","proAttribute_dictText":"咨询类项目","projectPer":null,"updateBy":null,"planWorkNum":null,"marketPerId":null,"marketPer":null,"projectPerId":null,"id":"1244865173243863042","comId":1,"proName":"双重预防机制项目1","comPer":null,"planStartTime":null,"projectPer_dictText":null,"comPerId":null,"updateTime":null,"createBy":"admin","createTime":"2020-03-31 13:52:40","marketPer_dictText":null,"sysOrgCode":"A01","planEndTime":null,"comName":"山东得益乳业股份有限公司"}]
     * total : 4
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
         * proCategory : null
         * proType : null
         * remark : null
         * workPlan : temp/作业安全数据库设计_1585539353306.xlsx
         * buildPlan : temp/自用系统数据库设计_1585539355751.xlsx
         * proAttribute : null
         * proAttribute_dictText : null
         * projectPer : null
         * updateBy : admin
         * planWorkNum : null
         * marketPerId : null
         * marketPer : null
         * projectPerId : null
         * id : 1244468634709204994
         * comId : 2
         * proName : 测试资料共享2
         * comPer : null
         * planStartTime : null
         * projectPer_dictText : null
         * comPerId : null
         * updateTime : 2020-03-30 11:47:16
         * createBy : admin
         * createTime : 2020-03-30 11:36:58
         * marketPer_dictText : null
         * sysOrgCode : A01
         * planEndTime : null
         * comName : 莱阳春雪食品有限公司
         * "proStatus":"4"
         */

        private String proCategory;
        private String proType;
        private String remark;
        private String workPlan;
        private String buildPlan;
        private String proAttribute;
        private String proAttribute_dictText;
        private String projectPer;
        private String updateBy;
        private Object planWorkNum;
        private String marketPerId;
        private String marketPer;
        private String projectPerId;
        private String id;
        private int comId;
        private String proName;
        private String comPer;
        private String planStartTime;
        private String projectPer_dictText;
        private String comPerId;
        private String updateTime;
        private String createBy;
        private String createTime;
        private String marketPer_dictText;
        private String sysOrgCode;
        private String planEndTime;
        private String comName;
        private String proStatus;

        public String getProCategory() {
            return proCategory;
        }

        public void setProCategory(String proCategory) {
            this.proCategory = proCategory;
        }

        public String getProType() {
            return proType;
        }

        public void setProType(String proType) {
            this.proType = proType;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getWorkPlan() {
            return workPlan;
        }

        public void setWorkPlan(String workPlan) {
            this.workPlan = workPlan;
        }

        public String getBuildPlan() {
            return buildPlan;
        }

        public void setBuildPlan(String buildPlan) {
            this.buildPlan = buildPlan;
        }

        public String getProAttribute() {
            return proAttribute;
        }

        public void setProAttribute(String proAttribute) {
            this.proAttribute = proAttribute;
        }

        public String getProAttribute_dictText() {
            return proAttribute_dictText;
        }

        public void setProAttribute_dictText(String proAttribute_dictText) {
            this.proAttribute_dictText = proAttribute_dictText;
        }

        public String getProjectPer() {
            return projectPer;
        }

        public void setProjectPer(String projectPer) {
            this.projectPer = projectPer;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public Object getPlanWorkNum() {
            return planWorkNum;
        }

        public void setPlanWorkNum(Object planWorkNum) {
            this.planWorkNum = planWorkNum;
        }

        public String getMarketPerId() {
            return marketPerId;
        }

        public void setMarketPerId(String marketPerId) {
            this.marketPerId = marketPerId;
        }

        public String getMarketPer() {
            return marketPer;
        }

        public void setMarketPer(String marketPer) {
            this.marketPer = marketPer;
        }

        public String getProjectPerId() {
            return projectPerId;
        }

        public void setProjectPerId(String projectPerId) {
            this.projectPerId = projectPerId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getComId() {
            return comId + "";
        }

        public void setComId(int comId) {
            this.comId = comId;
        }

        public String getProName() {
            return proName;
        }

        public void setProName(String proName) {
            this.proName = proName;
        }

        public String getComPer() {
            return comPer;
        }

        public void setComPer(String comPer) {
            this.comPer = comPer;
        }

        public String getPlanStartTime() {
            return planStartTime;
        }

        public void setPlanStartTime(String planStartTime) {
            this.planStartTime = planStartTime;
        }

        public Object getProjectPer_dictText() {
            return projectPer_dictText;
        }

        public void setProjectPer_dictText(String projectPer_dictText) {
            this.projectPer_dictText = projectPer_dictText;
        }

        public String getComPerId() {
            return comPerId;
        }

        public void setComPerId(String comPerId) {
            this.comPerId = comPerId;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
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

        public String getMarketPer_dictText() {
            return marketPer_dictText;
        }

        public void setMarketPer_dictText(String marketPer_dictText) {
            this.marketPer_dictText = marketPer_dictText;
        }

        public String getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(String sysOrgCode) {
            this.sysOrgCode = sysOrgCode;
        }

        public String getPlanEndTime() {
            return planEndTime;
        }

        public void setPlanEndTime(String planEndTime) {
            this.planEndTime = planEndTime;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }


        public String getProStatus() {
            return proStatus;
        }

        public void setProStatus(String proStatus) {
            this.proStatus = proStatus;
        }
    }
}
