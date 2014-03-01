package com.h13.slg.bar.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.bar.co.BarCO;
import com.h13.slg.bar.co.BarRoleCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午8:20
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BarDAO {

    @Autowired
    JdbcTemplate j;

    public void insert(long uid, List<BarRoleCO> roleList) {
        String sql = "insert into user_bar (id,role_list,createtime) values (?,?,now())";
        j.update(sql, new Object[]{uid, JSON.toJSONString(roleList)});
    }

    public void update(long uid, List<BarRoleCO> roleList) {
        String sql = "update user_bar set role_list=? where id=?";
        j.update(sql, new Object[]{JSON.toJSONString(roleList), uid});
    }

    public BarCO get(long uid) {
        String sql = "select id,role_list from user_bar where id=?";
        return j.queryForObject(sql, new Object[]{uid}, new BeanPropertyRowMapper<BarCO>(BarCO.class));
    }

}
