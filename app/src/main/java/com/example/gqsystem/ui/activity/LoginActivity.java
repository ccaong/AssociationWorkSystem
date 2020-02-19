package com.example.gqsystem.ui.activity;

import android.os.Bundle;

import com.example.gqsystem.R;
import com.example.gqsystem.entity.response.LoginData;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author : devel
 * @date : 2020/2/18 16:42
 * @desc :
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginData loginData = new LoginData();
        // TODO: 2020/2/19 没网

        // TODO: 2020/2/19 获取用户信息
        // TODO: 2020/2/19 没有信息，显示登录界面

        // TODO: 2020/2/19 有登录信息，自动登录

        // TODO: 2020/2/19 登录成功，显示指纹 

        // TODO: 2020/2/19 登录失败，显示登录界面
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    /**
     * 登录
     */
    private void login() {

    }


    /**
     * 验证指纹
     */
    private void confirmfingerprint() {

    }
}
