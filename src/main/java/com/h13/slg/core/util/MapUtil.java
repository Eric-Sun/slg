package com.h13.slg.core.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午2:55
 * To change this template use File | Settings | File Templates.
 */
public class MapUtil {

    public static void addItem(Map<String, Integer> map, String id, int num) {
        Integer cur = map.get(id);
        if (cur == null) {
            map.put(id, 1);
        } else {
            map.put(id, cur + num);
        }
    }


    public static void appendItem(Map<String, List<Long>> map, String id, long id2) {
        List<Long> cur = map.get(id);
        if (cur == null) {
            List<Long> l = new LinkedList<Long>();
            l.add(id2);
            map.put(id, l);
        } else {
            List<Long> l = map.get(id);
            l.add(id2);
            map.put(id, l);
        }
    }
}
