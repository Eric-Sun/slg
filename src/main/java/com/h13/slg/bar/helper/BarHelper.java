package com.h13.slg.bar.helper;

import com.h13.slg.bar.co.BarCO;
import com.h13.slg.bar.co.BarRoleCO;
import com.h13.slg.bar.dao.BarDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午8:25
 * To change this template use File | Settings | File Templates.
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

