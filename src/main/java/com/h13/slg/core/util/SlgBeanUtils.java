package com.h13.slg.core.util;

import com.h13.slg.core.RequestErrorException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-6-30
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
public class SlgBeanUtils {

    public static String getProperty(Object obj, String name) throws RequestErrorException {
        try {
            return BeanUtils.getProperty(obj, name);
        } catch (Exception e) {
            throw new RequestErrorException(e);
        }
    }


    public static void copyProperties(Object dest, Object orig) throws RequestErrorException {
        try {
            BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new RequestErrorException(e);
        }
    }
}
