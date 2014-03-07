package com.h13.slg.role;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
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
        map.put("auth_key","fdsafdsa");
        map.put("auth_time","24321431") ;
        map.put("args","{ueid:4,urid:1}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }

    @Test
    public void takeOff() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","role");
        map.put("act","takeOff");
        map.put("uid","1");
        map.put("auth_key","fdsafdsa");
        map.put("auth_time","24321431") ;
        map.put("args","{ueid:4,urid:1}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }
}
