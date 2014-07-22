package com.h13.slg.skill.helper;

import com.google.common.collect.Lists;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.co.ZuLingCO;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.config.fetcher.ZuLingConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.skill.RoleSkillConstants;
import com.h13.slg.skill.ZuLingConstants;
import com.h13.slg.skill.co.UserZuLingCO;
import com.h13.slg.skill.co.UserZuLingItemCO;
import com.h13.slg.skill.dao.UserZuLingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-21
 * Time: 下午6:06
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserZuLingHelper {

    @Autowired
    UserZuLingDAO userZuLingDAO;
    @Autowired
    UserZuLingHelper userZuLingHelper;
    @Autowired
    ZuLingConfigFetcher zuLingConfigFetcher;
    @Autowired
    RoleSkillConfigFetcher roleSkillConfigFetcher;

    public UserZuLingCO get(long uid) {
        return userZuLingDAO.get(uid);
    }

    /**
     * 判断zuling是否已经满了
     *
     * @param userZuLingCO
     * @return
     */
    public boolean isFull(UserZuLingCO userZuLingCO) {
        if (userZuLingCO.getList().size() == 0)
            return false;
        else
            return true;
    }


    public List<UserZuLingItemCO> summon(int uid) throws RequestErrorException {
        UserZuLingCO userZuLingCO = userZuLingHelper.get(uid);

        if (userZuLingHelper.isFull(userZuLingCO)) {
            throw new RequestErrorException(CodeConstants.ZuLing.ZULING_IS_FULL, "");
        }

        List<UserZuLingItemCO> list = Lists.newLinkedList();
        for (int i = 0; i < ZuLingConstants.SIZE; i++) {

            UserZuLingItemCO item = new UserZuLingItemCO();
            String key = randomQuality();
            RoleSkillCO roleSkillCO = roleSkillConfigFetcher.random(key);
            item.setRsId(roleSkillCO.getId());

            list.add(item);
        }

        userZuLingDAO.insert(uid, list);

        return list;
    }

    private String randomQuality() {

        ZuLingCO zuLingCO = zuLingConfigFetcher.get("1");
        Random random = new Random();
        int result = random.nextInt(100);
        if (result < zuLingCO.getSheng()) {
            return RoleSkillConstants.SHENG;
        } else if (result < zuLingCO.getSheng() + zuLingCO.getWang()) {
            return RoleSkillConstants.WANG;
        } else if (result < zuLingCO.getSheng() + zuLingCO.getWang() + zuLingCO.getShi()) {
            return RoleSkillConstants.SHI;
        } else {
            return RoleSkillConstants.ZHE;
        }
    }


    public void leave(int uid) {
        List<UserZuLingItemCO> list = Lists.newLinkedList();
        userZuLingDAO.update(uid, list);
    }


    public RoleSkillCO getSkill(int uid, int index) throws RequestErrorException {

        UserZuLingCO userZuLingCO = get(uid);
        if (!isFull(userZuLingCO)) {
            throw new RequestErrorException(CodeConstants.ZuLing.ZULING_IS_EMPTY, "");
        }
        UserZuLingItemCO item = userZuLingCO.getList().get(index);

        if (item.getStatus() == RoleSkillConstants.GOT) {
            throw new RequestErrorException(CodeConstants.ZuLing.SKILL_HAVE_GOT, "");
        }

        item.setStatus(RoleSkillConstants.GOT);

        userZuLingDAO.update(uid, userZuLingCO.getList());

        int rsId = item.getRsId();
        RoleSkillCO roleSkillCO = roleSkillConfigFetcher.get(rsId + "");


        return roleSkillCO;

    }


}
