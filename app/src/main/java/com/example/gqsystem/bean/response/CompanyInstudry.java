package com.example.gqsystem.bean.response;

/**
 * @author : devel
 * @date : 2020/3/20 12:55
 * @desc :所属行业
 */
public class CompanyInstudry {
    /**
     * industry_medium_id : 489
     * industry_main_id : E
     * company_id : 1
     * industry_small_id : 4892
     * main_name : 建筑业
     * industry_large_id : 48
     * id : 15832205851650
     * small_name : 体育场地设施工程施工
     */

    private long id;
    private String company_id;
    private String industry_large_id;
    private String industry_medium_id;
    private String industry_small_id;
    private String industry_main_id;
    private String main_name;
    private String small_name;

    public String getIndustry_medium_id() {
        return industry_medium_id;
    }

    public void setIndustry_medium_id(String industry_medium_id) {
        this.industry_medium_id = industry_medium_id;
    }

    public String getIndustry_main_id() {
        return industry_main_id;
    }

    public void setIndustry_main_id(String industry_main_id) {
        this.industry_main_id = industry_main_id;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getIndustry_small_id() {
        return industry_small_id;
    }

    public void setIndustry_small_id(String industry_small_id) {
        this.industry_small_id = industry_small_id;
    }

    public String getMain_name() {
        return main_name;
    }

    public void setMain_name(String main_name) {
        this.main_name = main_name;
    }

    public String getIndustry_large_id() {
        return industry_large_id;
    }

    public void setIndustry_large_id(String industry_large_id) {
        this.industry_large_id = industry_large_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSmall_name() {
        return small_name;
    }

    public void setSmall_name(String small_name) {
        this.small_name = small_name;
    }
}
