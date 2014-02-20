package com.h13.slg.config;

import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.annotations.FromAnnotationsRuleModule;
import org.apache.commons.digester3.binder.DigesterLoader;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.digester3.binder.DigesterLoader.newLoader;

/**
 * 每个xml处理类的基础类
 */
public class XmlParser<T> {
    private Class<T> clazz = null;
    private Digester digester = null;
    private String configFilename = null;
    private boolean inited = false;


    public XmlParser(Class clazz, String configFilename) {
        this.clazz = clazz;
        this.configFilename = configFilename;
    }


    private void init() {
        if (!inited) {
            DigesterLoader loader = newLoader(new FromAnnotationsRuleModule() {
                @Override
                protected void configureRules() {
                    bindRulesFrom(clazz);
                }
            });
            this.digester = loader.newDigester();
            inited = true;
        }
    }


    public synchronized T parse() throws ConfigParseException {
        try {
            if (!inited)
                init();
            return digester.parse(new File(this.configFilename));
        } catch (Exception e) {
            throw new ConfigParseException("fileName=" + this.configFilename, e);
        }
    }
}
