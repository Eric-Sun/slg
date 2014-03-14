package com.h13.slg.tavern.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.tavern.co.TavernCO;
import com.h13.slg.tavern.co.TavernRoleCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午8:20
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TavernDAO {

    @Autowired
    JdbcTemplate j;

    public void insert(long uid, List<TavernRoleCO> roleList) {
        String sql = "insert into user_tavern (id,role_list,createtime) values (?,?,now())";
        j.update(sql, new Object[]{uid, JSON.toJSONString(roleList)});
    }

    public void update(long uid, List<TavernRoleCO> roleList) {
        String sql = "update user_tavern set role_list=? where id=?";
        j.update(sql, new Object[]{JSON.toJSONString(roleList), uid});
    }

    public TavernCO get(long uid) {
        String sql = "select id,role_list from user_tavern where id=?";
        return j.queryForObject(sql, new Object[]{uid}, new RowMapper<TavernCO>() {
            @Override
            public TavernCO mapRow(ResultSet resultSet, int i) throws SQLException {
                TavernCO co = new TavernCO();
                co.setId(resultSet.getLong(1));
                co.setRoleList(JSON.parseArray(resultSet.getString(2), TavernRoleCO.class));
                return co;
            }
        });
    }

}
