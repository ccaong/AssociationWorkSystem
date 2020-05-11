package com.example.gqsystem.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import com.example.gqsystem.App;
import com.example.gqsystem.manager.MyActivityManager;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import androidx.fragment.app.Fragment;


/**
 * @author devel
 */
public class PermissionCheckUtils {

    /**
     * 检查权限
     *
     * @param fragment
     * @return
     */
    public static Boolean requestPermission(Fragment fragment) {
        AtomicReference<Boolean> hasPermission = new AtomicReference<>(false);
        AndPermission.with(fragment)
                .runtime().permission(Permission.WRITE_EXTERNAL_STORAGE)
                .onDenied(permission -> hasPermission.set(true))
                .onDenied(permission -> hasPermission.set(false)).start();

        return hasPermission.get();
    }

    private static void showOpenAppSettingDialog() {
        Activity topActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (topActivity == null) {
            return;
        }
        new AlertDialog.Builder(topActivity)
                .setTitle("权限提示")
                .setMessage("我们需要您拒绝的某些权限或系统无法应用失败，请手动设置为页面授权，否则该功能无法正常使用！")
                .setPositiveButton(android.R.string.ok, (DialogInterface.OnClickListener) (dialog, which) -> launchAppDetailsSettings())
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }


    /**
     * Launch the application's details settings.
     */
    public static void launchAppDetailsSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + App.getContext().getPackageName()));
        App.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}
