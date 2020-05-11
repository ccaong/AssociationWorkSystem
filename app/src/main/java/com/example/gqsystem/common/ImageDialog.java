package com.example.gqsystem.common;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.gqsystem.R;
import com.example.gqsystem.bean.response.InvoiceInfoBean;
import com.example.gqsystem.databinding.CompanyDialogInvoiceInfoBinding;
import com.example.gqsystem.databinding.ImageDialogBinding;
import com.example.gqsystem.http.request.ServerAddress;
import com.example.gqsystem.interfice.DownloadListener;
import com.example.gqsystem.util.CommonUtils;
import com.example.gqsystem.util.DownloadUtil;
import com.example.gqsystem.util.FileOpenUtil;
import com.example.gqsystem.util.FileUtil;
import com.example.gqsystem.util.ToastUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.runtime.Permission;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

/**
 * @author : devel
 * @date : 2020/3/6 10:30
 * @desc : 图片Dialog
 */
public class ImageDialog extends DialogFragment {
    private static final String IMAGE_PATH = "image_path";

    private ImageDialogBinding mDataBinding;
    private String fileName;

    public static ImageDialog newInstance(String data) {
        ImageDialog fragment = new ImageDialog();
        Bundle bundle = new Bundle();
        bundle.putString(IMAGE_PATH, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.inflate(inflater, R.layout.image_dialog, container, false);
        mDataBinding.setLifecycleOwner(this);
        return mDataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            fileName = getArguments().getString(IMAGE_PATH);
            String url = ServerAddress.getApiDefaultHost() + "download/downloadFile/" + fileName;
            mDataBinding.setImagePath(url);
        }
        mDataBinding.setImageDialog(this);
    }

    @Override
    public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);
        super.onResume();
    }


    public void downLoad() {

        if (CommonUtils.isStringEmpty(fileName)) {
            ToastUtils.showToast("没有文件可供下载！");
            return;
        }
        AndPermission.with(this)
                .runtime().permission(Permission.WRITE_EXTERNAL_STORAGE)
                .onGranted(permission -> downloadFile(fileName))
                .onDenied(permission -> ToastUtils.showToast("获取文件读取权限失败，请先授权！"))
                .start();
    }


    private void downloadFile(String filePath) {
        DownloadUtil downloadUtil = new DownloadUtil();
        downloadUtil.downloadImageFile(filePath, new DownloadListener() {
            @Override
            public void onStart() {
                Log.e("下载", "onStart");
            }

            @Override
            public void onProgress(int currentLength) {
                Log.e("下载", "onProgress" + currentLength);
            }

            @Override
            public void onFinish(String localPath) {
                Log.e("下载", "onFinish" + localPath);
                ToastUtils.showToast("下载完成！");
                dismiss();
            }

            @Override
            public void onFailure(String errorMsg) {
                Log.e("下载", "onFailure" + errorMsg);
                //下载失败，删除下载的文件
                FileUtil.deleteFile(filePath);
            }
        });
    }
}
