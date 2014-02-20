package com.h13.slg.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 下午7:19
 * To change this template use File | Settings | File Templates.
 */
public class SysConfig {
    private static final Logger LOG = LoggerFactory.getLogger(SysConfig.class);
    private static SysConfig sysConfig = null;
    private static Properties props = new Properties();

    private SysConfig() {

        InputStream in = SysConfig.class.getClassLoader().getResourceAsStream("slg.properties");
        try {
            props.load(in);
        } catch (IOException e) {
            LOG.error("load error. filename=slg.properties", e);
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
                LOG.error("close error. filename=slg.properties", e);
            }
        }
    }

    public static String get(String key) {
        if (sysConfig == null) {
            sysConfig = new SysConfig();
        }

        return props.get(key).toString();
    }
}
