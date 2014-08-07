package com.h13.slg.battle.fight;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * buff类的技能
 */
public class FightBuffLog {

    private String type;
    private String skillType;
    private int pos;
    private String owner;
    private List<Object> status = Lists.newLinkedList();

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public List<Object> getStatus() {
        return status;
    }

    public void setStatus(List<Object> status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
