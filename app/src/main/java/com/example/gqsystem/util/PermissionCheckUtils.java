package com.example.gqsystem.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.example.gqsystem.App;
import com.example.gqsystem.manager.MyActivityManager;

import java.util.List;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2018/01/06
 *     desc  : helper about permission
 * </pre>
 */
public class PermissionCheckUtils {

//    public static void requestCamera(final OnPermissionGrantedListener listener) {
//        request(listener, PermissionConstants.CAMERA);
//    }
//
//    public static void requestStorage(final OnPermissionGrantedListener listener) {
//        request(listener, PermissionConstants.STORAGE);
//    }
//
//    public static void requestPhone(final OnPermissionGrantedListener listener) {
//        request(listener, PermissionConstants.PHONE);
//    }
//
//    public static void requestPhone(final OnPermissionGrantedListener grantedListener,
//                                    final OnPermissionDeniedListener deniedListener) {
//        request(grantedListener, deniedListener, PermissionConstants.PHONE);
//    }
//
//    public static void requestSms(final OnPermissionGrantedListener listener) {
//        request(listener, PermissionConstants.SMS);
//    }
//
//    public static void requestLocation(final OnPermissionGrantedListener listener) {
//        request(listener, PermissionConstants.LOCATION);
//    }
//
//    private static void request(final OnPermissionGrantedListener grantedListener,
//                                final @PermissionConstants.Permission String... permissions) {
//        request(grantedListener, null, permissions);
//    }

//    private static void request(final OnPermissionGrantedListener grantedListener,
//                                final OnPermissionDeniedListener deniedListener,
//                                final @PermissionConstants.Permission String... permissions) {
//        PermissionUtils.permission(permissions)
//                .rationale(new PermissionUtils.OnRationaleListener() {
//                    @Override
//                    public void rationale(PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
//                        showRationaleDialog(shouldRequest);
//                    }
//                })
//                .callback(new PermissionUtils.FullCallback() {
//                    @Override
//                    public void onGranted(List<String> permissionsGranted) {
//                        if (grantedListener != null) {
//                            grantedListener.onPermissionGranted();
//                        }
//                    }
//
//                    @Override
//                    public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
//                        if (!permissionsDeniedForever.isEmpty()) {
//                            showOpenAppSettingDialog();
//                        }
//                        if (deniedListener != null) {
//                            deniedListener.onPermissionDenied();
//                        }
//                        LogUtils.d(permissionsDeniedForever, permissionsDenied);
//                    }
//                })
//                .request();
//    }

    public interface OnPermissionGrantedListener {
        void onPermissionGranted();
    }

    public interface OnPermissionDeniedListener {
        void onPermissionDenied();
    }

//    private static void showRationaleDialog(final PermissionUtils.OnRationaleListener.ShouldRequest shouldRequest) {
//        Activity topActivity = ActivityUtils.getTopActivity();
//        if (topActivity == null) return;
//        new AlertDialog.Builder(topActivity)
//                .setTitle("权限提示")
//                .setMessage("请同意授权，否则该功能无法正常使用！")
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        shouldRequest.again(true);
//                    }
//                })
//                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        shouldRequest.again(false);
//                    }
//                })
//                .setCancelable(false)
//                .create()
//                .show();
//
//    }

    private static void showOpenAppSettingDialog() {
        Activity topActivity = MyActivityManager.getInstance().getCurrentActivity();
        if (topActivity == null) return;
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
        intent.setData(Uri.parse("package:" +   App.getContext().getPackageName()));
        App.getContext().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}