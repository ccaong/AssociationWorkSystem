package com.example.gqsystem.bean.response;

import java.util.List;

/**
 * @author : devel
 * @date : 2020/3/19 9:59
 * @desc :
 */
public class CompanyListBean {

    /**
     * records : [{"comType":"4","comAddressResult":null,"sourceResult":null,"comCreateTime":null,"comMarket":"5","comSpecialReform":"1,2","updateBy":"developer002","platformUser":"admin,developer002","id":1,"comType_dictText":"股份制","employNumber":null,"touchReason":null,"comRelation":"2,3","platformUser_dictText":"管理员,开发者002","updateTime":"2020-03-09","touchSource":null,"comSpecialReform_dictText":"涉氨制冷,有限空间","comAddress":"山东省,淄博市","createBy":"developer002","comAddressDetail":"淄博开发区裕民路135号","createTime":"2020-03-03","fristTouchTime":"2020-03-03","comMarket_dictText":"未上市","sysOrgCode":"A01A05","comName":"山东得益乳业股份有限公司","comProcessType":null,"comRelation_dictText":"一般关系,弱关系"},{"comType":"3","comAddressResult":null,"sourceResult":null,"comCreateTime":null,"comMarket":"5","comSpecialReform":"2,3","updateBy":"developer002","platformUser":"admin","id":2,"comType_dictText":"民营","employNumber":null,"touchReason":null,"comRelation":null,"platformUser_dictText":"管理员","updateTime":"2020-03-04","touchSource":null,"comSpecialReform_dictText":"有限空间,粉尘防爆","comAddress":"山东省,烟台市,莱州市","createBy":"developer002","comAddressDetail":"山东省莱阳市富山路382号","createTime":"2020-03-03","fristTouchTime":"2020-03-03","comMarket_dictText":"未上市","sysOrgCode":"A01A05","comName":"莱阳春雪食品有限公司","comProcessType":null,"comRelation_dictText":null},{"comType":"4","comAddressResult":null,"sourceResult":null,"comCreateTime":null,"comMarket":"5","comSpecialReform":"1,3","updateBy":"developer002","platformUser":"admin","id":3,"comType_dictText":"股份制","employNumber":null,"touchReason":null,"comRelation":"2","platformUser_dictText":"管理员","updateTime":"2020-03-04","touchSource":null,"comSpecialReform_dictText":"涉氨制冷,粉尘防爆","comAddress":"河南省,焦作市,解放区","createBy":"developer002","comAddressDetail":"安丘市景芝镇景阳街","createTime":"2020-03-03","fristTouchTime":"2020-03-03","comMarket_dictText":"未上市","sysOrgCode":"A01A05","comName":"山东景芝酒业股份有限公司","comProcessType":null,"comRelation_dictText":"一般关系"},{"comType":"1","comAddressResult":"ffff","sourceResult":null,"comCreateTime":"2020-03-04","comMarket":"2","comSpecialReform":"2,3","updateBy":"developer002","platformUser":"admin,developer002","id":5,"comType_dictText":"央企","employNumber":222,"touchReason":"ssss","comRelation":"2,3","platformUser_dictText":"管理员,开发者002","updateTime":"2020-03-09","touchSource":"dddd","comSpecialReform_dictText":"有限空间,粉尘防爆","comAddress":"河北省,唐山市,路北区","createBy":"developer002","comAddressDetail":"ssssffff","createTime":"2020-03-04","fristTouchTime":"2020-03-04","comMarket_dictText":"创业板","sysOrgCode":"A01A05","comName":"111","comProcessType":"ssssdddd","comRelation_dictText":"一般关系,弱关系"}]
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

    public static class RecordsBean {
        /**
         * comType : 4
         * comAddressResult : null
         * sourceResult : null
         * comCreateTime : null
         * comMarket : 5
         * comSpecialReform : 1,2
         * updateBy : developer002
         * platformUser : admin,developer002
         * id : 1
         * comType_dictText : 股份制
         * employNumber : null
         * touchReason : null
         * comRelation : 2,3
         * platformUser_dictText : 管理员,开发者002
         * updateTime : 2020-03-09
         * touchSource : null
         * comSpecialReform_dictText : 涉氨制冷,有限空间
         * comAddress : 山东省,淄博市
         * createBy : developer002
         * comAddressDetail : 淄博开发区裕民路135号
         * createTime : 2020-03-03
         * fristTouchTime : 2020-03-03
         * comMarket_dictText : 未上市
         * sysOrgCode : A01A05
         * comName : 山东得益乳业股份有限公司
         * comProcessType : null
         * comRelation_dictText : 一般关系,弱关系
         */

