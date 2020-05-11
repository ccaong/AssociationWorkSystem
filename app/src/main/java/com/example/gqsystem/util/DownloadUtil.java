package com.example.gqsystem.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.example.gqsystem.App;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.interfice.DownloadListener;
import com.example.gqsystem.manager.ThreadManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import androidx.annotation.NonNull;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * @author : devel
 * @date : 2020/3/24 17:14
 * @desc : 文件下载
 */
public class DownloadUtil {

    private static final String TAG = "DownloadUtil";
    private static final String PATH_DOWNLOAD_FILE = App.getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) + "/DownloadFile";
    private File mFile;
    private ThreadManager.ThreadPool threadPool;
    /**
     * 下载到本地的文件路径
     */
    private String mFilePath;

    public DownloadUtil() {
        threadPool = ThreadManager.getThreadPool();
    }

    public void downloadFile(String fileName, final DownloadListener downloadListener) {

        //通过fileName得到文件并创建本地文件
        if (FileUtil.createOrExistsDir(PATH_DOWNLOAD_FILE)) {
            mFilePath = PATH_DOWNLOAD_FILE + "/" + fileName;
        }
        if (TextUtils.isEmpty(mFilePath)) {
            return;
        }

        //建立一个文件
        mFile = new File(mFilePath);
        if (!FileUtil.isFileExists(mFile) && FileUtil.createOrExistsFile(mFile)) {
            String downLoadUrl = "download/downloadFile/" + fileName;

            downloadListener.onProgress(1);
            Call<ResponseBody> mCall = HttpRequest.getInstance().downloadFileWithFileName(downLoadUrl);
            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                    //下载文件
                    threadPool.execute(() -> writeFile2Disk(response, mFile, downloadListener, "file"));
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //下载失败
                    downloadListener.onFailure(t.getMessage());
                }
            });
        } else {
            //本地已经存在该文件
            downloadListener.onFinish(mFilePath);
        }
    }

    /**
     * 将下载的文件写入本地存储
     *
     * @param response
     * @param file
     * @param downloadListener
     */
    private void writeFile2Disk(Response<ResponseBody> response, File file, DownloadListener downloadListener, String type) {

        if (response.body() == null) {
            downloadListener.onFailure("文件下载失败");
            return;
        }

        if (response.body().contentLength() == 0) {
            downloadListener.onFinish(mFilePath);
            return;
        }

        downloadListener.onStart();
        long currentLength = 0;
        OutputStream os = null;
        //获取下载输入流
        InputStream is = response.body().byteStream();
        long totalLength = response.body().contentLength();

        try { //输出流
            os = new FileOutputStream(file);
            int len;
            byte[] buff = new byte[1024];
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
                currentLength += len;
                Log.e(TAG, "当前进度: " + currentLength);
                //计算当前下载百分比
                downloadListener.onProgress((int) (100 * currentLength / totalLength));

                if ((int) (100 * currentLength / totalLength) == 100) {
                    //下载完成
                    downloadListener.onFinish(mFilePath);
                }
            }
        } catch (IOException e) {
            downloadListener.onFailure(e.getMessage());
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    //关闭输出流
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    //关闭输入流
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if ("Image".equals(type)) {
                saveToSystemGallery(file,file.getName());
            }
        }
    }


    public void downloadImageFile(String fileName, final DownloadListener downloadListener) {

        String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SDCLI/WeChatCode/";

        //通过fileName得到文件并创建本地文件
        if (FileUtil.createOrExistsDir(dir)) {
            mFilePath = dir + "/" + fileName;
        }
        if (TextUtils.isEmpty(mFilePath)) {
            return;
        }

        //建立一个文件
        mFile = new File(mFilePath);
        if (!FileUtil.isFileExists(mFile) && FileUtil.createOrExistsFile(mFile)) {
            String downLoadUrl = "download/downloadFile/" + fileName;

            downloadListener.onProgress(1);
            Call<ResponseBody> mCall = HttpRequest.getInstance().downloadFileWithFileName(downLoadUrl);
            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                    //下载文件
                    threadPool.execute(() -> writeFile2Disk(response, mFile, downloadListener, "Image"));
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    //下载失败
                    downloadListener.onFailure(t.getMessage());
                }
            });
        } else {
            //本地已经存在该文件
            downloadListener.onFinish(mFilePath);
        }
    }


    public static void saveToSystemGallery(File file, String fileName) {
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(App.getContext().getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        App.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(file.getAbsolutePath())));
    }
}
