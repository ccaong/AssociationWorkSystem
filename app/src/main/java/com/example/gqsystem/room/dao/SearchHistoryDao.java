package com.example.gqsystem.room.dao;

import com.example.gqsystem.bean.SearchHistoryBean;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * @author : devel
 * @date : 2020/3/23 10:27
 * @desc : 搜素历史Dao
 */
@Dao
public interface SearchHistoryDao {

    /**
     * 获取所有历史搜素数据
     *
     * @return
     */
    @Query("Select * from searchHistory")
    List<SearchHistoryBean> getAll();


    /**
     * 删除所有数据
     *
     * @return
     */
    @Query("delete from searchHistory")
    void deleteAll();


    /**
     * 插入一条数据
     *
     * @param bean
     */
    @Insert
    void insertSearchHistory(SearchHistoryBean bean);


    /**
     * 插入多条数据
     *
     * @param beans
     */
    @Insert
    void insertAll(SearchHistoryBean... beans);


    /**
     * 插入多条数据
     *
     * @param beans
     */
    @Insert
    void insertList(List<SearchHistoryBean> beans);


    /**
     * 删除数据
     *
     * @param beans
     */
    @Delete
    void delete(SearchHistoryBean... beans);

}
