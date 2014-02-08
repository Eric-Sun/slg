package com.h13.slg.user.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-1-24
 * Time: 下午7:15
 * To change this template use File | Settings | File Templates.
 */
public class UserVO {

    private long id;
    private String name;
    private UserStatusVO status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatusVO getStatus() {
        return status;
    }

    public void setStatus(UserStatusVO status) {
        this.status = status;
    }
}
