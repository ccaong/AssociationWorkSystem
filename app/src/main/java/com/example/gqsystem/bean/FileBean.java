package com.example.gqsystem.bean;

/**
 * @author : devel
 * @date : 2020/3/27 17:29
 * @desc : 文件
 */
public class FileBean {

    private int position;

    private String fileTitle;

    private String fileName;

    private String fileType;

    private int downloadProgress;

    public FileBean(String fileName, String fileType, int downloadProgress) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.downloadProgress = downloadProgress;
    }

    public FileBean(int pos, String title, String fileName, String fileType, int downloadProgress) {
        this.position = pos;
        this.fileTitle = title;
        this.fileName = fileName;
        this.fileType = fileType;
        this.downloadProgress = downloadProgress;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        fileTitle = fileTitle;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getDownloadProgress() {
        return downloadProgress;
    }

    public void setDownloadProgress(int downloadProgress) {
        this.downloadProgress = downloadProgress;
    }
}
