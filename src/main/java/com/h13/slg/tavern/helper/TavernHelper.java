package com.h13.slg.tavern.helper;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.h13.slg.config.co.RoleCO;
import com.h13.slg.config.co.TavernConfigCO;
import com.h13.slg.config.fetcher.GlobalConfigFetcher;
import com.h13.slg.config.fetcher.RoleConfigFetcher;
import com.h13.slg.config.fetcher.TavernConfigFetcher;
import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.tavern.TavernConstants;
import com.h13.slg.tavern.co.TavernCO;
import com.h13.slg.tavern.co.TavernRoleCO;
import com.h13.slg.tavern.dao.TavernDAO;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.tavern.vo.EnrollUserRoleVO;
import com.h13.slg.tavern.vo.InviteTavernVO;
import com.h13.slg.tavern.vo.TavernRoleVO;
import com.h13.slg.user.co.UserStatusCO;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 招贤馆
 */
@Service
public class TavernHelper {

    private static List<TavernRoleCO> DEFAULT_ROLE_LIST = new ArrayList<TavernRoleCO>();

    @Autowired
    TavernDAO tavernDAO;
    @Autowired
    RoleConfigFetcher roleConfigFetcher;
    @Autowired
    GlobalConfigFetcher globalConfigFetcher;
    @Autowired
    UserStatusHelper userStatusHelper;
    @Autowired
    TavernConfigFetcher tavernConfigFetcher;
    @Autowired
    UserRoleHelper userRoleHelper;

    /**
     * 创建一个人物的时候创建一个招贤馆对象
     *
     * @param uid
     */
    public void create(long uid) {
        tavernDAO.insert(uid, DEFAULT_ROLE_LIST);
        SlgLogger.info(SlgLoggerEntity.p("tavern", "create", uid, "create a new tavern."));
    }

    /**
     * 获取当前招贤馆的信息
     *
     * @param uid
     * @return
     */
    public TavernCO get(long uid) {
        return tavernDAO.get(uid);
    }


    /**
     * 更新tavern
     *
     * @param tavernCO
     */
    public void update(TavernCO tavernCO) {
        tavernDAO.update(tavernCO.getId(), tavernCO.getRoleList());
    }

    /**
     * 检查tavern是否已经准备好了，如果其中已经有数据则为为准备好，如果没有数据则为准备好了
     *
     * @param uid
     * @return
     */
    private boolean checkTavernIsReady(long uid) {
        TavernCO co = get(uid);
        if (co.getRoleList().size() == 0)
            return true;
        else
            return false;
    }


    /**
     * 清除招贤馆的所有人，把卡牌转化为soul，并且返回这个soul
     *
     * @param uid
     * @return
     * @throws RequestErrorException
     */
    public int leave(long uid) throws RequestErrorException {
        TavernCO co = get(uid);
        if (co.getRoleList().size() == 0) {
            SlgLogger.error(SlgLoggerEntity.p("tavern", "invite", uid, "tavern is empty"));
            throw new RequestErrorException(ErrorCodeConstants.Tavern.TAVERN_IS_EMPTY, "");
        }
        int soul = 0;
        List<TavernRoleCO> list = co.getRoleList();
        for (TavernRoleCO tavernRoleCO : list) {
            long roleId = tavernRoleCO.getId();
            RoleCO roleCO = roleConfigFetcher.get(roleId + "");
            String quality = roleCO.getQuantity();
            if (quality.equals("orange")) {
                soul += globalConfigFetcher.getIntValue("RoleToSoulorange");
            } else if (quality.equals("purple")) {
                soul += globalConfigFetcher.getIntValue("RoleToSoulpurple");
            } else if (quality.equals("blue")) {
                soul += globalConfigFetcher.getIntValue("RoleToSoulblue");
            } else {
                soul += globalConfigFetcher.getIntValue("RoleToSoulwhite");
            }
        }
        co.setRoleList(new LinkedList<TavernRoleCO>());
        update(co);

        userStatusHelper.addSoul(uid, soul);
        return soul;
    }

