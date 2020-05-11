package com.example.gqsystem.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.example.gqsystem.App;

import java.io.File;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * @author : devel
 * @date : 2020/2/18 15:29
 * @desc :
 */
public class MapUtil {

    public static final String PN_GAODE_MAP = "com.autonavi.minimap";// 高德地图包名
    public static final String PN_BAIDU_MAP = "com.baidu.BaiduMap"; // 百度地图包名
    public static final String PN_TENCENT_MAP = "com.tencent.map"; // 腾讯地图包名


    /**
     * 开始导航
     *
     * @param address 终点名称 必填
     */
    public static void startNavigation(String address) {
        if (isGdMapInstalled()) {
            openGaoDeNavigation(address);
        } else {
            if (isBaiduMapInstalled()) {
                openBaiDuNavigation(address);
            } else {
                if (isTencentMapInstalled()) {
                    openTencentMap(address);
                } else {
                    //手机上没有导航软件
                    Toast.makeText(App.getContext(), "抱歉，您的手机上没有可用的地图软件，请先下载相关应用！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    /**
     * 打开高德地图导航功能
     *
     * @param address 终点名称 必填
     */
    private static void openGaoDeNavigation(String address) {
        String uriString = null;
        StringBuilder builder = new StringBuilder("androidamap://poi?sourceApplication=maxuslife");
        builder.append("&keywords=").append(address)
                .append("&dev=0");
        uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_GAODE_MAP);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(uriString));
        App.getContext().startActivity(intent);
    }


    /**
     * 打开百度地图导航功能
     *
     * @param address 终点名称
     */
    private static void openBaiDuNavigation(String address) {
        String uriString = null;
        StringBuilder builder = new StringBuilder("baidumap://map/geocoder?src=andr.baidu.openAPIdemo&");
        builder.append("address=").append(address);
        uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_BAIDU_MAP);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(uriString));
        App.getContext().startActivity(intent);
    }

    /**
     * 打开腾讯地图
     *
     * @param address 终点名称 必填
     */
    private static void openTencentMap(String address) {
        String uriString = null;
        StringBuilder builder = new StringBuilder("qqmap://map/search");
        builder.append("&keyword=").append(address);
        uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_TENCENT_MAP);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse(uriString));
        App.getContext().startActivity(intent);
    }

    /**
     * 开始导航
     *
     * @param dlat  终点纬度
     * @param dlon  终点经度
     * @param dname 终点名称 必填
     */
    public static void startNavigation(double dlat, double dlon, String dname) {
        if (isGdMapInstalled()) {
            openGaoDeNavi(App.getContext(), 0, 0, null, dlat, dlon, dname);
        } else {
            if (isBaiduMapInstalled()) {
                openBaiDuNavi(App.getContext(), 0, 0, null, dlat, dlon, dname);
            } else {
                if (isTencentMapInstalled()) {
                    openTencentMap(App.getContext(), 0, 0, null, dlat, dlon, dname);
                } else {
                    //手机上没有导航软件
                    Toast.makeText(App.getContext(), "没有导航软件！", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    /**
     * 0
     * 检查地图应用是否安装
     *
     * @return
     */
    private static boolean isGdMapInstalled() {
        return isInstallPackage(PN_GAODE_MAP);
    }

    private static boolean isBaiduMapInstalled() {
        return isInstallPackage(PN_BAIDU_MAP);
    }

    private static boolean isTencentMapInstalled() {
        return isInstallPackage(PN_TENCENT_MAP);
    }

    private static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }


    /**
     * 打开高德地图导航功能
     *
     * @param context
     * @param slat    起点纬度
     * @param slon    起点经度
     * @param sname   起点名称 可不填（0,0，null）
     * @param dlat    终点纬度
     * @param dlon    终点经度
     * @param dname   终点名称 必填
     */
    public static void openGaoDeNavi(Context context, double slat, double slon, String sname, double dlat, double dlon, String dname) {
        String uriString = null;
        StringBuilder builder = new StringBuilder("amapuri://route/plan?sourceApplication=maxuslife");
        if (slat != 0) {
            builder.append("&sname=").append(sname)
                    .append("&slat=").append(slat)
                    .append("&slon=").append(slon);
        }
        builder.append("&dlat=").append(dlat)
                .append("&dlon=").append(dlon)
                .append("&dname=").append(dname)
                .append("&dev=0")
                .append("&t=0");
        uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_GAODE_MAP);
        intent.setData(Uri.parse(uriString));
        context.startActivity(intent);
    }

    /**
     * 打开腾讯地图
     * params 参考http://lbs.qq.com/uri_v1/guide-route.html
     *
     * @param context
     * @param slat    起点纬度
     * @param slon    起点经度
     * @param sname   起点名称 可不填（0,0，null）
     * @param dlat    终点纬度
     * @param dlon    终点经度
     * @param dname   终点名称 必填
     *                驾车：type=drive，policy有以下取值
     *                0：较快捷
     *                1：无高速
     *                2：距离
     *                policy的取值缺省为0
     *                &from=" + dqAddress + "&fromcoord=" + dqLatitude + "," + dqLongitude + "
     */
    private static void openTencentMap(Context context, double slat, double slon, String sname, double dlat, double dlon, String dname) {
        String uriString = null;
        StringBuilder builder = new StringBuilder("qqmap://map/routeplan?type=drive&policy=0&referer=zhongshuo");
        if (slat != 0) {
            builder.append("&from=").append(sname)
                    .append("&fromcoord=").append(slat)
                    .append(",")
                    .append(slon);
        }
        builder.append("&to=").append(dname)
                .append("&tocoord=").append(dlat)
                .append(",")
                .append(dlon);
        uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_TENCENT_MAP);
        intent.setData(Uri.parse(uriString));
        context.startActivity(intent);
    }

    /**
     * 打开百度地图导航功能(默认坐标点是高德地图，需要转换)
     *
     * @param context
     * @param slat    起点纬度
     * @param slon    起点经度
     * @param sname   起点名称 可不填（0,0，null）
     * @param dlat    终点纬度
     * @param dlon    终点经度
     * @param dname   终点名称 必填
     */
    private static void openBaiDuNavi(Context context, double slat, double slon, String sname, double dlat, double dlon, String dname) {
        String uriString = null;
        //终点坐标转换
        LatLng destination = new LatLng(dlat, dlon);
        LatLng destinationLatLng = GCJ02ToBD09(destination);
        dlat = destinationLatLng.latitude;
        dlon = destinationLatLng.longitude;

        StringBuilder builder = new StringBuilder("baidumap://map/direction?mode=driving&");
        if (slat != 0) {
            //起点坐标转换

            LatLng origin = new LatLng(slat, slon);
            LatLng originLatLng = GCJ02ToBD09(origin);
            slat = originLatLng.latitude;
            slon = originLatLng.longitude;

            builder.append("origin=latlng:")
                    .append(slat)
                    .append(",")
                    .append(slon)
                    .append("|name:")
                    .append(sname);
        }
        builder.append("&destination=latlng:")
                .append(dlat)
                .append(",")
                .append(dlon)
                .append("|name:")
                .append(dname);
        uriString = builder.toString();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(PN_BAIDU_MAP);
        intent.setData(Uri.parse(uriString));
        context.startActivity(intent);
    }


    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换
     * 即谷歌、高德 转 百度
     *
     * @param latLng
     * @returns 需要百度地图的BaiduLBS_Android.jar包
     */
    public static LatLng GCJ02ToBD09(LatLng latLng) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double z = Math.sqrt(latLng.longitude * latLng.longitude + latLng.latitude * latLng.latitude) + 0.00002 * Math.sin(latLng.latitude * x_pi);
        double theta = Math.atan2(latLng.latitude, latLng.longitude) + 0.000003 * Math.cos(latLng.longitude * x_pi);
        double bd_lat = z * Math.sin(theta) + 0.006;
        double bd_lng = z * Math.cos(theta) + 0.0065;
        return new LatLng(bd_lat, bd_lng);
    }


    /**
     * 百度转高德
     *
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public static double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }

    /**
     * 高德、腾讯转百度
     *
     * @param gd_lon
     * @param gd_lat
     * @return
     */
    private static double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
        double[] bd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gd_lon, y = gd_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
        bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
        return bd_lat_lon;
    }

    /**
     * 百度坐标系 (BD-09) 与 火星坐标系 (GCJ-02)的转换
     * 即 百度 转 谷歌、高德
     *
     * @param latLng
     * @returns 使用此方法需要下载导入百度地图的BaiduLBS_Android.jar包
     */
    public static LatLng BD09ToGCJ02(LatLng latLng) {
        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
        double x = latLng.longitude - 0.0065;
        double y = latLng.latitude - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gg_lat = z * Math.sin(theta);
        double gg_lng = z * Math.cos(theta);
        return new LatLng(gg_lat, gg_lng);
    }

}
