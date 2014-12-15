package com.h13.slg.admin;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunbo on 14-11-13.
 */
public class AdminUserServiceTest {


    @Test
    public void getUserInfo() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "adminUser");
        map.put("act", "getUserInfo");
        map.put("uid", "2");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void getUserInfoByName() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "adminUser");
        map.put("act", "getUserInfoByName");
        map.put("uid", "2");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", "{'name':'ssss'}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }

}
