package com.h13.slg.business.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.business.co.UserBusinessCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-12-8
 * Time: 下午7:26
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserBusinessDAO {


    @Autowired
    JdbcTemplate j;

    public void insert(final UserBusinessCO userBusinessCO) {
        final String sql = "insert into user_business (id,step,result,pass,createtime) values (?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, userBusinessCO.getId());
                pstmt.setInt(2, userBusinessCO.getStep());
                pstmt.setString(3, JSON.toJSONString(userBusinessCO.getResult()));
                pstmt.setInt(3, userBusinessCO.getPass());
                return pstmt;
            }
        });
    }

    public void update(UserBusinessCO userBusinessCO) {
        String sql = "update user_business set step=?,result=?,pass=? where id=?";
        j.update(sql, new Object[]{userBusinessCO.getStep(),
                JSON.toJSONString(userBusinessCO.getResult()),
                userBusinessCO.getPass(),
                userBusinessCO.getId()});
    }

    public UserBusinessCO get(int uid) {
        String sql = "select id,step,result,pass from user_business where id=?";
        return j.queryForObject(sql, new Object[]{uid}, new RowMapper<UserBusinessCO>() {
            @Override
            public UserBusinessCO mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserBusinessCO userBusinessCO = new UserBusinessCO();
                userBusinessCO.setId(rs.getInt(1));
                userBusinessCO.setStep(rs.getInt(2));
                userBusinessCO.setResult(JSON.parseObject(rs.getString(3), List.class));
                userBusinessCO.setPass(rs.getInt(4));
                return userBusinessCO;
            }
        });
    }

}
