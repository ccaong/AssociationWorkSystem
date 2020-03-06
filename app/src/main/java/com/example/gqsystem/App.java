package com.example.gqsystem;

import android.content.Context;

import com.example.gqsystem.http.data.HttpBaseResponse;
import com.example.gqsystem.http.data.HttpResponseInterface;
import com.example.gqsystem.http.httptool.HttpException;
import com.example.gqsystem.http.request.ServerAddress;
import com.example.gqsystem.http.request.HttpFactory;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshFooterCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator;
import com.scwang.smart.refresh.layout.listener.DefaultRefreshInitializer;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDexApplication;

/**
 * @author : devel
 * @date : 2020/2/19 14:37
 * @desc :
 */
public class App extends MultiDexApplication {
    private static Context context;
    public static String Mac = "";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        init();
    }

    public static Context getContext() {
        return context;
    }


    public void init() {
        setHttpConfig();
        Hawk.init(context).build();
    }

    /**
     * 请求配置
     */
    public static void setHttpConfig() {
        HttpFactory.HTTP_HOST_URL = ServerAddress.getApiDefaultHost();
        HttpFactory.httpResponseInterface = new HttpResponseInterface() {
            @Override
            public String getResponseData(Gson gson, String response) {
                HttpBaseResponse httpResponse = gson.fromJson(response, HttpBaseResponse.class);
                if (httpResponse.code != 0 && httpResponse.code != 104) {
                    throw new HttpException(httpResponse.msg);
                }
                return gson.toJson(httpResponse.extend);
            }
        };
    }

    /**
     * 设置上拉加载和下拉刷新的样式
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                //全局设置主题颜色
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });

        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableFooterFollowWhenLoadFinished(true);
                layout.setEnableAutoLoadMore(false);
            }
        });
    }

}
