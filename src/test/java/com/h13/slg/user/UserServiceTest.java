package com.h13.slg.user;


import com.h13.slg.HttpClientUtil;
import com.h13.slg.config.ConfigLoader;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.web.SysConfigConstants;
import com.h13.slg.web.WebApplicationContentHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * UserServiceTest: sunbo
 * Date: 14-2-9
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public class UserServiceTest {

    @Test
    public void httpClient() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "castle");
        map.put("act", "harvest");
        map.put("uid", "432143214312");
        map.put("auth_key", "fdsafdsa");
        map.put("auth_time", "24321431");
        map.put("args", "fdsafdsa");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }


    @Test
    public void register() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "user");
        map.put("act", "register");
        map.put("uid", "432143214312");
        map.put("auth_key", "fdsafdsa");
        map.put("auth_time", "24321431");
        map.put("args", "{name:'ssss',password:'bbbb'}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void login() {
        // "authKey":"c4ca4238a0b923820dcc509a6f75849b","authTime":1400069582722,
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "user");
        map.put("act", "login");
        map.put("auth_key", "fdsafdsa");
        map.put("auth_time", "24321431");
        map.put("args", "{name:'ssss',password:'bbbb'}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void getInfo() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "user");
        map.put("act", "getInfo");
        map.put("uid", "2");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", "{}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


}
