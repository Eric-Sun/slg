package com.h13.slg.task.dao;

import com.h13.slg.task.co.UserTaskCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-27
 * Time: 下午4:21
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserTaskDAO {

    @Autowired
    JdbcTemplate j;

    public void insert(long uid, int taskId, String progress) {
        String sql = "insert into user_task (id,task_id,progess,createtime) values (?,?,?,now())";
        j.update(sql, new Object[]{uid, taskId, progress});
    }

    public UserTaskCO get(long uid) {
        String sql = "select id,task_id,progress from user_task where id=?";
        return j.queryForObject(sql, new Object[]{uid}, new BeanPropertyRowMapper<UserTaskCO>(UserTaskCO.class));
    }

    public void updateProgess(long uid, int taskId, String progress) {
        String sql = "update user_task set progress=? where id=? and task_id=?";
        j.update(sql, new Object[]{progress, uid, taskId});
    }

    public void updateTask(long uid, int taskId, String progress) {
        String sql = "update user_task set progress=? ,task_id=? where id=? ";
        j.update(sql, new Object[]{progress, taskId, uid
        });
    }
}
