package com.h13.slg.pkg.helper;

import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.core.util.MapUtil;
import com.h13.slg.pkg.PackageConstants;
import com.h13.slg.role.RoleConstants;
import com.h13.slg.pkg.co.UserPackageCO;
import com.h13.slg.pkg.dao.UserPackageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-26
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserPackageHelper {

    @Autowired
    UserPackageDAO userPackageDAO;

    public void create(long id) {
        userPackageDAO.insert(id, new HashMap<String, Integer>(), new HashMap<String, Integer>(),
                new HashMap<String, Integer>(), new HashMap<String, Integer>());
    }

    public void updateGem(UserPackageCO userPackageCO) {
        userPackageDAO.updateGem(userPackageCO.getId(), userPackageCO.getGem());
    }

    public void updateMaterial(UserPackageCO userPackageCO) {
        userPackageDAO.updateMaterial(userPackageCO.getId(), userPackageCO.getMaterial());
    }

    public void updateRoleCard(UserPackageCO userPackageCO) {
        userPackageDAO.updateRoleCard(userPackageCO.getId(), userPackageCO.getRoleCard());
    }

    public void updateEquip(UserPackageCO userPackageCO) {
        userPackageDAO.updateEquip(userPackageCO.getId(), userPackageCO.getEquip());
    }

    public UserPackageCO get(long uid) {
        return userPackageDAO.get(uid);
    }

    /**
     * 添加新的物品到背包中
     *
     * @param uid
     * @param type
     * @param itemId
     * @param num
     */
    public void addItems(long uid, String type, long itemId, int num) throws RequestErrorException {
        UserPackageCO userPackageCO = get(uid);
        if (type.equals(PackageConstants.PACKAGE.MATERIAL_SPACE)) {
            MapUtil.addItem(userPackageCO.getMaterial(), itemId + "", num);
            updateMaterial(userPackageCO);
        } else if (type.equals(PackageConstants.PACKAGE.GEM_SPACE)) {
            MapUtil.addItem(userPackageCO.getGem(), itemId + "", num);
            updateGem(userPackageCO);
        } else if (type.equals(PackageConstants.PACKAGE.EQUIP_SPACE)) {
            MapUtil.addItem(userPackageCO.getEquip(), itemId + "", num);
            updateEquip(userPackageCO);
        } else if (type.equals(PackageConstants.PACKAGE.ROLE_CODE_SPACE)) {
            MapUtil.addItem(userPackageCO.getRoleCard(), itemId + "", num);
            updateRoleCard(userPackageCO);
        } else {
            throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, type + " is not in pkg.");
        }
    }


}
