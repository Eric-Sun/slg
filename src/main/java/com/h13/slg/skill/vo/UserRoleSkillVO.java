package com.h13.slg.skill.vo;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-7-24
 * Time: 下午5:34
 * To change this template use File | Settings | File Templates.
 */
public class UserRoleSkillVO {
    private int id;
    private int rid;
    private int uid;
    private String type;
    private int rsid;
    private int level = 1;
    private int delete ;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRsid() {
        return rsid;
    }

    public void setRsid(int rsid) {
        this.rsid = rsid;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