    public InviteTavernVO invite(long uid) throws RequestErrorException {
        if (!checkTavernIsReady(uid)) {
            SlgLogger.error(SlgLoggerEntity.p("tavern", "invite", uid, "tavern is full")
            );
            throw new RequestErrorException(ErrorCodeConstants.Tavern.TAVERN_IS_FULL, "");
        }

        int cost = 0;
        InviteTavernVO vo = new InviteTavernVO();
        List<TavernRoleCO> tList = new LinkedList<TavernRoleCO>();
        List<TavernRoleVO> list = new LinkedList<TavernRoleVO>();
        // 一共邀请10个人
        int level = 1;
        for (int i = 0; i < 10; i++) {
            List<Object> person = new LinkedList<Object>();
            TavernConfigCO tavernConfigCO = tavernConfigFetcher.get(level + "");
            int gold = inviteGold(tavernConfigCO);
            cost += gold;
            String quality = randomQuality(tavernConfigCO);
            level = randomNextLevel(level, tavernConfigCO);
            long size = roleConfigFetcher.getZhaoxianSize(quality);
            long roleId = roleConfigFetcher.getFromZhaoxian(randomId(size), quality);
            String roleName = roleConfigFetcher.get(roleId + "").getName();

            TavernRoleVO tavernRoleVO = new TavernRoleVO();
            tavernRoleVO.setGold(gold);
            tavernRoleVO.setId(roleId);
            tavernRoleVO.setRoleName(roleName);

            list.add(tavernRoleVO);
            TavernRoleCO tco = new TavernRoleCO();
            tco.setId(roleId);
            tco.setStatus(TavernConstants.DEFAULT);
            tList.add(tco);
        }
        vo.setData(list);
        tavernDAO.update(uid, tList);

        // 减掉cost的金币
        userStatusHelper.subtractGold(uid, cost);
        return vo;
    }

    private int inviteGold(TavernConfigCO tavernConfigCO) {
        return tavernConfigCO.getGold();
    }


    private String randomQuality(TavernConfigCO tavernConfigCO) {
        int min = 1;
        int max = 100;
        Random random = new Random();
        int result = random.nextInt(max) % (max - min + 1) + min;

        if (result < tavernConfigCO.getOrange()) {
            return "orange";
        } else if (result < tavernConfigCO.getBlue() +
                tavernConfigCO.getOrange()) {
            return "blue";
        } else if (result < tavernConfigCO.getPurple() +
                tavernConfigCO.getBlue() +
                tavernConfigCO.getOrange()) {
            return "purple";
        } else {
            return "white";
        }
    }

    private int randomNextLevel(int curLevel, TavernConfigCO tavernConfigCO) {
        int rate = tavernConfigCO.getUpRate();
        int min = 1;
        int max = 100;
        Random random = new Random();
        int result = random.nextInt(max) % (max - min + 1) + min;
        if (result > rate) {
            return (curLevel - 1) == 0 ? 1 : curLevel - 1;
        } else {
            curLevel++;
            return curLevel;
        }
    }


    private int randomId(long max) {
        Random random = new Random();
        return random.nextInt(new Integer(max + ""));
    }


    public EnrollUserRoleVO enroll(long uid, int pos) throws RequestErrorException {
        EnrollUserRoleVO vo = new EnrollUserRoleVO();
        try {
            // enroll
            TavernCO tavernCO = get(uid);
            TavernRoleCO tavernRoleCO = tavernCO.getRoleList().get(pos);
            tavernRoleCO.setStatus(1);
            update(tavernCO);
            long roleId = tavernRoleCO.getId();
            RoleCO roleCO = roleConfigFetcher.get(roleId + "");
            UserRoleCO userRoleCO = userRoleHelper.add(uid, roleCO.getId());

            BeanUtils.copyProperties(vo, userRoleCO);
            return vo;
        } catch (Exception e) {
            throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "", e);
        }

    }

}

