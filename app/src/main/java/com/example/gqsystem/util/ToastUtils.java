package com.example.gqsystem.util;

import android.widget.Toast;

import com.example.gqsystem.App;

/**
 * @author devel
 */
public class ToastUtils {

    private static Toast mShortToast;
    private static Toast mLongToast;


    public static void showToast(String message) {
        if (mShortToast == null) {
            mShortToast = Toast.makeText(App.getContext(), message, Toast.LENGTH_SHORT);
        }
        mShortToast.setText(message);
        mShortToast.show();

    }

    public static void showLongToast(String message) {
        if (mLongToast == null) {
            mLongToast = Toast.makeText(App.getContext(), message, Toast.LENGTH_LONG);
        }
        mLongToast.setText(message);
        mLongToast.show();
    }

}

