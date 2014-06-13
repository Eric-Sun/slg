package com.h13.slg.user;


import com.h13.slg.HttpClientUtil;
import org.junit.Test;

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
        map.put("mod","castle");
        map.put("act","harvest");
        map.put("uid","432143214312");
        map.put("auth_key","fdsafdsa");
        map.put("auth_time","24321431") ;
        map.put("args","fdsafdsa");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }



    @Test
    public void register(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","user");
        map.put("act","register");
        map.put("uid","432143214312");
        map.put("auth_key","fdsafdsa");
        map.put("auth_time","24321431") ;
        map.put("args","{name:'ssss',password:'bbbb'}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }



    @Test
    public void login(){
        // "authKey":"c4ca4238a0b923820dcc509a6f75849b","authTime":1400069582722,
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","user");
        map.put("act","login");
        map.put("auth_key","fdsafdsa");
        map.put("auth_time","24321431") ;
        map.put("args","{name:'aaa',password:'bbb'}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void getInfo(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","user");
        map.put("act","getInfo");
        map.put("uid","1");
        map.put("auth_key","c4ca4238a0b923820dcc509a6f75849b");
        map.put("auth_time","1400069582722") ;
        map.put("args","{name:'aaa',password:'bbb'}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


}
