package com.h13.slg.role;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * UserServiceTest: sunbo
 * Date: 14-3-6
 * Time: 下午4:16
 * To change this template use File | Settings | File Templates.
 */
public class RoleServiceTest {

    @Test
    public void wear() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","role");
        map.put("act","wear");
        map.put("uid","1");
        map.put("auth_key","c4ca4238a0b923820dcc509a6f75849b");
        map.put("auth_time","1401882863762") ;
        map.put("args","{ueid:4,urid:1}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }

    @Test
    public void takeOff() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","role");
        map.put("act","takeOff");
        map.put("uid","1");
        map.put("auth_key","c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time","1401882863762") ;
        map.put("args","{ueid:4,urid:1}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }

    @Test
    public void roleList() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","role");
        map.put("act","roleList");
        map.put("uid","2");
        map.put("auth_key","c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time","1402571328688") ;
        map.put("args","{}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }

    @Test
    public void role() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","role");
        map.put("act","role");
        map.put("uid","2");
        map.put("auth_key","c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time","1402590756563") ;
        map.put("args","{urid:3}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }
}
