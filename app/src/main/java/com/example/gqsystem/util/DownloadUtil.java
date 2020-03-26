package com.example.gqsystem.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.example.gqsystem.App;
import com.example.gqsystem.http.request.ApiAddress;
import com.example.gqsystem.http.request.HttpRequest;
import com.example.gqsystem.interfice.DownloadListener;
import com.example.gqsystem.manager.ThreadManager;

import java.io.File;
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
    private ApiAddress mApi;
    private Call<ResponseBody> mCall;
    private File mFile;
    private ThreadManager.ThreadPool threadPool;
    /**
     * 下载到本地的文件路径
     */
    private String mFilePath;

    public DownloadUtil() {
        if (mApi == null) {
            //初始化网络请求接口
            mApi = HttpRequest.getInstance();
        }
        threadPool = ThreadManager.getThreadPool();
    }

    public void downloadFile(String fileName, final DownloadListener downloadListener) {
        App.isDownloadFile = true;
        String name = fileName;
        //通过Url得到文件并创建本地文件
        if (FileUtil.createOrExistsDir(PATH_DOWNLOAD_FILE)) {
            mFilePath = PATH_DOWNLOAD_FILE + "/" + name;
        }
        if (TextUtils.isEmpty(mFilePath)) {
            Log.e(TAG, "存储路径为空");
            return;
        }
        //建立一个文件
        mFile = new File(mFilePath);
        if (!FileUtil.isFileExists(mFile) && FileUtil.createOrExistsFile(mFile)) {
            //文件不存在，&& 创建成功
            if (mApi == null) {
                Log.e(TAG, "下载接口为空");
                return;
            }

            mCall = mApi.downloadFileWithFileName(fileName);
            mCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                    //下载文件
                    threadPool.execute(() -> writeFile2Disk(response, mFile, downloadListener));
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
    private void writeFile2Disk(Response<ResponseBody> response, File file, DownloadListener downloadListener) {
        if (response.body() == null) {
            downloadListener.onFailure("文件下载失败");
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
        }
    }
}
