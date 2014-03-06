package com.h13.slg.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-4
 * Time: 下午5:44
 * To change this template use File | Settings | File Templates.
 */
public class SlgLogger {


    public static void info(SlgLoggerEntity obj) {
        Logger log = LoggerFactory.getLogger("slg."+obj.getMod());
        log.info(obj.toString());
    }

    public static void error(SlgLoggerEntity obj,Throwable t) {
        Logger log = LoggerFactory.getLogger("slg."+obj.getMod());
        log.error(obj.toString(),t);
    }

    public static void error(SlgLoggerEntity obj) {
        Logger log = LoggerFactory.getLogger("slg."+obj.getMod());
        log.error(obj.toString());
    }



    public static void debug(SlgLoggerEntity obj) {
        Logger log = LoggerFactory.getLogger("slg."+obj.getMod());
        log.debug(obj.toString());
    }

}
