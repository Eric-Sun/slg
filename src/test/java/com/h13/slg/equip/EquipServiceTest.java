package com.h13.slg.equip;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-16
 * Time: 下午6:50
 * To change this template use File | Settings | File Templates.
 */
public class EquipServiceTest {

    @Test
    public void equipList() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "equip");
        map.put("act", "equipList");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1402915939605");
        map.put("args", "{type:'weapon'}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }

    @Test
    public void equip() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "equip");
        map.put("act", "equip");
        map.put("uid", "2");
        map.put("auth_key", "c81e728d9d4c2f636f067f89cc14862c");
        map.put("auth_time", "1403017602502");
        map.put("args", "{ueid:12}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }
}
