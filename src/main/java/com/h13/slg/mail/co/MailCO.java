package com.h13.slg.mail.co;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-18
 * Time: 上午1:19
 * To change this template use File | Settings | File Templates.
 */
public class MailCO {

    private int id;
    private int uid;
    private String content;
    private List<SystemMailAttachmentCO> attachment;
    private int status;

    public List<SystemMailAttachmentCO> getAttachment() {
        return attachment;
    }

    public void setAttachment(List<SystemMailAttachmentCO> attachment) {
        this.attachment = attachment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
