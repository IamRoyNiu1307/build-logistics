package com.zoo.logistics.api;

import com.zoo.logistics.util.HttpRequestUtil;
import com.zoo.logistics.util.MyConst;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 逆地理解析
 */
public class ReGeo {

    /**
     * 高德地图逆地理解析api
     * @param lng 经度
     * @param lat 维度
     * @return 解析后的地址
     */
    public static Map reGeo(BigDecimal lng,BigDecimal lat){
        return reGeo(lng.toString(),lat.toString());
    }

    /**
     * 高德地图逆地理解析api
     * @param lng 经度
     * @param lat 维度
     * @return 解析后的地址
     */
    public static Map reGeo(String lng, String lat){

        Map map = new HashMap();

        String param = "location=" + lng + "," + lat + "&output=json" + "&key=" + MyConst.MAP_WEB_KEY;
        String result = HttpRequestUtil.sendGet(MyConst.REGRO_URL, param);

        JSONObject jsonObject = new JSONObject(result);

        String address = jsonObject.getJSONObject("regeocode").getString("formatted_address");

        String province = jsonObject.getJSONObject("regeocode").getJSONObject("addressComponent").getString("province");
        if(province.endsWith("市")){
            map.put("province",province);
            map.put("city",province);
        }else {
            String city = jsonObject.getJSONObject("regeocode").getJSONObject("addressComponent").getString("city");
            map.put("province",province);
            map.put("city",city);
        }

        map.put("formattedAddress",address);

        return map;
    }
}
