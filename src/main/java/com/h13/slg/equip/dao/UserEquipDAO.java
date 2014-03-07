package com.h13.slg.equip.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.equip.co.UserEquipCO;
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
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-24
 * Time: 下午7:45
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserEquipDAO {

    @Autowired
    JdbcTemplate j;

    public long insert(final long uid,
                       final String type, final int level, final String gems,
                       final int strength, final int fail, final int refine,
                       final int star) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into user_equip(uid," +
                "type,level,gems,strength,fail,refine,star,createtime) " +
                "values " +
                "(?,?,?,?,?,?,?,?,now())";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, uid);
                pstmt.setString(2, type);
                pstmt.setInt(3, level);
                pstmt.setString(4, gems);
                pstmt.setInt(5, strength);
                pstmt.setInt(6, fail);
                pstmt.setInt(7, refine);
                pstmt.setInt(8, star);
                return pstmt;
            }
        }, holder);
        return holder.getKey().longValue();
    }

    public void update(long id, int level, Map<String, String> gems, int strength, int fail, int refine, int star) {
        String sql = "update user_equip set type=?,level=?,gems=?,strength=?,fail=?,refine=?,star=?,e_id=? where id=?";
        j.update(sql, new Object[]{level, JSON.toJSONString(gems), strength, fail, refine, star, id});
    }

    public UserEquipCO get(long id) {
        String sql = "select id,uid,type,level,gems,strength,fail,refine,star,createtime from user_equip where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<UserEquipCO>(UserEquipCO.class));
    }
}
