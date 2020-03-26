package com.example.gqsystem.bean;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author : devel
 * @date : 2020/3/23 10:22
 * @desc : 搜索历史
 */
@Entity(tableName = "searchHistory")
public class SearchHistoryBean {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private Integer type;

    @ColumnInfo(name = "searchName")
    private String searchName;


    public SearchHistoryBean() {

    }

    @Ignore
    public SearchHistoryBean( Integer type, String searchName) {
        this.type = type;
        this.searchName = searchName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
}
