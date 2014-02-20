package com.h13.slg.config;

/**
 * 配置文件解析错误
 */
public class ConfigParseException extends Exception {

    public ConfigParseException(String msg) {
        super(msg);

    }

    public ConfigParseException(String msg, Throwable t) {
        super(msg, t);
    }
}
