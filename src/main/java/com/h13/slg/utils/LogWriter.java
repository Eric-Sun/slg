package com.h13.slg.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-2-22
 * Time: 下午11:56
 * To change this template use File | Settings | File Templates.
 */
public class LogWriter {
    private static Log LOG = LogFactory.getLog(LogWriter.class);


    public static String TASK = "task";
    public static String REQEUST = "request";
    public static String RESPONSE = "response";
    public static String TROOP = "troop";
    public static String PASSPORT_LOGIN = "passport|login";
    public static String PASSPORT_REGIETER = "passport|register";
    public static String CITY = "city";
    public static String SCHEDULER = "scheduler";
    public static String PACKAGE = "package";

    public static void info(String catalog, Object... info) {
        StringBuilder sb = new StringBuilder();
        for (Object info0 : info) {
            sb.append(info0.toString()).append("|");
        }
        LOG.info(catalog + "|" + sb.toString());
    }

    public static void debug(String catalog, Object... info) {
        StringBuilder sb = new StringBuilder();
        for (Object info0 : info) {
            sb.append(info).append("|");
        }
        LOG.debug(catalog + "|" + sb.toString());
    }

    public static void warn(String catalog, Throwable t) {
        LOG.warn(catalog + "|" + t.getMessage());
    }

    public static void warn(String catalog, String msg) {
        LOG.warn(catalog + "|" + msg);
    }

    public static void error(String catalog, String msg, Throwable t) {
        LOG.error(catalog + "|" + msg, t);
    }

    public static void error(String catalog, String msg) {
        LOG.error(catalog + "|" + msg);
    }

    public static void error(String catalog, Throwable t) {
        LOG.error(catalog + "|", t);
    }

    //////////////////////////////////////////////
    public static void logRequest(HttpServletRequest request) {


    }

}
