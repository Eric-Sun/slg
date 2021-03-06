package com.h13.slg.skill.service;

import com.google.common.collect.Lists;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.config.fetcher.ZuLingConfigFetcher;
import com.h13.slg.core.CodeConstants;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.transmission.SlgData;
import com.h13.slg.core.transmission.SlgRequestDTO;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.skill.co.UserRoleSkillCO;
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
@Service("ZulingService")
public class ZuLingServiceImpl implements ZuLingService {
    @Autowired
    UserZuLingHelper userZuLingHelper;
    @Autowired
    ZuLingConfigFetcher zuLingConfigFetcher;
    @Autowired
    RoleSkillConfigFetcher roleSkillConfigFetcher;
    @Autowired
    UserPackageHelper userPackageHelper;

    @Override
    public SlgData summon(SlgRequestDTO request) throws RequestFatalException {

        int uid = request.getUid();

        UserZuLingCO userZuLingCO = userZuLingHelper.get(uid);

        if (userZuLingHelper.isFull(userZuLingCO)) {
            throw new RequestFatalException(CodeConstants.ZuLing.ZULING_IS_FULL, "");
        }

        List<UserZuLingItemCO> list = Lists.newLinkedList();

        List<RoleSkillVO> voList = Lists.newLinkedList();

        for (int i = 0; i < SlgConstants.ZuLingConstants.SIZE; i++) {

            UserZuLingItemCO item = new UserZuLingItemCO();
            String key = userZuLingHelper.randomQuality();
            RoleSkillCO roleSkillCO = roleSkillConfigFetcher.random(key);
            item.setRsId(roleSkillCO.getId());

            list.add(item);

            RoleSkillVO vo = new RoleSkillVO();
            vo.setRsid(roleSkillCO.getId());
            vo.setStatus(UserRoleSkillCO.COMMON);
            voList.add(vo);
        }
        userZuLingCO.setList(list);

        userZuLingHelper.update(userZuLingCO);

        return SlgData.getData().add("list", list);
    }


    @Override
    public SlgData leave(SlgRequestDTO request) throws RequestFatalException {

        int uid = request.getUid();

        List<UserZuLingItemCO> list = Lists.newLinkedList();
        UserZuLingCO userZuLingCO = new UserZuLingCO();
        userZuLingCO.setId(uid);
        userZuLingCO.setList(list);
        userZuLingHelper.update(userZuLingCO);

        return SlgData.getData();
    }

    @Override
    public SlgData getSkill(SlgRequestDTO request) throws RequestFatalException {
        int uid = request.getUid();
        int index = new Integer(request.getArgs().get("index") + "");


        UserZuLingCO userZuLingCO = userZuLingHelper.get(uid);
        if (!userZuLingHelper.isFull(userZuLingCO)) {
            throw new RequestFatalException(CodeConstants.ZuLing.ZULING_IS_EMPTY, "");
        }
        UserZuLingItemCO item = userZuLingCO.getList().get(index);

        if (item.getStatus() == SlgConstants.RoleSkillConstants.GOT) {
            throw new RequestFatalException(CodeConstants.ZuLing.SKILL_HAVE_GOT, "");
        }

        item.setStatus(SlgConstants.RoleSkillConstants.GOT);

        userZuLingHelper.update(userZuLingCO);

        int rsId = item.getRsId();
        RoleSkillCO roleSkillCO = roleSkillConfigFetcher.get(rsId + "");

        userPackageHelper.addSkillItem(uid, rsId, 1);


        RoleSkillVO roleSkillVO = new RoleSkillVO();
        SlgBeanUtils.copyProperties(roleSkillVO, roleSkillCO);
        roleSkillVO.setRsid(roleSkillCO.getId());

        return SlgData.getData().add("roleSkill", roleSkillVO);
    }
}
