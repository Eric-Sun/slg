package com.h13.slg.pkg.helper;

import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.core.util.MapUtil;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.pkg.cache.UserPackageCache;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.pkg.dao.UserPackageDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用户包裹的类，提供所有的用户包裹相关的操作
 */
@Service
public class UserPackageHelper {
    private static Logger LOG = LoggerFactory.getLogger(UserPackageHelper.class);

    @Autowired
    UserPackageDAO userPackageDAO;
    @Autowired
    UserEquipHelper userEquipHelper;
    @Autowired
    UserPackageCache userPackageCache;

    /**
     * 初始化用户的时候，创建一个默认的用户背包对象
     *
     * @param uid
     */
    public UserPackageCO createDefaultPackage(int uid) {
        UserPackageCO userPackageCO = new UserPackageCO();
        userPackageCO.setId(uid);
        userPackageCO.setEquip(new LinkedList<Integer>());
        userPackageCO.setMaterial(new HashMap<String, Integer>());
        userPackageCO.setSkill(new HashMap<String, Integer>());
        userPackageDAO.insert(userPackageCO.getId(),
                userPackageCO.getEquip(),
                userPackageCO.getMaterial(),
                userPackageCO.getSkill());

        userPackageCache.set(userPackageCO);
        return userPackageCO;
    }

    /**
     * 更新材料包裹
     *
     * @param userPackageCO
     */
    public void updateMaterial(UserPackageCO userPackageCO) {
        userPackageDAO.updateMaterial(userPackageCO.getId(), userPackageCO.getMaterial());
        userPackageCache.set(userPackageCO);
    }

    /**
     * 更新装备包裹
     *
     * @param userPackageCO
     */
    public void updateEquip(UserPackageCO userPackageCO) {
        userPackageDAO.updateEquip(userPackageCO.getId(), userPackageCO.getEquip());
        userPackageCache.set(userPackageCO);
    }

    /**
     * 更新技能包裹
     *
     * @param userPackageCO
     */
    private void updateSkill(UserPackageCO userPackageCO) {
        userPackageDAO.updateSkill(userPackageCO.getId(), userPackageCO.getSkill());
        userPackageCache.set(userPackageCO);
    }


    /**
     * 获得用户的包裹
     *
     * @param uid
     * @return
     */
    public UserPackageCO get(long uid) {
        UserPackageCO userPackageCO = userPackageCache.get(uid);
        if (userPackageCO == null) {
            userPackageCO = userPackageDAO.get(uid);
            userPackageCache.set(userPackageCO);
            return userPackageCO;
        } else {
            return userPackageDAO.get(uid);
        }

    }


    /**
     * 检查某个material是否够
     *
     * @param uid
     * @param materialId
     * @param materialNum
     * @return true为够，false为不够
     */
    public boolean checkMaterialEnough(long uid, long materialId, int materialNum) {
        UserPackageCO userPackageCO = get(uid);
        if (!userPackageCO.getMaterial().containsKey(materialId + ""))
            return false;
        int packageMCount = userPackageCO.getMaterial().get(materialId + "");
        if (packageMCount < materialNum) {
            return false;
        }
        return true;

    }


    /**
     * 从material背包中减去对应的material
     *
     * @param uid
     * @param materialId
     * @param materialNum
     * @return
     */
    public boolean subtractMaterial(long uid, long materialId, int materialNum) {
        UserPackageCO userPackageCO = get(uid);
        int packageMCount = userPackageCO.getMaterial().get(materialId + "");
        int newCount = packageMCount - materialNum;
        userPackageCO.getMaterial().put(materialId + "", newCount);
        updateMaterial(userPackageCO);
        return true;
    }


    /**
     * 从skill背包中减去对应的skill
     *
     * @param uid
     * @param rsId
     * @param materialNum
     * @return
     */
    public boolean subtractSkill(long uid, long rsId, int materialNum) {
        UserPackageCO userPackageCO = get(uid);
        int packageMCount = userPackageCO.getSkill().get(rsId + "");
        int newCount = packageMCount - materialNum;
        userPackageCO.getSkill().put(rsId + "", newCount);
        updateSkill(userPackageCO);
        return true;
    }


    /**
     * 向材料包裹内加入新的物品
     *
     * @param uid
     * @param itemId
     * @param num
     */
    public void addMaterialItem(long uid, long itemId, int num) {
        UserPackageCO userPackageCO = get(uid);
        Map<String, Integer> data = userPackageCO.getMaterial();
        MapUtil.addItem(data, itemId + "", num);
        updateMaterial(userPackageCO);
    }

    /**
     * 向宝石包裹内加入新的物品
     *
     * @param uid
     * @param itemId
     * @param num
     */
    public void addSkillItem(long uid, long itemId, int num) {
        UserPackageCO userPackageCO = get(uid);
        Map data = userPackageCO.getSkill();
        MapUtil.addItem(data, itemId + "", num);
        updateSkill(userPackageCO);
    }


    /**
     * 向装备包裹内加入新的物品
     *
     * @param uid
     * @param id
     */
    public void addEquipItem(long uid, long id) {
        UserPackageCO userPackageCO = get(uid);
        List data = userPackageCO.getEquip();
        data.add(id);
        updateEquip(userPackageCO);
    }


    /**
     * 获得当前包裹内某个材料的数量
     *
     * @param uid
     * @param materialId
     * @return
     */
    public int getMaterialCount(long uid, int materialId) {
        UserPackageCO userPackageCO = get(uid);
        if (userPackageCO.getMaterial().get(materialId + "") == null)
            return 0;
        else
            return userPackageCO.getMaterial().get(materialId + "");
    }


    /**
     * 检测技能包裹中制定将领技能的数量
     *
     * @param uid
     * @param usid
     * @return
     */
    public int getSkillCount(long uid, int usid) {
        UserPackageCO userPackageCO = get(uid);
        if (userPackageCO.getSkill().get(usid + "") == null)
            return 0;
        else
            return userPackageCO.getSkill().get(usid + "");
    }
}
