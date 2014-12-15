package com.h13.slg.mail.dao;

import com.alibaba.fastjson.JSON;
import com.h13.slg.core.SlgConstants;
import com.h13.slg.data.DeleteConstants;
import com.h13.slg.mail.co.MailCO;
import com.h13.slg.mail.co.SystemMailAttachmentCO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.xml.datatype.DatatypeConstants;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-11-18
 * Time: 上午1:18
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MailDAO {


    @Autowired
    JdbcTemplate j;

    public int insert(final MailCO mailCO) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "insert into mail (uid,content,attachment,status,createtime,deleted) " +
                "values " +
                "(?,?,?,?,now(),?)";
        j.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, mailCO.getUid());
                pstmt.setString(2, mailCO.getContent());
                pstmt.setString(3, JSON.toJSONString(mailCO.getAttachment()));
                pstmt.setInt(4, mailCO.getStatus());
                pstmt.setInt(5, DeleteConstants.NORMAL);
                return pstmt;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }


    public MailCO get(int uid, int mailId) {
        String sql = "select id,uid,content,attachment,status " +
                "from mail where uid=? and id=? and deleted=?";
        return j.queryForObject(sql, new Object[]{uid, mailId, DeleteConstants.NORMAL},
                new RowMapper<MailCO>() {
                    @Override
                    public MailCO mapRow(ResultSet resultSet, int i) throws SQLException {
                        MailCO mailCO = new MailCO();
                        mailCO.setId(resultSet.getInt(1));
                        mailCO.setUid(resultSet.getInt(2));
                        mailCO.setContent(resultSet.getString(3));
                        mailCO.setAttachment(JSON.parseArray(resultSet.getString(4), SystemMailAttachmentCO.class));
                        mailCO.setStatus(resultSet.getInt(5));
                        return mailCO;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
    }


    public void delete(int uid, int id) {
        String sql = "update mail set deleted =? where id=? and uid=?";
        j.update(sql, new Object[]{DeleteConstants.DELETED, id, uid});
    }


    public void updateStatus(int uid, int mailId, int status) {
        String sql = "update mail set status=? where id=? and uid=?";
        j.update(sql, new Object[]{status, mailId, uid});
    }


    public List<MailCO> list(int uid, int maxCount) {
        String sql = "select id,uid,content,attachment,status " +
                "from mail where uid=? and deleted=? order by id desc limit ?";
        return j.query(sql, new Object[]{uid, DeleteConstants.NORMAL, maxCount},
                new RowMapper<MailCO>() {
                    @Override
                    public MailCO mapRow(ResultSet resultSet, int i) throws SQLException {
                        MailCO mailCO = new MailCO();
                        mailCO.setId(resultSet.getInt(1));
                        mailCO.setUid(resultSet.getInt(2));
                        mailCO.setContent(resultSet.getString(3));
                        mailCO.setAttachment(JSON.parseArray(resultSet.getString(4), SystemMailAttachmentCO.class));
                        mailCO.setStatus(resultSet.getInt(5));
                        return mailCO;  //To change body of implemented methods use File | Settings | File Templates.
                    }
                });
    }


}
