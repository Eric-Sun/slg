package com.h13.slg.user.dao;

import com.h13.slg.user.co.UserTimerCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 下午3:59
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserTimerDAO {

    @Autowired
    JdbcTemplate j;

    public void add(UserTimerCO userTimerCO) {
        final String sql = "insert into user_timer" +
                "(id,food,gold) " +
                "values " +
                "(?,?,?)";
        j.update(sql, new Object[]{userTimerCO.getId(), userTimerCO.getFood(),userTimerCO.getGold()});
    }

    public void update(UserTimerCO userTimerCO) {
        final String sql = "update user_timer set food=?,gold=? where id=?";
        j.update(sql, new Object[]{userTimerCO.getFood(), userTimerCO.getGold(),userTimerCO.getId()});
    }


    public UserTimerCO get(long uid) {
        final String sql = "select food,gold from user_timer where id=?";
        return j.queryForObject(sql, new Object[]{uid}, new BeanPropertyRowMapper<UserTimerCO>(UserTimerCO.class));
    }

}
