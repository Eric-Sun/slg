package com.h13.slg.battle.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.battle.co.UserTeamCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-6
 * Time: 上午12:36
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserTeamDAO {

    @Autowired
    JdbcTemplate j;

    public void insert(long id, String data) {
        String sql = "insert into user_team (id,data,createtime) values (?,?,now())";
        j.update(sql, new Object[]{id, data});
    }

    public void update(long id, String data) {
        String sql = "update user_team set data=? where id=?";
        j.update(sql, new Object[]{data, id});
    }


    public UserTeamCO get(long id) {
        final String sql = "select id,data from user_team where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<UserTeamCO>(UserTeamCO.class));
    }
}
