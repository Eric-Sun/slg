package com.h13.slg.chat;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-20
 * Time: 下午7:19
 * To change this template use File | Settings | File Templates.
 */
public class ChatServiceTest {

    @Test
    public void say() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "chat");
        map.put("act", "say");
        map.put("uid", "3");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", "{toId:2,sendtime:3242,content:'ssss'}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void get() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "chat");
        map.put("act", "get");
        map.put("uid", "2");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


}
