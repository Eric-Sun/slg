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

    public int insert(final String name, final String passwordAfterMd5) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into user (name,password,createtime) values (?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, passwordAfterMd5);
                return pstmt;
            }
        }, holder);
        return holder.getKey().intValue();
    }


    /**
     * if result is -1 . user not exists
     *
     * @param name
     * @param passwordAfterMd5
     * @return
     */
    public int login(final String name, final String passwordAfterMd5) {
        try {
            final String sql = "select id from user where name=? and password=?";
            return j.queryForInt(sql, new Object[]{name, passwordAfterMd5});
        } catch (Exception e) {
            SlgLogger.error(SlgLoggerEntity.p("user", "login", -1, "error")
                    .addParam("name", name), e);
            return -1;
        }
    }


    public boolean check(String name) {
        final String sql = "select count(*) from user where name=?";

        int cnt = j.queryForInt(sql, new Object[]{name});
        if (cnt == 0)
            return true;
        else
            return false;

    }
}
