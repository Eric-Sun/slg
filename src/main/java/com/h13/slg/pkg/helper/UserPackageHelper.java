package com.h13.slg.pkg.helper;

import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import com.h13.slg.core.util.MapUtil;
import com.h13.slg.equip.EquipConstants;
import com.h13.slg.equip.helper.UserEquipHelper;
import com.h13.slg.pkg.PackageConstants;
import com.h13.slg.role.RoleConstants;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.pkg.dao.UserPackageDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    /**
     * 初始化用户的时候，创建一个默认的用户背包对象
     *
     * @param id
     */
    public void create(long id) {
        userPackageDAO.insert(id,
                new HashMap<String, Integer>(),
                new HashMap<String, List<Long>>(),
                new HashMap<String, Integer>(),
                new HashMap<String, Integer>());
        LOG.info("create new package. uid=" + id);
    }

    /**
     * 更新宝石包裹
     *
     * @param userPackageCO
     */
    public void updateGem(UserPackageCO userPackageCO) {
        userPackageDAO.updateGem(userPackageCO.getId(), userPackageCO.getGem());
    }

    /**
     * 更新材料包裹
     *
     * @param userPackageCO
     */
    public void updateMaterial(UserPackageCO userPackageCO) {
        userPackageDAO.updateMaterial(userPackageCO.getId(), userPackageCO.getMaterial());
    }

    /**
     * 更新卡牌包裹
     *
     * @param userPackageCO
     */
    public void updateRoleCard(UserPackageCO userPackageCO) {
        userPackageDAO.updateRoleCard(userPackageCO.getId(), userPackageCO.getRoleCard());
    }

    /**
     * 更新装备包裹
     *
     * @param userPackageCO
     */
    public void updateEquip(UserPackageCO userPackageCO) {
        userPackageDAO.updateEquip(userPackageCO.getId(), userPackageCO.getEquip());
    }

    /**
     * 获得用户的包裹
     *
     * @param uid
     * @return
     */
    public UserPackageCO get(long uid) {
        return userPackageDAO.get(uid);
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
            SlgLogger.error(SlgLoggerEntity.p("skill", "update", uid, "material is not enough")
                    .addParam("uid", uid)
                    .addParam("materialId", materialId)
                    .addParam("needNum", materialNum)
                    .addParam("haveNum", packageMCount));
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
        SlgLogger.info(SlgLoggerEntity.p("package", "subtract material", uid, "ok")
                .addParam("uid", uid)
                .addParam("materialId", materialId)
                .addParam("materialNum", materialNum)
                .addParam("finalNum", newCount));
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
    public void addGemItem(long uid, long itemId, int num) {
        UserPackageCO userPackageCO = get(uid);
        Map data = userPackageCO.getGem();
        MapUtil.addItem(data, itemId + "", num);
        updateGem(userPackageCO);
    }

    /**
     * 向卡牌包裹内加入新的物品
     *
     * @param uid
     * @param itemId
     * @param num
     */
    public void addRoleCodeItem(long uid, long itemId, int num) {
        UserPackageCO userPackageCO = get(uid);
        Map data = userPackageCO.getRoleCard();
        MapUtil.addItem(data, itemId + "", num);
        updateRoleCard(userPackageCO);
    }

    /**
     * 向装备包裹内加入新的物品
     *
     * @param uid
     * @param itemId
     * @param id
     */
    public void addEquipItem(long uid, long itemId, long id) {
        UserPackageCO userPackageCO = get(uid);
        Map data = userPackageCO.getEquip();
        MapUtil.appendItem(data, itemId + "", id);
        updateEquip(userPackageCO);
    }


    /**
     * 注册的时候用于给新用户添加一些基础装备
     * 3 weapon
     * 3 accessory
     * 3 armor
     *
     * @param uid
     */
    public void addSomeEquipForRegister(long uid) throws RequestErrorException {
        long ueid1 = 0;
        long ueid2 = 0;
        long ueid3 = 0;
        for (int i = 0; i < 3; i++) {
            ueid1 = userEquipHelper.getANewUserEquip(uid, 1, EquipConstants.EquipType.ACCESSORY);
            ueid2 = userEquipHelper.getANewUserEquip(uid, 1, EquipConstants.EquipType.ARMOR);
            ueid3 = userEquipHelper.getANewUserEquip(uid, 1, EquipConstants.EquipType.WEAPON);
            SlgLogger.info(SlgLoggerEntity.p("package", "addSomeEquipForRegister", uid, "ok")
                    .addParam("weapon", ueid3)
                    .addParam("accessory", ueid1)
                    .addParam("armor", ueid2));
        }
    }
}
