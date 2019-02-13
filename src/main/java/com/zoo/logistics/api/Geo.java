package com.zoo.logistics.api;

import com.zoo.logistics.util.HttpRequestUtil;
import com.zoo.logistics.util.MyConst;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 地理解析
 */
@Component
public class Geo {

    /**
     * 高德地图地理解析api
     * @param address 地址 填写结构化地址信息:省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码
     * @return 根据地址解析的经纬度
     */
    public static String geo(String address){
        String param = "address=" + address + "&output=json" + "&key=" + MyConst.MAP_WEB_KEY;
        String result = HttpRequestUtil.sendGet(MyConst.GRO_URL, param);

        JSONObject jsonObject = new JSONObject(result);

        String location = jsonObject.getJSONArray("geocodes").getJSONObject(0).getString("location");

        return location;
    }
}
