package com.example.gqsystem.entity.response;

import java.io.Serializable;
import java.util.Map;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * @author devel
 */
@Entity(tableName = "user")
public class LoginData implements Serializable {


    /**
     * loginName : admin
     * userName : 系统管理员
     * userId : admin
     * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHQiOjE1NDA3OTkxNTA4OTgsImlhdCI6MTU0MDc5NzM1MDg5OCwiam9iTnVtYmVyIjoiYWRtaW4ifQ.W4xumKb-TLf9dWQgTh-ycrXhbS28sF-ciVG0cbkrl7o
     */

    @PrimaryKey
    private String userId;

    @ColumnInfo(name = "loginName")
    private String loginName;

    @ColumnInfo(name = "userName")
    private String userName;

    @ColumnInfo(name = "token")
    private String token;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "funtionMap")
    private Map funtionMap;


    public LoginData() {
    }

    @Ignore
    public LoginData(String userId, String loginName, String userName, String token, String password, Map funtionMap) {
        this.userId = userId;
        this.loginName = loginName;
        this.userName = userName;
        this.token = token;
        this.password = password;
        this.funtionMap = funtionMap;
    }

    public Map getFuntionMap() {
        return funtionMap;
    }

    public void setFuntionMap(Map funtionMap) {
        this.funtionMap = funtionMap;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
