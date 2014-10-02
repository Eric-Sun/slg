package com.h13.slg.equip.dao;

import com.h13.slg.core.SlgConstants;
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
import java.util.List;

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
                       final String type, final int level,
                       final int strength, final long urid, final String name) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into user_equip(uid," +
                "type,level,strength,urid,createtime,name) " +
                "values " +
                "(?,?,?,?,?,now(),?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, uid);
                pstmt.setString(2, type);
                pstmt.setInt(3, level);
                pstmt.setInt(4, strength);
                pstmt.setLong(5, urid);
                pstmt.setString(6, name);
                return pstmt;
            }
        }, holder);
        return holder.getKey().longValue();
    }

    public void update(long id, int level, int strength, long urid,
                       String name) {
        String sql = "update user_equip set level=?,strength=?,urid=?,name=? where id=?";
        j.update(sql, new Object[]{level, strength, urid, name, id});
    }

    public UserEquipCO get(long uid, long ueid) {
        String sql = "select id,uid,urid,type,level,strength,createtime,name from user_equip where id=?" +
                " and uid=?";
        return j.queryForObject(sql, new Object[]{ueid, uid}, new BeanPropertyRowMapper<UserEquipCO>(UserEquipCO.class));
    }

    public UserEquipCO get(long uid, long urid, String type) {
        String sql = "select id,uid,urid,type,level,strength,createtime,name " +
                "from user_equip where uid=? and urid=? and type=?";
        return j.queryForObject(sql, new Object[]{uid, urid, type},
                new BeanPropertyRowMapper<UserEquipCO>(UserEquipCO.class));

    }

    public List<UserEquipCO> equipList(long uid, String type) {

        String sql = "select id,uid,urid,type,level,strength,createtime,name " +
                "from user_equip where uid=? and type=?";
        return j.query(sql, new Object[]{uid, type},
                new BeanPropertyRowMapper<UserEquipCO>(UserEquipCO.class));
    }

    public List<UserEquipCO> noUsedEquipList(long uid, String type) {
        String sql = "select id,uid,urid,type,level,strength,createtime,name " +
                "from user_equip where uid=? and  urid=? and type=?";
        return j.query(sql, new Object[]{uid, SlgConstants.Role.NO_ROLE, type},
                new BeanPropertyRowMapper<UserEquipCO>(UserEquipCO.class));
    }

    public List<UserEquipCO> getUserEquips(int uid) {
        String sql = "select id,uid,urid,type,level,strength,createtime,name " +
                "from user_equip where uid=?";
        return j.query(sql, new Object[]{uid},
                new BeanPropertyRowMapper<UserEquipCO>(UserEquipCO.class));
    }
}
