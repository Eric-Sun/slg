package com.h13.slg.chat.service;

import com.h13.slg.chat.cache.UserChatCache;
import com.h13.slg.chat.co.UserChatCO;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-19
 * Time: 下午10:59
 * To change this template use File | Settings | File Templates.
 */
@Service("ChatService")
public class ChatService {

    @Autowired
    UserChatCache userChatCache;

    @Autowired
    UserStatusHelper userStatusHelper;


    public SlgData say(SlgRequestDTO request) {

        int uid = request.getUid();
        int toId = new Integer(request.getArgs().get("toId") + "");
        long sendtime = new Long(request.getArgs().get("sendtime") + "");
        String content = request.getArgs().get("content") + "";

        String fromName = userStatusHelper.getUserStatus(uid).getName();
        String toName = userStatusHelper.getUserStatus(toId).getName();

        UserChatCO userChatCO = new UserChatCO();
        userChatCO.setFromId(uid);
        userChatCO.setToId(toId);
        userChatCO.setFromName(fromName);
        userChatCO.setToName(toName);
        userChatCO.setContent(content);
        userChatCO.setSendtime(sendtime);

        userChatCache.set(userChatCO);

        return SlgData.getData();
    }


    public SlgData get(SlgRequestDTO request) {
        int uid = request.getUid();

        List<UserChatCO> list = userChatCache.get(uid);

        return SlgData.getData().add("list", list);
    }
}
