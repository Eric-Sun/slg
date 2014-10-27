package com.h13.slg.skill.dao;

import com.h13.slg.core.SlgConstants;
import com.h13.slg.skill.co.UserRoleSkillCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
 * Date: 14-7-24
 * Time: 下午5:40
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserRoleSkillDAO {

    @Autowired
    JdbcTemplate j;

    public int insert(final UserRoleSkillCO userRoleSkillCO) {
        KeyHolder holder = new GeneratedKeyHolder();
        final String sql = "insert into user_role_skill " +
                "(rid,uid,`type`,level,`delete`,createtime,rsid,name) values " +
                "(?,?,?,?,?,now(),?,?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, userRoleSkillCO.getRid());
                pstmt.setInt(2, userRoleSkillCO.getUid());
                pstmt.setString(3, userRoleSkillCO.getType());
                pstmt.setInt(4, userRoleSkillCO.getLevel());
                pstmt.setInt(5, userRoleSkillCO.getDelete());
                pstmt.setInt(6, userRoleSkillCO.getRsid());
                pstmt.setString(7, userRoleSkillCO.getName());
                return pstmt;
            }
        }, holder);
        return holder.getKey().intValue();

    }

    public void update(UserRoleSkillCO userRoleSkillCO) {
        final String sql = "update user_role_skill set rid=?,uid=?,`type`=?,level=?,updatetime=now(),`delete`=?,rsid=?,name=? where id=?";
        j.update(sql, new Object[]{
                userRoleSkillCO.getRid(),
                userRoleSkillCO.getUid(),
                userRoleSkillCO.getType(),
                userRoleSkillCO.getLevel(),
                userRoleSkillCO.getDelete(),
                userRoleSkillCO.getRsid(),
                userRoleSkillCO.getName(),
                userRoleSkillCO.getId()
        });
    }

    public UserRoleSkillCO get(int uid, int rid, int ursid) {
        final String sql = "select id,rid,uid,`type`,level,`delete`,rsid,name from user_role_skill where uid=? and rid=? and id=? and delete=0";
        return j.queryForObject(sql, new Object[]{uid, rid, ursid}, new BeanPropertyRowMapper<UserRoleSkillCO>(UserRoleSkillCO.class));
    }

    public void delete(int uid, int rid, int ursid) {
        final String sql = "update user_role_skill set `delete`=? where id=? and rid=? and uid=?";
        j.update(sql, new Object[]{
                UserRoleSkillCO.DELETED,
                ursid,
                rid,
                uid
        });
    }


    public UserRoleSkillCO getTianfu(int uid, int rid) {
        final String sql = "select id,rid,uid,`type`,level,`delete`,rsid,name from user_role_skill where uid=? and rid=? and `delete`=0 and `type`='tianfu'";
        try {
            return j.queryForObject(sql, new Object[]{uid, rid}, new BeanPropertyRowMapper<UserRoleSkillCO>(UserRoleSkillCO.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public UserRoleSkillCO getJiangling(int uid, int rid) {
        final String sql = "select id,rid,uid,`type`,level,`delete`,rsid,name from user_role_skill where uid=? and rid=? and `delete`=0 and `type`=?";
        try {
            return j.queryForObject(sql, new Object[]{uid, rid, SlgConstants.RoleSkillConstants.SkillType.JIANGLING}, new BeanPropertyRowMapper<UserRoleSkillCO>(UserRoleSkillCO.class));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * 获取uid对应的所有的技能
     *
     * @param uid
     * @return
     */
    public List<Integer> getAllIdListByUid(int uid) {
        final String sql = "select id from user_role_skill where uid=? and `delete`=?";
        return j.queryForList(sql, new Object[]{uid, UserRoleSkillCO.COMMON}, Integer.class);
    }


    public UserRoleSkillCO get(int uid, int ursid) {
        final String sql = "select id,rid,uid,`type`,level,`delete`,rsid,name from user_role_skill where uid=? and id=? and delete=0";
        return j.queryForObject(sql, new Object[]{uid, ursid}, new BeanPropertyRowMapper<UserRoleSkillCO>(UserRoleSkillCO.class));
    }
}
