package com.h13.slg.bar.helper;

import com.h13.slg.bar.co.BarCO;
import com.h13.slg.bar.co.BarRoleCO;
import com.h13.slg.bar.dao.BarDAO;
import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 招贤馆
 */
@Service
public class BarHelper {

    private static List<BarRoleCO> DEFAULT_ROLE_LIST = new ArrayList<BarRoleCO>();

    @Autowired
    BarDAO barDAO;

    /**
     * 创建一个人物的时候创建一个招贤馆对象
     * @param uid
     */
    public void create(long uid) {
        barDAO.insert(uid, DEFAULT_ROLE_LIST);
        SlgLogger.info(SlgLoggerEntity.p("bar","create",uid,"create a new bar."));
    }

    /**
     * 获取当前招贤馆的信息
     * @param uid
     * @return
     */
    public BarCO get(long uid) {
        return barDAO.get(uid);
    }



}

