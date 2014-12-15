package com.h13.slg.mail;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-19
 * Time: 下午6:59
 * To change this template use File | Settings | File Templates.
 */
public class MailServiceTest {


    @Test
    public void getAll() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "mail");
        map.put("act", "getAll");
        map.put("uid", "2");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);

    }


    @Test
    public void getAttachment() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "mail");
        map.put("act", "getAttachment");
        map.put("uid", "2");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", "{'index':0,'mailId':1}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);

    }
}
