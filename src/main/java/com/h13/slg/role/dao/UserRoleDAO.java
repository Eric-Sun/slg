package com.h13.slg.role.dao;

import com.h13.slg.role.co.UserRoleCO;
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

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-21
 * Time: 下午5:58
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserRoleDAO {

    @Autowired
    JdbcTemplate j;

    public long insert(final long roleId, final int weapon,
                       final int armor, final int accessory) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into user_role (role_id,weapon,armor,accessory,createtime) " +
                "values (?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, roleId);
                pstmt.setInt(2, weapon);
                pstmt.setInt(3, armor);
                pstmt.setInt(4, accessory);
                return pstmt;
            }
        }, holder);
        return holder.getKey().longValue();
    }

    public UserRoleCO get(long urId) {
        String sql = "select id,role_id,weapon,armor,accessory from user_role where id=?";
        return j.queryForObject(sql, new Object[]{urId}, new BeanPropertyRowMapper<UserRoleCO>(UserRoleCO.class));
    }


    public void update(long urId, int weapon, int armor, int accessory) {
        String sql = "update user_role set weapon=?,armor=?,accessory=? " +
                "where id=?";
        j.update(sql, new Object[]{weapon, armor, accessory, urId});
    }


}
