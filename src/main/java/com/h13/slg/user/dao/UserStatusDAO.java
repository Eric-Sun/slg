package com.h13.slg.user.dao;

import com.h13.slg.user.co.UserStatusCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-24
 * Time: 下午5:50
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserStatusDAO {

    @Autowired
    JdbcTemplate j;

    public long insert(final String name, final int gold, final int food, final int cash, final int honor) {
        final KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into user_status " +
                "(name,gold,food,cash,honor,createtime) " +
                "values " +
                "(?,?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setInt(2, gold);
                pstmt.setInt(3, food);
                pstmt.setInt(4, cash);
                pstmt.setInt(5, honor);
                return pstmt;
            }
        }, holder);
        return holder.getKey().longValue();
    }


    public UserStatusCO get(final long userId) {
        final String sql = "select id,name,gold_coin,food,gold_ingot,medal,createtime where id=?";
        List<UserStatusCO> userList = j.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<UserStatusCO>(UserStatusCO.class));
        if (userList.size() == 0)
            return null;
        else
            return userList.get(0);
    }


    public UserStatusCO login(String loginName, String pwd) {
        final String sql = "select id,name,user_level_id,gold_coin,food,gold_ingot,medal " +
                "from user where login_name=? and pwd=?";
        UserStatusCO userStatusCO = j.queryForObject(sql, new Object[]{loginName, pwd},
                new BeanPropertyRowMapper<UserStatusCO>(UserStatusCO.class));
        return userStatusCO;
    }
}
