package com.h13.slg.admin.service;

import com.alibaba.fastjson.JSON;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.mail.co.SystemMailAttachmentCO;
import com.h13.slg.mail.helper.MailHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-19
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
@Service("AdminMailService")
public class AdminMailService {

    @Autowired
    MailHelper mailHelper;

    public SlgData send(SlgRequestDTO request) {
        int uid = request.getUid();
        String content = request.getArgs().get("content") + "";
        String attachmentStr = request.getArgs().get("attachment") + "";

        List<SystemMailAttachmentCO> attachment = JSON.parseArray(attachmentStr, SystemMailAttachmentCO.class);

        mailHelper.send(uid, content, attachment);

        return SlgData.getData();
    }


}
