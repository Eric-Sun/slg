package com.h13.slg.user.dao;

import com.h13.slg.user.co.UserLevelCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-26
 * Time: 下午5:22
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserLevelDAO {

    @Autowired
    JdbcTemplate j;

    public UserLevelCO get(int userLevelId) {
        final String sql = "select * from user_level where id=?";
        return j.queryForObject(sql, new Object[]{userLevelId}, new BeanPropertyRowMapper<UserLevelCO>());
    }

    public void insert(final int id, final int fromExp, final int toExp, final int foodCapacity, final int goldCapacity) {
        final String sql = "insert into user_level " +
                "(id,from_exp,to_exp,food_capacity,gold_capacity,createtime) " +
                "values " +
                "(?,?,?,?,?,now()) ";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setInt(1, id);
                pstmt.setInt(2, fromExp);
                pstmt.setInt(3, toExp);
                pstmt.setInt(4, foodCapacity);
                pstmt.setInt(5, goldCapacity);
                return pstmt;
            }
        });
    }

}
