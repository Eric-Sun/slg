package com.h13.slg.config;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-10
 * Time: 上午12:18
 * To change this template use File | Settings | File Templates.
 */
public class DemoXmlParser {

    @Test
    public void demoParser() throws ConfigParseException {
        XmlParser<DemoCO> parser = new XmlParser<DemoCO>(DemoCO.class, "/Users/sunbo/project/h13/slg/src/test/resource/demo.xml");
        DemoCO d = parser.parse();
        System.out.println(d.getName());
        System.out.println(d.getItems().get("1").getName());
    }

}
