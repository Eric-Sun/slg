package com.h13.slg.web;

import com.alibaba.fastjson.JSON;
import com.h13.slg.core.SlgResponseDTO;
import com.h13.slg.core.SlgDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping("/")
    @ResponseBody
    public String dispatch(HttpServletRequest request, HttpServletResponse response) {
        try {
            String mod = request.getParameter("mod");
            String act = request.getParameter("act");
            long uid = new Long(request.getParameter("uid"));
            String args = request.getParameter("args");
            String authKey = request.getParameter("auth_key");
            long authTime = new Long(request.getParameter("auth_time"));
            int seq = new Integer(request.getParameter("seq"));
            LOG.info("request mod=" + mod + " act=" + act + " uid=" + uid + " seq=" + seq
                    + " args=" + args + " authTime=" + authTime + " authKey=" + authKey);
            SlgResponseDTO resp = slg.handle(mod, act, uid, seq, args, authTime, authKey);
            return JSON.toJSONString(resp);
        } catch (Exception e) {
            LOG.error("error.",e);
            return null;
        }
    }

}
