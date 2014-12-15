package com.h13.slg.mail.service;

import com.google.common.collect.Lists;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.transmission.SlgResponseDTO;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.mail.co.MailCO;
import com.h13.slg.mail.co.SystemMailAttachmentCO;
import com.h13.slg.mail.helper.MailHelper;
import com.h13.slg.mail.vo.SystemMailAttachmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-18
 * Time: 上午1:22
 * To change this template use File | Settings | File Templates.
 */
@Service("MailService")
public class MailService {

    @Autowired
    MailHelper mailHelper;


    public SlgData getAttachment(SlgRequestDTO request) throws RequestFatalException {
        int uid = request.getUid();
        int mailId = new Integer(request.getArgs().get("mailId") + "");
        int index = new Integer(request.getArgs().get("index") + "");

        List<SystemMailAttachmentCO> attachment = mailHelper.getAttachment(uid, index, mailId);

        List<SystemMailAttachmentVO> list = Lists.newLinkedList();
        for (SystemMailAttachmentCO item : attachment) {
            SystemMailAttachmentVO vo = new SystemMailAttachmentVO();
            SlgBeanUtils.copyProperties(vo, item);
            list.add(vo);
        }

        return SlgData.getData().add("list", list);
    }


    public SlgData getAll(SlgRequestDTO request) {
        int uid = request.getUid();
        List<MailCO> mailList = mailHelper.list(uid);
        return SlgData.getData().add("list", mailList);
    }


}
