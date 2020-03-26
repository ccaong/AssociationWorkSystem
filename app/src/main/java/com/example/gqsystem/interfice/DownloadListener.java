package com.example.gqsystem.interfice;

/**
 * @author : devel
 * @date : 2020/3/24 17:16
 * @desc :
 */
public interface DownloadListener {
    /**
     * 开始下载
     */
    void onStart();

    /**
     * 下载中
     *
     * @param currentLength 进度
     */
    void onProgress(int currentLength);

    /**
     * 下载完成
     *
     * @param localPath 文件保存地址
     */
    void onFinish(String localPath);

    /**
     * 下载失败
     *
     * @param errorMsg 错误信息
     */
    void onFailure(String errorMsg);
}
