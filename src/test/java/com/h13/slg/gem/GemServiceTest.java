package com.h13.slg.gem;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-2
 * Time: 下午5:43
 * To change this template use File | Settings | File Templates.
 */
public class GemServiceTest {

    @Test
    public void join() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "gem");
        map.put("act", "join");
        map.put("uid", "2");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", "{'gemId':102}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }

}
