package com.h13.slg.core;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 上午1:02
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SlgDispatcher implements ApplicationContextAware {
    private final static Logger LOG = LoggerFactory.getLogger(SlgDispatcher.class);


    private ApplicationContext applicationContext;
    private final static String SUFFIX = "Service";

    public SlgResponseDTO handle(String mod, String act, long uid, int seq, String args, long authTime, String authKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(mod.substring(0, 1).toUpperCase());
        sb.append(mod.substring(1).toLowerCase());
        sb.append(SUFFIX);

        Map<String, String> map = JSON.parseObject(args, Map.class);
        Object beanObj = applicationContext.getBean(sb.toString());
        Class clazz = applicationContext.getBean(sb.toString()).getClass();
        SlgResponseDTO resp = null;
        SlgRequestDTO req = null;
        try {
            Method m = clazz.getMethod(act, new Class[]{SlgRequestDTO.class});
            req = new SlgRequestDTO(mod, act, uid, seq, map);
            SlgData r = (SlgData) m.invoke(beanObj, new Object[]{req});
            resp = new SlgResponseDTO(req, r);
        } catch (Exception e) {
            if (e instanceof RequestErrorException) {
                RequestErrorException reqError = (RequestErrorException) e;
                return new SlgResponseDTO(req, reqError.getCode(), reqError.getDesc());
            }
            LOG.error("error. act=" + act + " mod=" + mod, e);
        }
        return resp;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}