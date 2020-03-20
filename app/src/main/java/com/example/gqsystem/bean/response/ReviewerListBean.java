package com.example.gqsystem.bean.response;

import java.util.List;

/**
 * @author : devel
 * @date : 2020/3/18 17:31
 * @desc :评审人员列表
 */
public class ReviewerListBean {


    /**
     * records : [{"profession":"3","idCard":"370104199409241316","updateTime":"2020-03-13 13:42:55","remark":"备注1","profession_dictText":"建材行业","createBy":"developer001","createTime":"2020-03-05 10:32:26","updateBy":"admin","phone":"15269192591","name":"李四","expiringDate":"2029-03-31","sysOrgCode":"A01A05","reviewerType":"2","company":"2","id":"1235392696226693121","certificateNum":"ZS-20200305","accessory":"temp/npEj-hqhqcir1847618_1583375536945.jpg,temp/隐患排查标准错误信息20200310094821_1584078172863.txt","company_dictText":"山东冠隆安全工程师事务所有限公司","reviewerType_dictText":"评审专家"},{"profession":"4","idCard":"370104199409241316","updateTime":"2020-02-25 16:00:03","remark":"","profession_dictText":"机械行业","createBy":"developer001","createTime":"2020-02-25 09:42:17","updateBy":"admin","phone":"15269192591","name":"张三","expiringDate":"2030-01-01","sysOrgCode":"A01A05","reviewerType":"2","company":"1","id":"40280981707a025101707a0251dd0000","certificateNum":"ZS-20200225","accessory":"有限空间导入模板_1582594934685.xls","company_dictText":"山东省轻工业安全生产管理协会","reviewerType_dictText":"评审专家"}]
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
         * profession : 3
         * idCard : 370104199409241316
         * updateTime : 2020-03-13 13:42:55
         * remark : 备注1
         * profession_dictText : 建材行业
         * createBy : developer001
         * createTime : 2020-03-05 10:32:26
         * updateBy : admin
         * phone : 15269192591
         * name : 李四
         * expiringDate : 2029-03-31
         * sysOrgCode : A01A05
         * reviewerType : 2
         * company : 2
         * id : 1235392696226693121
         * certificateNum : ZS-20200305
         * accessory : temp/npEj-hqhqcir1847618_1583375536945.jpg,temp/隐患排查标准错误信息20200310094821_1584078172863.txt
         * company_dictText : 山东冠隆安全工程师事务所有限公司
         * reviewerType_dictText : 评审专家
         */

        private String profession;
        private String idCard;
        private String updateTime;
        private String remark;
        private String profession_dictText;
        private String createBy;
        private String createTime;
        private String updateBy;
        private String phone;
        private String name;
        private String expiringDate;
        private String sysOrgCode;
        private String reviewerType;
        private String company;
        private String id;
        private String certificateNum;
        private String accessory;
        private String company_dictText;
        private String reviewerType_dictText;

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
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

        public String getProfession_dictText() {
            return profession_dictText;
        }

        public void setProfession_dictText(String profession_dictText) {
            this.profession_dictText = profession_dictText;
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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExpiringDate() {
            return expiringDate;
        }

        public void setExpiringDate(String expiringDate) {
            this.expiringDate = expiringDate;
        }

        public String getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(String sysOrgCode) {
            this.sysOrgCode = sysOrgCode;
        }

        public String getReviewerType() {
            return reviewerType;
        }

        public void setReviewerType(String reviewerType) {
            this.reviewerType = reviewerType;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCertificateNum() {
            return certificateNum;
        }

        public void setCertificateNum(String certificateNum) {
            this.certificateNum = certificateNum;
        }

        public String getAccessory() {
            return accessory;
        }

        public void setAccessory(String accessory) {
            this.accessory = accessory;
        }

        public String getCompany_dictText() {
            return company_dictText;
        }

        public void setCompany_dictText(String company_dictText) {
            this.company_dictText = company_dictText;
        }

        public String getReviewerType_dictText() {
            return reviewerType_dictText;
        }

        public void setReviewerType_dictText(String reviewerType_dictText) {
            this.reviewerType_dictText = reviewerType_dictText;
        }
    }
}
