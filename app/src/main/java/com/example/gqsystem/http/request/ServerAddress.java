package com.example.gqsystem.http.request;


/**
 * 服务器地址
 *
 * @author devel
 */
public class ServerAddress {

    //    public static String API_DEFAULT_HOST = "http://192.168.3.31:8080/jeecg-boot/";
    public static String API_DEFAULT_HOST = "http://116.62.242.209:8088/";


    public static String getApiDefaultHost() {
        return API_DEFAULT_HOST;
    }

    public static void setApiDefaultHost(String IP) {
        API_DEFAULT_HOST = "http://" + IP + "/mobileInterface/";
    }

}
