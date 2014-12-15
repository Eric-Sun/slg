package com.h13.slg.admin;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.h13.slg.HttpClientUtil;
import com.h13.slg.mail.co.SystemMailAttachmentCO;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-19
 * Time: 下午6:47
 * To change this template use File | Settings | File Templates.
 */
public class AdminMailServiceTest {


    @Test
    public void send() {

        Map<String, Object> data = Maps.newHashMap();
        data.put("content", "fdsafsa");
        List<SystemMailAttachmentCO> list = Lists.newLinkedList();
        SystemMailAttachmentCO attachmentCO = new SystemMailAttachmentCO();
        attachmentCO.setType("material");
        attachmentCO.setId(1);
        attachmentCO.setCount(1);
        list.add(attachmentCO);
        data.put("attachment", list);

        HttpClientUtil http = new HttpClientUtil();
        Map<String, String> map = new HashMap<String, String>();
        map.put("mod", "adminMail");
        map.put("act", "send");
        map.put("uid", "2");
        map.put("auth_key", "aaaa");
        map.put("auth_time", "12345");
        map.put("args", JSON.toJSONString(data));
        map.put("seq", "1");
        String s = http.post("http://localhost:8080/slg/", map);
        System.out.println(s);
    }
}
