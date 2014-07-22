package com.h13.slg.skill.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.config.co.ZuLingCO;
import com.h13.slg.skill.co.UserZuLingCO;
import com.h13.slg.skill.co.UserZuLingItemCO;
import com.h13.slg.tavern.co.TavernCO;
import com.h13.slg.tavern.co.TavernRoleCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-22
 * Time: 下午5:46
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserZuLingDAO {

    @Autowired
    JdbcTemplate j;


    public void insert(long uid, List<UserZuLingItemCO> list) {
        String sql = "insert into user_zuling (id,list,createtime) values (?,?,now())";
        j.update(sql, new Object[]{uid, JSON.toJSONString(list)});
    }

    public void update(long uid, List<UserZuLingItemCO> list) {
        String sql = "update user_zuling set list=? where id=?";
        j.update(sql, new Object[]{JSON.toJSONString(list), uid});
    }

    public UserZuLingCO get(long uid) {
        String sql = "select id,list from user_zuling where id=?";
        return j.queryForObject(sql, new Object[]{uid}, new RowMapper<UserZuLingCO>() {
            @Override
            public UserZuLingCO mapRow(ResultSet resultSet, int i) throws SQLException {
                UserZuLingCO co = new UserZuLingCO();
                co.setId(resultSet.getInt(1));
                co.setList(JSON.parseArray(resultSet.getString(2), UserZuLingItemCO.class));
                return co;
            }
        });
    }


}
