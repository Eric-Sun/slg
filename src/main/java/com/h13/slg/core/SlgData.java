package com.h13.slg.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-14
 * Time: 下午5:55
 * To change this template use File | Settings | File Templates.
 */
public class SlgData extends HashMap<String, Object> {

    private SlgData() {
    }

    public static SlgData getData() {
        return new SlgData();
    }

    public SlgData add(String key, Object value) {
        put(key, value);
        return this;
    }
}
