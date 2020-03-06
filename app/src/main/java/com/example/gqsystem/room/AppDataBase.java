package com.example.gqsystem.room;

import android.content.Context;

import com.example.gqsystem.bean.response.LoginData;
import com.example.gqsystem.room.dao.UserDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author : devel
 * @date : 2019/11/18 11:04
 * @desc :
 */

@Database(entities = {LoginData.class}, version = 1 ,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    /**
     * 获取人员信息DAO
     *
     * @return
     */
    public abstract UserDao getUserDao();


    private static final String DB_NAME = "room_test";

    private static volatile AppDataBase appDataBase;

    static AppDataBase getInstance(Context context) {
        if (appDataBase == null) {
            synchronized (AppDataBase.class) {
                if (appDataBase == null) {
                    return Room.databaseBuilder(context, AppDataBase.class, DB_NAME)
                            .build();
                }
            }
        }
        return appDataBase;
    }
}
