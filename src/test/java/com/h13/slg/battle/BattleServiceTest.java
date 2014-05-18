package com.h13.slg.battle;

import com.h13.slg.HttpClientUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * UserServiceTest: sunbo
 * Date: 14-3-6
 * Time: 上午12:53
 * To change this template use File | Settings | File Templates.
 */
public class BattleServiceTest {

    @Test
    public void saveTeam() {
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "battle");
        map.put("act", "saveTeam");
        map.put("uid", "1");
        map.put("auth_key", "fdsafdsa");
        map.put("auth_time", "24321431");
        map.put("args", "{\"team\":{\"0\":0,\"1\":1,\"2\":0,\"3\":0,\"4\":0,\"5\":0,\"6\":0,\"7\":0,\"8\":0}}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);


    }


    @Test
    public void pve(){
        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "battle");
        map.put("act", "pve");
        map.put("uid", "1");
        map.put("auth_key", "fdsafdsa");
        map.put("auth_time", "24321431");
        map.put("args", "{\"battleId\":1}");
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/", map);
        System.out.println(s);
    }
}
