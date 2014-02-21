package com.h13.slg.user.dao;

import com.h13.slg.user.co.CastleCO;
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
public class CastleDAO {

    @Autowired
    JdbcTemplate j;


    public void add(long id, long castleTimer) {
        final String sql = "insert into castle(id,timer) values (?,?)";
        j.update(sql, new Object[]{id, castleTimer});
    }

    public void update(long id, long castleTimer) {
        final String sql = "update castle set timer=? where id=?";
        j.update(sql, new Object[]{castleTimer, id});
    }

    public CastleCO get(long id) {
        final String sql = "select id,timer from castle where id=?";
        return j.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<CastleCO>(CastleCO.class));
    }

}
