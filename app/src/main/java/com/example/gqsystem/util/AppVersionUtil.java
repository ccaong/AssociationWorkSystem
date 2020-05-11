package com.example.gqsystem.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.gqsystem.App;

/**
 * @author devel
 */
public class AppVersionUtil {


    /**
     * 获取当前apk的版本号 currentVersionCode
     *
     * @return
     */
    public static String getAPPLocalVersion() {
        int currentVersionCode = 0;
        String appVersionName = "1.0";
        PackageManager manager = App.getContext().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(App.getContext().getPackageName(), 0);
            //版本名
            appVersionName = info.versionName;
            // 版本号
            currentVersionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appVersionName;
    }

    /**
     *  
     * 获取当前手机系统版本号 
     *
     * @return  系统版本号       
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号 
     *
     * @return  手机型号 
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

}
