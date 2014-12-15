package com.h13.slg.user.hepler;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by sunbo on 14-11-4.
 */
public class PassportResponse {

    private Map<String, Object> data = Maps.newHashMap();
    private int code = 0;

    private PassportResponse() {
    }

    private PassportResponse(int code) {
        this.code = code;
    }

    public static PassportResponse newSuccessResponse() {
        return new PassportResponse();
    }

    public static PassportResponse newFailureResponse(int code) {
        return new PassportResponse(code);

    }

    public PassportResponse addData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String end() {
        return JSON.toJSONString(this);
    }

}
