package com.example.gqsystem.room.dao;

import com.example.gqsystem.bean.response.LoginData;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

/**
 * @author : devel
 * @date : 2019/11/18 11:08
 * @desc :
 */
@Dao
public interface UserDao {


    /**
     * 获取所有人员信息
     *
     * @return
     */
    @Query("Select * from user")
    List<LoginData> getAll();

    /**
     * 获取指定人员信息
     *
     * @param userId
     * @return
     */
    @Query("Select * from user Where userId == (:userId)")
    List<LoginData> getUserById(String userId);


    /**
     * 插入一条数据
     *
     * @param user
     */
    @Insert
    void insertUser(LoginData user);


    /**
     * 插入多条数据
     *
     * @param users
     */
    @Insert
    void insertAll(LoginData... users);


    /**
     * 插入多条数据
     *
     * @param userBeans
     */
    @Insert
    void insertList(List<LoginData> userBeans);


    /**
     * 删除数据
     *
     * @param users
     */
    @Delete
    void delete(LoginData... users);

    /**
     * 更新数据
     *
     * @param users
     */
    @Update
    void update(LoginData... users);

    /**
     * 修改某条数据的name属性
     *
     * @param name1
     * @param uid
     */
    @Query("update user set userName =:name1 where userId=:uid")
    void updateCustom(String name1, String uid);
}
