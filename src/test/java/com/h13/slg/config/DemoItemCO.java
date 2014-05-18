package com.h13.slg.config;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * UserServiceTest: sunbo
 * Date: 14-2-11
 * Time: 上午12:01
 * To change this template use File | Settings | File Templates.
 */
public @ObjectCreate(pattern = "demo/item")
class DemoItemCO {

    @BeanPropertySetter(pattern = "demo/item/id")
    private String id;
    @BeanPropertySetter(pattern = "demo/item/name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
