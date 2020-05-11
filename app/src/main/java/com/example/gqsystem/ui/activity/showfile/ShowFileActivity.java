package com.example.gqsystem.ui.activity.showfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.gqsystem.R;
import com.example.gqsystem.base.BaseActivity;
import com.example.gqsystem.databinding.ActivityShowFileBinding;
import com.example.gqsystem.util.FileUtil;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.util.Objects;

/**
 * @author devel
 * 使用TBS打开文件
 */
public class ShowFileActivity extends BaseActivity<ActivityShowFileBinding, ShowFileViewModel> implements TbsReaderView.ReaderCallback {

    private TbsReaderView mTbsReaderView;

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
        if (bundle == null) {
            finish();
        } else {
            String mFilePath = bundle.getString("FilePath");

            //添加ReadView
            mTbsReaderView = new TbsReaderView(this, this);
            mDataBinding.tbsView.addView(mTbsReaderView, new RelativeLayout.LayoutParams(-1, -1));

            openFile(mFilePath);
        }
    }


    /**
     * 打开文件
     */
    public void openFile(String mFilePath) {

        Bundle bundle = new Bundle();
        bundle.putString("filePath", mFilePath);
        //缓存文件夹，该文件夹必须要有，否则将不能打开文件
        bundle.putString("tempPath", Objects.requireNonNull(getExternalCacheDir()).getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(mFilePath), false);
        if (result) {
            mTbsReaderView.openFile(bundle);
            Log.e("加载", "加载成功");
        } else {
            Log.e("加载", "加载失败");
            //使用手机上的其他方式打开文件
            File file = new File(mFilePath);
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
