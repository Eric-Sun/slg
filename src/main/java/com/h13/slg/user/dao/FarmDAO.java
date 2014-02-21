package com.h13.slg.user.dao;

import com.h13.slg.user.co.FarmCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-17
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class FarmDAO {

    @Autowired
    JdbcTemplate j;


    public void add(long id, long farmTimer) {
        final String sql = "insert into farm(id,timer) values (?,?)";
        j.update(sql, new Object[]{id, farmTimer});
    }

    public void update(long id, long farmTimer) {
        final String sql = "update farm set timer=? where id=?";
        j.update(sql, new Object[]{farmTimer, id});
    }

    public FarmCO get(long id) {
        final String sql = "select id,timer from farm where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<FarmCO>(FarmCO.class));
    }

}
