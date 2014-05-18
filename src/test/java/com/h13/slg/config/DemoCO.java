package com.h13.slg.config;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;
import org.apache.commons.digester3.annotations.rules.SetNext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * UserServiceTest: sunbo
 * Date: 14-2-10
 * Time: 上午12:19
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "demo")
public class DemoCO {
    @BeanPropertySetter(pattern = "demo/name")
    private String name;

    private Map<String, DemoItemCO> items = new HashMap<String, DemoItemCO>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @SetNext
    public void addItem(DemoItemCO item) {
        items.put(item.getId(), item);
    }

    public Map<String, DemoItemCO> getItems() {
        return items;
    }

    public void setItems(Map<String, DemoItemCO> items) {
        this.items = items;
    }
}

