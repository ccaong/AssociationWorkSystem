package com.example.gqsystem.room;

import android.content.Context;

import com.example.gqsystem.bean.SearchHistoryBean;
import com.example.gqsystem.bean.response.LoginData;
import com.example.gqsystem.room.dao.SearchHistoryDao;
import com.example.gqsystem.room.dao.UserDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author : devel
 * @date : 2019/11/18 11:04
 * @desc :AbstractDataBase
 */

@Database(entities = {SearchHistoryBean.class, LoginData.class}, version = 1, exportSchema = false)
public abstract class AbstractDataBase extends RoomDatabase {

    /**
     * 获取人员信息DAO
     *
     * @return UserDao
     */
    public abstract UserDao getUserDao();

    /**
     * 获取搜索历史Dao
     *
     * @return SearchHistoryDao
     */
    public abstract SearchHistoryDao getHistoryDao();

    private static final String DB_NAME = "AssSystemAppDB";

    private static volatile AbstractDataBase appDataBase;

    public static AbstractDataBase getInstance(Context context) {
        if (appDataBase == null) {
            synchronized (AbstractDataBase.class) {
                if (appDataBase == null) {
                    return Room.databaseBuilder(context, AbstractDataBase.class, DB_NAME)
                            .addMigrations()//迁移数据库使用
                            .fallbackToDestructiveMigration()//迁移数据库如果发生错误，将会重新创建数据库，而不是发生崩溃
                            .build();
                }
            }
        }
        return appDataBase;
    }
}
