package com.h13.slg.battle.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.battle.co.UserTeamCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

    public void insert(long id, List<Integer> data) {
        String sql = "insert into user_team (id,data,createtime) values (?,?,now())";
        j.update(sql, new Object[]{id, JSON.toJSONString(data)});
    }

    public void update(long id, List<Integer> data) {
        String sql = "update user_team set data=? where id=?";
        j.update(sql, new Object[]{JSON.toJSONString(data), id});
    }


    public UserTeamCO get(long id) {
        final String sql = "select id,data from user_team where id=?";
        return j.queryForObject(sql, new Object[]{id}, new RowMapper<UserTeamCO>() {
            @Override
            public UserTeamCO mapRow(ResultSet resultSet, int i) throws SQLException {
                UserTeamCO userTeamCO = new UserTeamCO();
                userTeamCO.setId(resultSet.getInt(1));
                userTeamCO.setData(JSON.parseObject(resultSet.getString(2), List.class));
                return userTeamCO;
            }
        });
    }
}
