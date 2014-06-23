package com.h13.slg.core;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-14
 * Time: 下午6:07
 * To change this template use File | Settings | File Templates.
 */
public class RequestErrorException extends Exception {
    private int code = 1001;     // 1001 默认请求失败错误码
    private String desc;


    public RequestErrorException(Throwable t) {
        this(ErrorCodeConstants.COMMON_ERROR, "", t);

    }

    public RequestErrorException(int code, String desc) {
        super(code + ":" + desc);
        this.code = code;
        this.desc = desc;

    }

    public RequestErrorException(int code, String desc, Throwable t) {
        super(code + ":" + desc, t);
        this.code = code;
        this.desc = desc;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
