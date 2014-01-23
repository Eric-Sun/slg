package com.h13.slg.utils;

import com.alibaba.fastjson.JSON;
import com.h13.slg.config.Constants;
import com.h13.slg.vos.BaseDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO的util
 */
public class DTOUtils {
    private static Log LOG = LogFactory.getLog(DTOUtils.class);
    private static String CALLBACK_KEY = "callback";

    /**
     * 返回的是一个类似于执行的请求，没有任何返回结果的可能性
     *
     * @param fromId
     * @param toId
     * @return
     */
    public static String getOriginalResponse(HttpServletRequest request, HttpServletResponse response,
                                             String fromId, String toId) {
        String callback = request.getParameter(CALLBACK_KEY);
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(Constants.ResponseStatus.SUCCESS, fromId, toId));
        String json = null;
        if (callback == null)
            json = JSON.toJSONString(list);
        else
            json = callback + "(" + JSON.toJSONString(list) + ")";
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

    /**
     * 返回一个成功的操作，并且包含N个返回结果
     *
     * @param fromId
     * @param toId
     * @param info
     * @return
     */
    public static String getSucessResponse(HttpServletRequest request, HttpServletResponse response,
                                           String fromId, String toId, Object... info) {
        String callback = request.getParameter(CALLBACK_KEY);
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(Constants.ResponseStatus.SUCCESS, fromId, toId));
        for (Object obj : info) {
            list.add(obj);
        }
        String json = null;
        if (callback == null)
            json = JSON.toJSONString(list);
        else
            json = callback + "(" + JSON.toJSONString(list) + ")";
        LogWriter.info(LogWriter.RESPONSE, json);
        return json;
    }

    /**
     * 返回一个操作失败的结果
     *
     * @param fromId
     * @param toId
     * @param errorCode
     * @return
     */
    public static String getFailureResponse(HttpServletRequest request, HttpServletResponse response,
                                            String fromId, String toId, int errorCode) {
        String callback = request.getParameter(CALLBACK_KEY);
        List<Object> list = new ArrayList<Object>();
        list.add(new BaseDTO(errorCode, Constants.ResponseStatus.FAILURE, fromId, toId));
        String json = null;
        if (callback == null)
            json = JSON.toJSONString(list);
        else
            json = callback + "(" + JSON.toJSONString(list) + ")";
        LogWriter.error(LogWriter.RESPONSE, json);
        return json;
    }

}
