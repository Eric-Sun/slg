package com.h13.slg.mail.helper;

import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.mail.cache.MailCache;
import com.h13.slg.mail.co.MailCO;
import com.h13.slg.mail.co.SystemMailAttachmentCO;
import com.h13.slg.mail.dao.MailDAO;
import com.h13.slg.pkg.cache.UserPackageCache;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-18
 * Time: 上午1:23
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MailHelper {


    @Autowired
    MailDAO mailDAO;

    @Autowired
    MailCache mailCache;

    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    UserPackageHelper userPackageHelper;


    /**
     * 只是系统调用，用户不可以调用此方法
     *
     * @param uid
     * @param content
     * @param attachment
     */
    public void send(int uid, String content, List<SystemMailAttachmentCO> attachment) {
        checkAndReload(uid);


        MailCO mailCO = new MailCO();
        mailCO.setUid(uid);
        mailCO.setContent(content);
        mailCO.setAttachment(attachment);
        mailCO.setStatus(SlgConstants.Mail.NOT_GOT);

        int mailId = mailDAO.insert(mailCO);
        mailCO.setId(mailId);

        mailCache.push(uid, mailCO, SlgConstants.Mail.MAX_SIZE);
    }

    private void checkAndReload(int uid) {
        if (mailCache.hasCache(uid)) {
            return;
        }

        // 由于某些原因数据丢失，需要重新导入
        List<MailCO> list = mailDAO.list(uid, SlgConstants.Mail.MAX_SIZE);
        for (MailCO mailCO : list) {
            mailCache.push(uid, mailCO, SlgConstants.Mail.MAX_SIZE);
        }
    }


    /**
     * 获取附件的奖励
     *
     * @param uid
     * @param mailId
     */
    public List<SystemMailAttachmentCO> getAttachment(int uid, int index, int mailId) throws RequestFatalException {
        checkAndReload(uid);
        // 把attachment放到背包中
        MailCO mail = mailCache.get(uid, index);

        List<SystemMailAttachmentCO> list = mail.getAttachment();

        for (SystemMailAttachmentCO co : list) {
            if (co.getType().equals("material")) {
                userPackageHelper.addMaterialItem(uid, co.getId(), co.getCount());
            } else if (co.getType().equals("equip")) {
                userPackageHelper.addMaterialItem(uid, co.getId(), co.getCount());
            } else {
                throw new RequestFatalException("attachment type is wrong. type=" + co.getType());
            }
        }

        // 更新数据库
        mail.setStatus(SlgConstants.Mail.GOT);

        mailDAO.updateStatus(uid, mail.getId(), mail.getStatus());

        // 更新cache中的数据

        mailCache.update(uid, index, mail);

        return list;

    }


    /**
     * 获取用户的系统邮件列表
     * @param uid
     * @return
     */
    public List<MailCO> list(int uid) {
        if (mailCache.hasCache(uid)) {
            List<MailCO> mailCOList = mailCache.getAll(uid);
            return mailCOList;
        }

        // 由于某些原因数据丢失，需要重新导入
        List<MailCO> list = mailDAO.list(uid, SlgConstants.Mail.MAX_SIZE);
        for (MailCO mailCO : list) {
            mailCache.push(uid, mailCO, SlgConstants.Mail.MAX_SIZE);
        }

        return list;
    }


}
