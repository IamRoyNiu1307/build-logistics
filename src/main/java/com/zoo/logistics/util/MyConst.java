package com.zoo.logistics.util;

/**
 * 常数类
 */
public class MyConst {
    //高德地图API Web服务的key
    public static final String MAP_WEB_KEY = "553ac3eed11ec277ed5afe84e3d492aa";
    //高德地图API 微信小程序服务的key
    public static final String MAP_WX_KEY = "19ef95cf4326dfae068dae506181fd15";
    //地理解析api
    public static final String GRO_URL = "https://restapi.amap.com/v3/geocode/geo";
    //逆地理解析api
    public static final String REGRO_URL = "https://restapi.amap.com/v3/geocode/regeo";
    //高德地图 测量多点到目的点的距离
    public static final String DISTANCE_URL = "https://restapi.amap.com/v3/distance";

    //微信小程序appid
    public static final String APPID = "wxde6415f50b2d05c3";
    //微信小程序appsecret
    public static final String APPSECRET = "300bda0ea35e7f536f4d8125dac6783a";
    //获取微信Openid的请求地址
    public static String WxGetOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session";
}
