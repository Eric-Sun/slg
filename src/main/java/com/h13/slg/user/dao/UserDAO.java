package com.h13.slg.user.dao;

import com.h13.slg.core.log.SlgLogger;
import com.h13.slg.core.log.SlgLoggerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-14
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserDAO {


    @Autowired
    JdbcTemplate j;

    public int insert(final int aid, final String name) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into user (name,aid,createtime) values (?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setInt(2, aid);
                return pstmt;
            }
        }, holder);
        return holder.getKey().intValue();
    }



    public boolean check(String name) {
        final String sql = "select count(*) from user where name=?";

        int cnt = j.queryForInt(sql, new Object[]{name});
        if (cnt == 0)
            return true;
        else
            return false;

    }

    public int getInfoByAid(int aid) {
        final String sql = "select uid from user where aid=? ";
        return j.queryForInt(sql, new Object[]{aid});
    }
}
