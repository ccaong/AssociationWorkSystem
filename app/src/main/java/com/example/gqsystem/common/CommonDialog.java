package com.example.gqsystem.common;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * @author : devel
 * @date : 2020/3/6 11:24
 * @desc : 弹窗
 */
public class CommonDialog extends DialogFragment {

    private static final String PARAM_TITLE_KEY = "dialog_title";
    private static final String PARAM_MESSAGE_KEY = "dialog_message";
    private static final String PARAM_CONFIRM_KEY = "dialog_confirm";
    private static final String PARAM_CANCEL_KEY = "dialog_cancel";

    /**
     * 弹窗位置
     */
    enum DialogGravity {

        /**
         * 正常
         */
        NORMAL,
        /**
         * 底部
         */
        BOTTOM
    }


    public static CommonDialog newInstance(String title, String message) {
        return CommonDialog.newInstance(title, message, "确定", "取消");
    }

    public static CommonDialog newInstance(String title, String message, String confirm) {
        CommonDialog fragment = new CommonDialog();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_TITLE_KEY, title);
        bundle.putString(PARAM_MESSAGE_KEY, message);
        bundle.putString(PARAM_CONFIRM_KEY, confirm);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static CommonDialog newInstance(String title, String message, String confirm, String cancel) {
        CommonDialog fragment = new CommonDialog();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM_TITLE_KEY, title);
        bundle.putString(PARAM_MESSAGE_KEY, message);
        bundle.putString(PARAM_CONFIRM_KEY, confirm);
        bundle.putString(PARAM_CANCEL_KEY, cancel);
        fragment.setArguments(bundle);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String title = "标题";
        String message = "信息";
        String confirm = "确定";
        String cancel = "取消";
        if (getArguments() != null) {
            title = getArguments().getString(PARAM_TITLE_KEY);
            message = getArguments().getString(PARAM_MESSAGE_KEY);
            confirm = getArguments().getString(PARAM_CONFIRM_KEY);
            cancel = getArguments().getString(PARAM_CANCEL_KEY);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) {
                    listener.onClickComplete();
                }
            }
        });
        builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        return builder.create();
    }


    /**
     * 底部弹窗
     *
     * @return
     */
    private Dialog createBootomDialog() {

        String title = "标题";
        String message = "信息";
        String confirm = "确定";
        String cancel = "取消";
        if (getArguments() != null) {
            title = getArguments().getString(PARAM_TITLE_KEY);
            message = getArguments().getString(PARAM_MESSAGE_KEY);
            confirm = getArguments().getString(PARAM_CONFIRM_KEY);
            cancel = getArguments().getString(PARAM_CANCEL_KEY);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (listener != null) {
                    listener.onClickComplete();
                }
            }
        });
        builder.setNegativeButton(cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        AlertDialog alertDialog = builder.create();
        // 设置宽度为屏宽、位置在屏幕底部
        Window window = alertDialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.white);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        return alertDialog;
    }


    private ConfirmListener listener;

    /**
     * 点击确定按钮
     */
    public void setConfirmClickListener(ConfirmListener confirmClickListener) {
        this.listener = confirmClickListener;
    }


    public interface ConfirmListener {

        /**
         * 点击确定
         */
        void onClickComplete();
    }

}
