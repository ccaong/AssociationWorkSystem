package com.example.gqsystem;

import android.content.Context;

import com.example.gqsystem.http.data.HttpResponseInterface;
import com.example.gqsystem.http.request.ServerAddress;
import com.example.gqsystem.http.request.HttpFactory;
import com.google.gson.Gson;

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

        setHttpConfig();
    }

    public static Context getContext() {
        return context;
    }


    /**
     * 请求配置
     */
    public static void setHttpConfig() {
        HttpFactory.HTTP_HOST_URL = ServerAddress.getApiDefaultHost();
        HttpFactory.httpResponseInterface = new HttpResponseInterface() {
            @Override
            public String getResponseData(Gson gson, String response) {

                return response;

//                HttpBaseResponse httpResponse = gson.fromJson(response, HttpBaseResponse.class);
//                if (httpResponse.code != 0 && httpResponse.code != 104) {
//                    throw new HttpException(httpResponse.msg);
//                }
//
//                return gson.toJson(httpResponse.extend);

            }
        };
    }

}
