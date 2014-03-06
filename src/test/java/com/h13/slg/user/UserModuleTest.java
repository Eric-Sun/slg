package com.h13.slg.user;


import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 下午4:39
 * To change this template use File | Settings | File Templates.
 */
public class UserModuleTest {

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
        map.put("args","{name:'aaa',password:'bbb'}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }


    @Test
    public void login(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","user");
        map.put("act","login");
        map.put("uid","21");
        map.put("auth_key","fdsafdsa");
        map.put("auth_time","24321431") ;
        map.put("args","{name:'aaa',password:'bbb'}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }
}
