package com.h13.slg.skill;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-29
 * Time: 下午4:05
 * To change this template use File | Settings | File Templates.
 */
public class SkillServiceTest {

    @Test
    public void skillList() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "skill");
        map.put("act", "skillList");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1406620279451");
        map.put("args", "{index:1}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void setSkill() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "skill");
        map.put("act", "setSkill");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1406620279451");
        map.put("args", "{urid:3,rsid:4}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


    @Test
    public void resetSkill() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "skill");
        map.put("act", "resetSkill");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1406620279451");
        map.put("args", "{urid:3,rsid:1,oldursid:4}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }


}
