package com.h13.slg.user;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FarmServiceTest {


    @Test
    public void harvest(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","farm");
        map.put("act","harvest");
        map.put("uid","1");
        map.put("auth_key","fdsafdsa");
        map.put("auth_time","24321431") ;
        map.put("args","{}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }

}
