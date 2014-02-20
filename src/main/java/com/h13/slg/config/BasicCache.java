package com.h13.slg.config;

import com.h13.slg.config.ConfigParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-11
 * Time: 下午4:54
 * To change this template use File | Settings | File Templates.
 */
public abstract class BasicCache<T> {
    private static final Logger LOG = LoggerFactory.getLogger(BasicCache.class);
    protected String name = null;

    public void load(String filename) throws ConfigParseException {
        doLoad(filename);
        LOG.info("load config file success . filename = " + filename);
    }

    public abstract void doLoad(String filename) throws ConfigParseException;

    public abstract T get(String id);
}
