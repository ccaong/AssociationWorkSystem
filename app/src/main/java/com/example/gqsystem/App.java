package com.example.gqsystem;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.example.gqsystem.http.data.HttpBaseResponse;
import com.example.gqsystem.http.httptool.HttpException;
import com.example.gqsystem.http.request.HttpFactory;
import com.example.gqsystem.http.request.ServerAddress;
import com.example.gqsystem.manager.MyActivityManager;
import com.orhanobut.hawk.Hawk;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.tencent.smtt.sdk.QbSdk;

import androidx.multidex.MultiDexApplication;
import cn.jpush.android.api.JPushInterface;

/**
 * @author : devel
 * @date : 2020/2/19 14:37
 * @desc :
 */
public class App extends MultiDexApplication {

    private static Context context;
    public static boolean isDownloadFile;
    public boolean tbsInitSuccess;

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
        isDownloadFile = false;
        setHttpConfig();
        //初始化Hawk
        Hawk.init(context).build();
        // 设置开启日志,发布时请关闭日志
        JPushInterface.setDebugMode(false);
        // 初始化 JPush
        JPushInterface.init(this);

        initActivityManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                //x5内核初始化完成回调接口，此接口回调并表示已经加载起来了x5，有可能特殊情况下x5内核加载失败，切换到系统内核。
            }

            @Override
            public void onViewInitFinished(boolean b) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.e("print","加载内核是否成功:"+b);
            }
        });
    }

    /**
     * 管理Activity
     */
    private void initActivityManager() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }

    /**
     * 请求配置
     */
    public static void setHttpConfig() {
        HttpFactory.HTTP_HOST_URL = ServerAddress.getApiDefaultHost();
        HttpFactory.httpResponseInterface = (gson, response) -> {
            if (isDownloadFile) {
                isDownloadFile = false;
                return response;
            } else {
                HttpBaseResponse httpResponse = gson.fromJson(response, HttpBaseResponse.class);
                if (httpResponse.code != 200) {
                    throw new HttpException(httpResponse.message);
                }
                return gson.toJson(httpResponse.result);
            }
        };
    }

    /**
     * 设置上拉加载和下拉刷新的样式
     */
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            return new ClassicsHeader(context);
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });

        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            layout.setEnableFooterFollowWhenLoadFinished(true);
            layout.setEnableAutoLoadMore(false);
        });
    }

}
