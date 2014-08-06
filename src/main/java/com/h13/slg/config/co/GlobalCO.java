package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-25
 * Time: 下午5:54
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "globalList/global")
public class GlobalCO {
    @BeanPropertySetter(pattern = "globalList/global/key")
    private String key;
    @BeanPropertySetter(pattern = "globalList/global/value")
    private String value;
    @BeanPropertySetter(pattern = "globalList/global/desc")
    private String desc;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
