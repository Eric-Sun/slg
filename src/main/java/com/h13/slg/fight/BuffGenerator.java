package com.h13.slg.fight;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.fight.buff.Buff;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

/**
 */
public class BuffGenerator {
    private static final String BUFF_PACKAGE = "com.h13.slg.fight.buff";


    public static Buff generator(String key, String argStr) throws RequestFatalException {
        String[] args = argStr.split(",");
        String clazzName = getClazzName(key);
        try {
            Class clazz = Class.forName(clazzName);
            Buff buff = (Buff) clazz.newInstance();
            buff.setArgs(args);
            return buff;
        } catch (Exception e) {
            throw new RequestFatalException("Buff Class not found . clazzName=" + clazzName);
        }
    }


    /**
     * key as 'add_attack_rate'
     * return AddAttackRate
     *
     * @param key
     * @return
     */
    private static String getClazzName(String key) {
        List<String> list = Splitter.on("_").splitToList(key);
        List<String> newList = Lists.newLinkedList();
        for (String s : list) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase());
            newList.add(sb.toString());
        }
        StringBuilder sb = new StringBuilder();

        return sb.append(BUFF_PACKAGE).append(".").append(Joiner.on("").join(newList)).append("Buff").toString();


    }
}


