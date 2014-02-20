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


    public void add(long id, int curFood) {
        final String sql = "insert into farm(id,cur_food) values (?,?)";
        j.update(sql, new Object[]{id, curFood});
    }

    public void update(long id, int curFood) {
        final String sql = "update farm set cur_food=? where id=?";
        j.update(sql, new Object[]{curFood, id});
    }

    public FarmCO get(long id) {
        final String sql = "select id,cur_food from farm where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<FarmCO>(FarmCO.class));
    }

}
