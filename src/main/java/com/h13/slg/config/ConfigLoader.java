package com.h13.slg.config;

import com.h13.slg.config.BasicCache;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.io.File;

/**
 * config模块的启动类
 * 用来加载所有的配置文件
 * 调用了cache包中所有的配置文件的类，一一进行加载，每个配置文件的类的加载的方法需要同步，防止多线程调用
 */
public class ConfigLoader {

    private ApplicationContext applicationContext = null;
    private String configPath = null;
    private static final String PACKAGE = "com.h13.slg.config.cache.";

    public ConfigLoader(String configPath, ApplicationContext applicationContext) {
        this.configPath = configPath;
        this.applicationContext = applicationContext;
    }

    public void load() {
        try {
            File configPathDir = new File(configPath);
            File[] fileList = configPathDir.listFiles();
            for (File xmlFile : fileList) {
                if (checkIsConfigFile(xmlFile.getName())) {
                    String filename = xmlFile.getName();
                    String className = cvtFilename(filename);
                    BasicCache basicCache = (BasicCache) applicationContext.getBean(Class.forName(className));
                    basicCache.load(xmlFile.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            SlgLogger.error(SlgLoggerEntity.e("base", "config load", -1, "ok", e));
        }

    }

    private boolean checkIsConfigFile(String filename) {
        if (filename.indexOf("fileList") >= 0)
            return false;
        if (filename.indexOf(".xml") < 0) {
            SlgLogger.error(SlgLoggerEntity.p("base", "config load", -1, "found file is not config file.")
                    .addParam("filename", filename));
            return false;
        } else
            return true;
    }


    private String cvtFilename(String filename) {
        String className = "";
        String[] strings = filename.split("\\.");
        className = strings[0].substring(0, 1).toUpperCase() + strings[0].substring(1) + "Cache";
        return PACKAGE + className;
    }

}
