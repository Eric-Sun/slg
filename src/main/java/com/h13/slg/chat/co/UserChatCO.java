package com.h13.slg.chat.co;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-19
 * Time: 下午10:16
 * To change this template use File | Settings | File Templates.
 */
public class UserChatCO {
    private int fromId;
    private int toId;
    private String fromName;
    private String toName;

    private String content;
    private long sendtime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public long getSendtime() {
        return sendtime;
    }

    public void setSendtime(long sendtime) {
        this.sendtime = sendtime;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }
}
