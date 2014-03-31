package com.h13.slg.web;

import com.h13.slg.config.ConfigLoader;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
public class BaseListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContentHolder.setServletContext(sce.getServletContext());

        ConfigLoader loader = new ConfigLoader(SysConfig.get(SysConfigConstants.CONFIGS_PATH),
                WebApplicationContentHolder.getApplicationContext());

        loader.load();
        SlgLogger.info(SlgLoggerEntity.p("base","config load",-1,"ok"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
