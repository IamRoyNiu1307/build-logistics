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
        //请求参数
        String param = "location=" + lng + "," + lat + "&output=json" + "&key=" + MyConst.MAP_WEB_KEY;
        //发送get请求，得到json格式的请求结果
        String result = HttpRequestUtil.sendGet(MyConst.REGRO_URL, param);

        JSONObject jsonObject = new JSONObject(result);

        String address = jsonObject.getJSONObject("regeocode").getString("formatted_address");

        String province = jsonObject.getJSONObject("regeocode").getJSONObject("addressComponent").getString("province");
        //如果省名是以“xx市”，说明是直辖市
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
