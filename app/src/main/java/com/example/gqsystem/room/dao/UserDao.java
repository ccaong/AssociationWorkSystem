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

}
