package com.h13.slg.role.dao;

import com.h13.slg.role.co.UserEquipCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public void insert(long id, int type, int level, String gems, int strength, int fail, int refine, int star) {
        String sql = "insert into user_equip(id,type,level,gems,strength,fail,refine,star,createtime) " +
                "values " +
                "(?,?,?,?,?,?,?,?,now())";
        j.update(sql, new Object[]{id, type, level, gems, strength, fail, refine, star});
    }

    public void update(long id, int level, String gems, int strength, int fail, int refine, int star) {
        String sql = "update user_equip set type=?,level=?,gems=?,strength=?,fail=?,refine=?,star=? where id=?";
        j.update(sql, new Object[]{level, gems, strength, fail, refine, star});
    }

    public UserEquipCO get(long id) {
        String sql = "select id,type,level,gems,strength,fail,refine,star,createtime from user_equip where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<UserEquipCO>(UserEquipCO.class));
    }
}
