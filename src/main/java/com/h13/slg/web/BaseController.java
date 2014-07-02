package com.h13.slg.web;

import com.alibaba.fastjson.JSON;
import com.h13.slg.auth.helper.AuthHelper;
import com.h13.slg.core.SlgResponseDTO;
import com.h13.slg.core.SlgDispatcher;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-9
 * Time: 上午1:01
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    SlgDispatcher slg;
    @Autowired
    AuthHelper authHelper;

    @RequestMapping("/")
    public String dispatch(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String mod = request.getParameter("mod");
            String act = request.getParameter("act");
            int uid = 0;
            if (request.getParameter("uid") != null) {
                uid = new Integer(request.getParameter("uid"));
            }
            String args = request.getParameter("args");
            String authKey = request.getParameter("auth_key");
            long authTime = new Long(request.getParameter("auth_time"));
            int seq = new Integer(request.getParameter("seq"));
            SlgLogger.info(SlgLoggerEntity.r(mod, act, uid, "request")
                    .addParam("seq", seq)
                    .addParam("args", args)
                    .addParam("authKey", authKey)
                    .addParam("authTime", authTime));
            SlgResponseDTO resp = slg.handle(mod, act, uid, seq, args, authTime, authKey);
            SlgLogger.info(SlgLoggerEntity.r(mod, act, uid, "response")
                    .addParam("resp", resp));
            response.getWriter().write(JSON.toJSONString(resp));
            response.flushBuffer();
            return null;
        } catch (Exception e) {
            SlgLogger.error(SlgLoggerEntity.r("", "", -1, ""), e);
            return null;
        }
    }

}
