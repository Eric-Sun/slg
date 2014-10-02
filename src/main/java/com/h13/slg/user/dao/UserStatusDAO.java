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


    public void insert(final long id, final String name,
                       final int gold, final int food, final int cash,
                       final int level, final int xp
            , final int fightForce) {
        final String sql = "insert into user_status " +
                "(id,name,gold,food,cash,level,xp,fight_force,createtime) " +
                "values " +
                "(?,?,?,?,?,?,?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, id);
                pstmt.setString(2, name);
                pstmt.setInt(3, gold);
                pstmt.setInt(4, food);
                pstmt.setInt(5, cash);
                pstmt.setInt(6, level);
                pstmt.setInt(7, xp);
                pstmt.setInt(8, fightForce);
                return pstmt;
            }
        });
    }


    public UserStatusCO get(long id) {
        String sql = "select id,name,gold,food," +
                "cash,level,createtime,xp,fight_force from user_status where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<UserStatusCO>(UserStatusCO.class));
    }

    public void update(UserStatusCO userStatusCO) {
        final String sql = "update user_status set " +
                "food=?," +
                "gold=?," +
                "cash=?," +
                "level=?," +
                "xp=?," +
                "fight_force=? where id=?";
        j.update(sql, new Object[]{userStatusCO.getFood(), userStatusCO.getGold(), userStatusCO.getCash(),
                userStatusCO.getLevel(),
                userStatusCO.getXp()
                , userStatusCO.getFightForce(), userStatusCO.getId()});
    }
}
