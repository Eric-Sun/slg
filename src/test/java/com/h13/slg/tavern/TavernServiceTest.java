package com.h13.slg.tavern;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-12
 * Time: 下午6:15
 * To change this template use File | Settings | File Templates.
 */
public class TavernServiceTest {

    @Test
    public void invite(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "tavern");
        map.put("act", "invite");
        map.put("uid", "1");
        map.put("auth_key", "fdsafdsa");
        map.put("auth_time", "24321431");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }

    @Test
    public void leave(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "tavern");
        map.put("act", "leave");
        map.put("uid", "1");
        map.put("auth_key", "fdsafdsa");
        map.put("auth_time", "24321431");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }


    public void enroll(){

    }
}
