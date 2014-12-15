package com.h13.slg.core;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.h13.slg.config.PropertiesConfiguration;
import com.h13.slg.core.exception.RequestException;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.transmission.SlgResponseDTO;
import com.h13.slg.user.hepler.AuthHelper;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.event.service.EventService;
import com.h13.slg.web.SysConfigConstants;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    List<String> whiteList = Lists.newArrayList();

    private ApplicationContext applicationContext;
    private final static String SUFFIX = "Service";

    public SlgResponseDTO handle(String mod, String act, int uid, int seq, String args, long authTime, String authKey, String remoteIp) {
        StringBuilder sb = new StringBuilder();
        sb.append(mod.substring(0, 1).toUpperCase());
        sb.append(mod.substring(1));
        sb.append(SUFFIX);
        if (whiteList.size() == 0) {
            String str = PropertiesConfiguration.getInstance().getStringValue(SysConfigConstants.ADMIN_IP_WHITELIST);
            whiteList = Splitter.on(",").splitToList(str);
            SlgLogger.info(SlgLoggerEntity.p("config", "adminWhiteList", uid, "set ok"));
        }

        Map<String, Object> map = JSON.parseObject(args, Map.class);
        Object beanObj = applicationContext.getBean(sb.toString());
        Class clazz = beanObj.getClass();
        SlgResponseDTO resp = null;
        SlgRequestDTO req = null;
        try {
            Method m = clazz.getMethod(act, new Class[]{SlgRequestDTO.class});
            req = new SlgRequestDTO(mod, act, uid, seq, map);
            // admin的话需要ip白名单
            if (mod.indexOf("admin") > 0 && !whiteList.contains(remoteIp)) {
                SlgLogger.warn(SlgLoggerEntity.p("request", "checkIp", uid, "ip is " + remoteIp));
                resp = new SlgResponseDTO(req, CodeConstants.SYSTEM.CANNOT_USE_ADMIN_INTERFACE, null);
                return resp;
            }


            if (!act.equals("login") && !act.equals("register")
                    && mod.indexOf("admin") < 0) {
                // 检测auth
                if (!authHelper.check(uid, authKey, authTime)) {
                    // 返回失败
                    resp = new SlgResponseDTO(req, CodeConstants.SYSTEM.TOKEN_INVALID, null);
                    return resp;
                }
            }
            SlgData r = (SlgData) m.invoke(beanObj, new Object[]{req});
            // 触发事件
            triggerEvents(req, r);
            resp = new SlgResponseDTO(req, r);
        } catch (Exception e) {
            if (e instanceof InvocationTargetException) {
                if (((InvocationTargetException) e).getTargetException() instanceof RequestException) {
                    RequestException reqError = (RequestException) ((InvocationTargetException) e).getTargetException();
                    return new SlgResponseDTO(req, reqError.getCode(), reqError.getDesc());
                } else {
                    // 其他异常，按照bug处理
                    Throwable reqError = ((InvocationTargetException) e).getTargetException();
                    SlgLogger.error(SlgLoggerEntity.e(mod, act, uid, reqError.getMessage(), reqError));
                    return new SlgResponseDTO(req, CodeConstants.SYSTEM.COMMON_ERROR, "");
                }
            }

        }
        return resp;
    }

    private void triggerEvents(SlgRequestDTO req, SlgData r) throws RequestFatalException {
        EventService eventService = (EventService) applicationContext.getBean("EventService");
        eventService.triggerTasks(req.getUid(), r);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
