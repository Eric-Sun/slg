package com.h13.slg;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-11
 * Time: 下午6:27
 * To change this template use File | Settings | File Templates.
 */
public class BeanutilsTest {

    @Test
    public void test() throws InstantiationException, IllegalAccessException {

        DynaProperty[] props = new DynaProperty[]{
                new DynaProperty("address", java.util.Map.class),
                new DynaProperty("firstName", String.class),
                new DynaProperty("lastName", String.class)
        };
        BasicDynaClass dynaClass = new BasicDynaClass("employee", null, props);


        DynaBean employee = dynaClass.newInstance();
        employee.set("address", new HashMap());
        employee.set("firstName", "Fred");
        employee.set("lastName", "Flintstone");

        System.out.println(JSON.toJSONString(employee));
    }


}