        private String comType;
        private Object comAddressResult;
        private Object sourceResult;
        private Object comCreateTime;
        private String comMarket;
        private String comSpecialReform;
        private String updateBy;
        private String platformUser;
        private int id;
        private String comType_dictText;
        private Object employNumber;
        private Object touchReason;
        private String comRelation;
        private String platformUser_dictText;
        private String updateTime;
        private Object touchSource;
        private String comSpecialReform_dictText;
        private String comAddress;
        private String createBy;
        private String comAddressDetail;
        private String createTime;
        private String fristTouchTime;
        private String comMarket_dictText;
        private String sysOrgCode;
        private String comName;
        private Object comProcessType;
        private String comRelation_dictText;

        public String getComType() {
            return comType;
        }

        public void setComType(String comType) {
            this.comType = comType;
        }

        public Object getComAddressResult() {
            return comAddressResult;
        }

        public void setComAddressResult(Object comAddressResult) {
            this.comAddressResult = comAddressResult;
        }

        public Object getSourceResult() {
            return sourceResult;
        }

        public void setSourceResult(Object sourceResult) {
            this.sourceResult = sourceResult;
        }

        public Object getComCreateTime() {
            return comCreateTime;
        }

        public void setComCreateTime(Object comCreateTime) {
            this.comCreateTime = comCreateTime;
        }

        public String getComMarket() {
            return comMarket;
        }

        public void setComMarket(String comMarket) {
            this.comMarket = comMarket;
        }

        public String getComSpecialReform() {
            return comSpecialReform;
        }

        public void setComSpecialReform(String comSpecialReform) {
            this.comSpecialReform = comSpecialReform;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public String getPlatformUser() {
            return platformUser;
        }

        public void setPlatformUser(String platformUser) {
            this.platformUser = platformUser;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getComType_dictText() {
            return comType_dictText;
        }

        public void setComType_dictText(String comType_dictText) {
            this.comType_dictText = comType_dictText;
        }

        public Object getEmployNumber() {
            return employNumber;
        }

        public void setEmployNumber(Object employNumber) {
            this.employNumber = employNumber;
        }

        public Object getTouchReason() {
            return touchReason;
        }

        public void setTouchReason(Object touchReason) {
            this.touchReason = touchReason;
        }

        public String getComRelation() {
            return comRelation;
        }

        public void setComRelation(String comRelation) {
            this.comRelation = comRelation;
        }

        public String getPlatformUser_dictText() {
            return platformUser_dictText;
        }

        public void setPlatformUser_dictText(String platformUser_dictText) {
            this.platformUser_dictText = platformUser_dictText;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public Object getTouchSource() {
            return touchSource;
        }

        public void setTouchSource(Object touchSource) {
            this.touchSource = touchSource;
        }

        public String getComSpecialReform_dictText() {
            return comSpecialReform_dictText;
        }

        public void setComSpecialReform_dictText(String comSpecialReform_dictText) {
            this.comSpecialReform_dictText = comSpecialReform_dictText;
        }

        public String getComAddress() {
            return comAddress;
        }

        public void setComAddress(String comAddress) {
            this.comAddress = comAddress;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getComAddressDetail() {
            return comAddressDetail;
        }

        public void setComAddressDetail(String comAddressDetail) {
            this.comAddressDetail = comAddressDetail;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFristTouchTime() {
            return fristTouchTime;
        }

        public void setFristTouchTime(String fristTouchTime) {
            this.fristTouchTime = fristTouchTime;
        }

        public String getComMarket_dictText() {
            return comMarket_dictText;
        }

        public void setComMarket_dictText(String comMarket_dictText) {
            this.comMarket_dictText = comMarket_dictText;
        }

        public String getSysOrgCode() {
            return sysOrgCode;
        }

        public void setSysOrgCode(String sysOrgCode) {
            this.sysOrgCode = sysOrgCode;
        }

        public String getComName() {
            return comName;
        }

        public void setComName(String comName) {
            this.comName = comName;
        }

        public Object getComProcessType() {
            return comProcessType;
        }

        public void setComProcessType(Object comProcessType) {
            this.comProcessType = comProcessType;
        }

        public String getComRelation_dictText() {
            return comRelation_dictText;
        }

        public void setComRelation_dictText(String comRelation_dictText) {
            this.comRelation_dictText = comRelation_dictText;
        }
    }
}
