package com.example.gqsystem.util;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.gqsystem.App;
import com.example.gqsystem.MainActivity;
import com.example.gqsystem.common.ImageDialog;
import com.example.gqsystem.config.Constants;
import com.example.gqsystem.manager.MyActivityManager;
import com.example.gqsystem.ui.activity.showfile.ShowFileActivity;
import com.orhanobut.hawk.Hawk;

import java.io.File;

/**
 * @author : devel
 * @date : 2020/3/26 11:20
 * @desc :打开文件
 */
public class FileOpenUtil {

    class WpsModel {
        static final String OPEN_MODE = "OpenMode";// 打开文件的模式。
        static final String SEND_SAVE_BROAD = "SendSaveBroad";// 文件保存时是否发送广播。
        static final String SEND_CLOSE_BROAD = "SendCloseBroad";// 文件关闭时是否发送广播
        static final String THIRD_PACKAGE = "ThirdPackage";// 第三方的包名，关闭的广播会包含该项

        static final String CLEAR_BUFFER = "ClearBuffer";// 关闭文件时是否请空临时文件。
        static final String CLEAR_TRACE = "ClearTrace";// 关闭文件时是否删除使用记录。
        static final String CLEAR_FILE = "ClearFile";// 关闭文件时是否删除打开的文件。
    }

    class OpenMode {
        // 正常模式
        static final String NORMAL = "Normal";
        // 只读模式
        static final String READ_ONLY = "ReadOnly";
        // 打开直接进入阅读器模式
        static final String READ_MODE = "ReadMode";
        // 保存模式(打开文件,另存,关闭)
        static final String SAVE_ONLY = "SaveOnly";
    }

    class ClassName {
        static final String NORMAL = "cn.wps.moffice.documentmanager.PreStartActivity2";// 普通版
    }

    class PackageName {
        static final String NORMAL = "cn.wps.moffice_eng";// 普通版
    }

    public FileOpenUtil() {

    }

    /**
     * 打开文件
     */
    public void openFile(String path) {

        Boolean useWps = Hawk.get(Constants.SettingCode.OPEN_FILE_WITH_WPS, false);

        if (useWps) {
            if (!openFileWithWps(path)) {
                openFileWithTbs(path);
            }
        } else {
            openFileWithTbs(path);
        }
    }

    /**
     * 使用Wps打开文档
     */
    private boolean openFileWithWps(String path) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        if (Hawk.get(Constants.SettingCode.READ_ONLY_TYPE, true)) {
            // 打开模式
            bundle.putString(WpsModel.OPEN_MODE, OpenMode.READ_ONLY);
        } else {
            bundle.putString(WpsModel.OPEN_MODE, OpenMode.NORMAL);

        }
        // 关闭时是否发送广播
        bundle.putBoolean(WpsModel.SEND_CLOSE_BROAD, true);
        // 第三方应用的包名，用于对改应用合法性的验证
        bundle.putString(WpsModel.THIRD_PACKAGE, App.getContext().getPackageName());
        // 清除打开记录
        bundle.putBoolean(WpsModel.CLEAR_TRACE, true);
        // bundle.putBoolean(CLEAR_FILE, true); //关闭后删除打开文件
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setClassName(PackageName.NORMAL, ClassName.NORMAL);

        File file = new File(path);
        if (file == null || !file.exists()) {
            return false;
        }

        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        intent.putExtras(bundle);
        try {
            MyActivityManager.getInstance().getCurrentActivity().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 使用腾讯Tbs打开文档
     *
     * @param path
     */
    private void openFileWithTbs(String path) {

        if (MyActivityManager.getInstance().getCurrentActivity() != null) {
            Intent intent = new Intent(MyActivityManager.getInstance().getCurrentActivity(), ShowFileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle bundle = new Bundle();
            bundle.putSerializable("FilePath", path);
            intent.putExtras(bundle);
            MyActivityManager.getInstance().getCurrentActivity().startActivity(intent);
        }
    }

    public static void showImage(String path) {
        ImageDialog imageDialog = ImageDialog.newInstance(path);
        imageDialog.show(((MainActivity) MyActivityManager.getInstance().getCurrentActivity()).getSupportFragmentManager(), "");
    }
}
