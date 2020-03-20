package com.example.gqsystem.http.data;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 数据封装类型
 *
 * @author devel
 */
public class HttpBaseResponse<T> implements Serializable {

    @Expose
    public int code;

    @Expose
    public String message;

    @Expose
    public Boolean success;

    @Expose
    public Long timestamp;

    @Expose
    public T result;

}