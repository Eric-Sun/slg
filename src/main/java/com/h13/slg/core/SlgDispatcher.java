package com.h13.slg.core;

import com.alibaba.fastjson.JSON;
import com.h13.slg.auth.helper.AuthHelper;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.event.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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

    @Autowired
    AuthHelper authHelper;

    private ApplicationContext applicationContext;
    private final static String SUFFIX = "Service";

    public SlgResponseDTO handle(String mod, String act, int uid, int seq, String args, long authTime, String authKey) {
        StringBuilder sb = new StringBuilder();
        sb.append(mod.substring(0, 1).toUpperCase());
        sb.append(mod.substring(1).toLowerCase());
        sb.append(SUFFIX);

        Map<String, Object> map = JSON.parseObject(args, Map.class);
        Object beanObj = applicationContext.getBean(sb.toString());
        Class clazz = applicationContext.getBean(sb.toString()).getClass();
        SlgResponseDTO resp = null;
        SlgRequestDTO req = null;
        try {
            Method m = clazz.getMethod(act, new Class[]{SlgRequestDTO.class});
            req = new SlgRequestDTO(mod, act, uid, seq, map);

            if (!act.equals("login") && !act.equals("register")) {
                // 检测auth
                if (!authHelper.check(uid, authKey, authTime)) {
                    // 返回失败
                    resp = new SlgResponseDTO(req, Constants.ResponseStatus.AUTH_ERROR, null);
                    return resp;
                }
            }
            SlgData r = (SlgData) m.invoke(beanObj, new Object[]{req});
            // 触发事件
            triggerEvents(req, r);
            resp = new SlgResponseDTO(req, r);
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                if (((InvocationTargetException) e).getTargetException() instanceof RequestErrorException) {
                    RequestErrorException reqError = (RequestErrorException) ((InvocationTargetException) e).getTargetException();
                    return new SlgResponseDTO(req, reqError.getCode(), reqError.getDesc());
                }
            }
            SlgLogger.error(SlgLoggerEntity.p(mod, act, uid, "error"), e);
        }
        return resp;
    }

    private void triggerEvents(SlgRequestDTO req, SlgData r) throws RequestErrorException {
        EventService eventService = (EventService) applicationContext.getBean("EventService");
        eventService.triggerTasks(req.getUid(), r);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
