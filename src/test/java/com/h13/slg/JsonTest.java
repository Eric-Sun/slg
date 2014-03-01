package com.h13.slg;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-26
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class JsonTest {

    @Test
    public void t() {

        String s = "{\"c\":{\"b\":2}}";
        Map<String, Map<String, Integer>> m = JSON.parseObject(s, Map.class);
        int c = m.get("c").get("b");
        System.out.println(c);
        m.get("c").put("b",333);
        System.out.println(JSON.toJSONString(m));
    }
}
