package com.h13.slg.role.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.role.co.UserRoleCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    public int insert(final long roleId, final long uid,
                      final long weapon,
                      final long armor, final long accessory,
                      final int level, final int fightForce,
                      final int attack, final int defence, final int health, final int soldier,
                      final String roleName, final int xp,
                      final int putongSkillId, final int tianfuSkillId) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into user_role " +
                "(role_id,uid,weapon,armor,accessory,level,fight_force," +
                "attack,defence,health,createtime,soldier,role_name,xp,putong_skill_id,tianfu_skill_id) " +
                "values (?,?,?,?,?,?,?,?,?,?,now(),?,?,?,?,?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setLong(1, roleId);
                pstmt.setLong(2, uid);
                pstmt.setLong(3, weapon);
                pstmt.setLong(4, armor);
                pstmt.setLong(5, accessory);
                pstmt.setInt(6, level);
                pstmt.setInt(7, fightForce);
                pstmt.setInt(8, attack);
                pstmt.setInt(9, defence);
                pstmt.setInt(10, health);
                pstmt.setInt(11, soldier);
                pstmt.setString(12, roleName);
                pstmt.setInt(13, xp);
                pstmt.setInt(14, putongSkillId);
                pstmt.setInt(15, tianfuSkillId);
                return pstmt;
            }
        }, holder);
        return new Long(holder.getKey().longValue()).intValue();
    }

    public UserRoleCO get(long uid, long urId) {
        String sql = "select id,role_id,uid,weapon,armor,accessory,level,fight_force,attack,defence,health" +
                ",soldier,role_name,xp,putong_skill_id,tianfu_skill_id from user_role where id=? and uid=?";
        return j.queryForObject(sql, new Object[]{urId, uid}, new BeanPropertyRowMapper<UserRoleCO>(UserRoleCO.class));
    }


    public List<UserRoleCO> getRoleList(long uid) {
        String sql = "select id,role_id,uid,weapon,armor,accessory,level,fight_force,attack,defence,health" +
                ",soldier,role_name,xp,putong_skill_id,tianfu_skill_id from user_role where uid=?";
        return j.query(sql, new Object[]{uid}, new BeanPropertyRowMapper<UserRoleCO>(UserRoleCO.class));
    }


    public void update(long urId, long weapon, long armor, long accessory, int fightForce, int level,
                        String roleName, int xp,int putongSkillId,
                       int tianfuSkillId) {
        String sql = "update user_role set weapon=?,armor=?,accessory=?,fight_force=?,level=?," +
                "role_name=?,xp=?,putong_skill_id=?,tianfu_skill_id=? " +
                "where id=?";
        j.update(sql, new Object[]{weapon, armor, accessory, fightForce, level, roleName, xp, putongSkillId,tianfuSkillId,
                urId});
    }


    public boolean check(long uid, int urid) {
        String sql = "select count(1) from user_role where id=? and uid=?";
        int count = j.queryForInt(sql, new Object[]{urid, uid});
        if (count == 1)
            return true;
        else
            return false;
    }
}
