package com.h13.slg.skill.service;

import com.google.common.collect.Lists;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.fetcher.ZuLingConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.skill.ZuLingConstants;
import com.h13.slg.skill.co.UserZuLingCO;
import com.h13.slg.skill.co.UserZuLingItemCO;
import com.h13.slg.skill.helper.UserZuLingHelper;
import com.h13.slg.skill.vo.RoleSkillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-21
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
@Service("zuLingService")
public class ZuLingServiceImpl implements ZuLingService {
    @Autowired
    UserZuLingHelper userZuLingHelper;
    @Autowired
    ZuLingConfigFetcher zuLingConfigFetcher;

    @Override
    public SlgData summon(SlgRequestDTO request) throws RequestErrorException {

        int uid = request.getUid();

        List<UserZuLingItemCO> list = userZuLingHelper.summon(uid);

        return SlgData.getData().add("list", list);
    }

    @Override
    public SlgData load(SlgRequestDTO request) throws RequestErrorException {

        int uid = request.getUid();

        UserZuLingCO userZuLingCO = userZuLingHelper.get(uid);

        return SlgData.getData().add("list", userZuLingCO.getList());
    }


    @Override
    public SlgData leave(SlgRequestDTO request) throws RequestErrorException {

        int uid = request.getUid();

        userZuLingHelper.leave(uid);

        return SlgData.getData();
    }

    @Override
    public SlgData getSkill(SlgRequestDTO request) throws RequestErrorException {
        int uid = request.getUid();
        int index = new Integer(request.getArgs().get("index") + "");

        RoleSkillCO roleSkillCO = userZuLingHelper.getSkill(uid, index);
        RoleSkillVO roleSkillVO = new RoleSkillVO();
        SlgBeanUtils.copyProperties(roleSkillVO, roleSkillCO);

        return SlgData.getData().add("roleSkill", roleSkillVO);
    }
}
