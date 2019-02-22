package com.zoo.logistics.api;

import com.zoo.logistics.entity.Station;
import com.zoo.logistics.util.HttpRequestUtil;
import com.zoo.logistics.util.MyConst;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.TreeSet;

public class Distance {

    /**
     * 获取距离目标点最近的站点
     * @param stationList 站点列表
     * @param location 目标点,String类型表示的经纬度
     * @return 最近的站点
     */
    public static Station getNearestStation(List<Station> stationList,String location){
        TreeSet<Station> stationSet = new TreeSet<Station>();

        String posStr = "";

        for(Station current : stationList){
            if(!"".equals(posStr)){
                posStr+="|";
            }
            posStr+=current.getPosLng()+","+current.getPosLat();
        }

        String param = "origins="+posStr+"&destination="+location+"&type=1&output=json&key="+MyConst.MAP_WEB_KEY;

        String resultJson = HttpRequestUtil.sendGet(MyConst.DISTANCE_URL, param);

        JSONObject jsonObject = new JSONObject(resultJson);

//        System.out.println(jsonObject.toString());

        JSONArray results = jsonObject.getJSONArray("results");

        for(int i = 0;i<results.length();i++){
            stationList.get(i).setIndex(results.getJSONObject(i).getInt("distance"));
            stationSet.add(stationList.get(i));
        }

        return stationSet.first();
    }

    /**
     * 计算两点(经纬度)之间的直线距离
     * @param origins 起点
     * @param destination 终点
     * @return 两点间的直线距离
     */
    public static int getDistance(String origins,String destination){
        String param = "origins="+origins+"&destination="+destination+"&type=1&output=json&key="+MyConst.MAP_WEB_KEY;
        String resultJson = HttpRequestUtil.sendGet(MyConst.DISTANCE_URL, param);
        JSONObject jsonObject = new JSONObject(resultJson);
        JSONArray results = jsonObject.getJSONArray("results");
        return results.getJSONObject(0).getInt("distance");
    }

}
