package com.example.gqsystem.ui.activity.showfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivityShowFileBinding;
import com.example.gqsystem.util.FileUtil;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;

/**
 * @author devel
 * 打开文件
 */
public class ShowFileActivity extends BaseActivity<ActivityShowFileBinding, ShowFileViewModel> implements TbsReaderView.ReaderCallback {

    TbsReaderView mTbsReaderView;
    private String mFilePath;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_show_file;
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void bindViewModel() {

    }

    @Override
    protected void init() {
        QbSdk.initX5Environment(this, null);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mFilePath = bundle.getString("FilePath");

        //添加ReadView
        mTbsReaderView = new TbsReaderView(this, this);
        mDataBinding.tbsView.addView(mTbsReaderView, new RelativeLayout.LayoutParams(-1, -1));

        openFile(mFilePath);
    }

    /**
     * 打开文件
     *
     * @param mFilePath
     */
    public void openFile(String mFilePath) {
        QbSdk.openFileReader(this, mFilePath, null, s -> {
            Log.e("打开文件", "" + s);
        });
    }


    /**
     * 打开文件
     * 第二种方法
     */
    public void initData() {

        //打开文件
        Bundle bundle = new Bundle();
        bundle.putString("filePath", getLocalFile().getPath());
        bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(mFilePath), false);
        if (result) {
            mTbsReaderView.openFile(bundle);
            Log.e("加载", "加载成功");
        } else {
            Log.e("加载", "加载失败");
            File file = new File(getLocalFile().getPath());
            if (file.exists()) {
                Intent openIntent = new Intent();
                openIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                String type = FileUtil.getMIMEType(file);
                // 设置intent的data和Type属性。
                openIntent.setDataAndType(Uri.fromFile(file), type);
                // 跳转
                startActivity(openIntent);
                finish();
            }
        }
    }

    /**
     * 获取文件后缀名，也就是文件类型
     *
     * @param fileName
     * @return
     */
    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private File getLocalFile() {
        return new File(mFilePath);
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mTbsReaderView.onStop();
    }
}
