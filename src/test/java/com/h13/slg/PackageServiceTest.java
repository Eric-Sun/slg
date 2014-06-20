package com.h13.slg;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-19
 * Time: 下午6:36
 * To change this template use File | Settings | File Templates.
 */
public class PackageServiceTest {

    @Test
    public void get() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "package");
        map.put("act", "get");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1403177558333");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);

    }
}
