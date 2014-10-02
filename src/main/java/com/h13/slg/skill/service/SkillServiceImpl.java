package com.h13.slg.skill.service;

import com.google.common.collect.Lists;
import com.h13.slg.config.co.RoleSkillCO;
import com.h13.slg.config.fetcher.RoleSkillConfigFetcher;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.SlgData;
import com.h13.slg.core.SlgRequestDTO;
import com.h13.slg.core.util.SlgBeanUtils;
import com.h13.slg.pkg.helper.UserPackageHelper;
import com.h13.slg.skill.helper.RoleSkillHelper;
import com.h13.slg.skill.vo.RoleSkillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午7:56
 * To change this template use File | Settings | File Templates.
 */
@Service("SkillService")
public class SkillServiceImpl implements SkillService {

    @Autowired
    UserPackageHelper userPackageHelper;
    @Autowired
    RoleSkillHelper roleSkillHelper;
    @Autowired
    RoleSkillConfigFetcher roleSkillConfigFetcher;

    @Override
    public SlgData setSkill(SlgRequestDTO requestDTO) throws RequestErrorException {

        int uid = requestDTO.getUid();
        int urid = new Integer(requestDTO.getArgs().get("urid") + "");
        int rsid = new Integer(requestDTO.getArgs().get("rsid") + "");
        String type = new String(requestDTO.getArgs().get("type") + "");
        roleSkillHelper.setRoleSkillToRole(uid, urid, rsid, type);

        return SlgData.getData();
    }

    @Override
    public SlgData upgrade(SlgRequestDTO requestDTO) throws RequestErrorException {

        int uid = requestDTO.getUid();
        int ursid = new Integer(requestDTO.getArgs().get("ursid") + "");
        int urid = new Integer(requestDTO.getArgs().get("urid") + "");

        roleSkillHelper.upgrade(uid, urid, ursid);

        return SlgData.getData();
    }
}
