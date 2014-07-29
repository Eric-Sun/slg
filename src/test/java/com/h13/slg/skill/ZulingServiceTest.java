package com.h13.slg.skill;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-28
 * Time: 下午5:28
 * To change this template use File | Settings | File Templates.
 */
public class ZulingServiceTest {


    @Test
    public void summon() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "zuLing");
        map.put("act", "summon");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1406620279451");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }

    @Test
    public void load(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "zuLing");
        map.put("act", "load");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1406545184154");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void leave(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "zuLing");
        map.put("act", "leave");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1406545184154");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void getSkill(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "zuLing");
        map.put("act", "getSkill");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1406620279451");
        map.put("args", "{index:'0'}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


}
