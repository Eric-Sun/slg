package com.h13.slg.role;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-4-9
 * Time: 下午4:06
 * To change this template use File | Settings | File Templates.
 */
public class SkillServiceTest {

    @Test
    public void upgrade(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod","skill");
        map.put("act","upgrade");
        map.put("uid","1");
        map.put("auth_key","fdsafdsa");
        map.put("auth_time","24321431") ;
        map.put("args","{urid:1,skill:1}");
        map.put("seq","1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);

    }
}
