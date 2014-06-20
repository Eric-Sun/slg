package com.h13.slg.inventory;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-18
 * Time: 下午4:59
 * To change this template use File | Settings | File Templates.
 */

public class ShopServiceTest {

    @Test
    public void shopList(){

        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "shop");
        map.put("act", "shopList");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1403084816152");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);

    }


    @Test
    public void buy(){

        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "inventory");
        map.put("act", "buy");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1403171968236");
        map.put("args", "{num:1,id:50}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);

    }

}
