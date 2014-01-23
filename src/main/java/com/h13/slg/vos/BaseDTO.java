package com.h13.slg.vos;

import com.h13.slg.config.Constants;

public class BaseDTO {
    private int code = 0;
    private int status = Constants.ResponseStatus.SUCCESS;
    private String fromId;
    private String toId;

    public BaseDTO(int code, int status, String fromId, String toId) {
        this.code = code;
        this.status = status;
        this.fromId = fromId;
        this.toId = toId;
    }

    public BaseDTO(int status, String fromId, String toId) {
        this.status = status;
        this.fromId = fromId;
        this.toId = toId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}
