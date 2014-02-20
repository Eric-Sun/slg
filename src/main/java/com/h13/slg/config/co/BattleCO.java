package com.h13.slg.config.co;

import org.apache.commons.digester3.annotations.rules.BeanPropertySetter;
import org.apache.commons.digester3.annotations.rules.ObjectCreate;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-2-20
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
@ObjectCreate(pattern = "battleList/battle")
public class BattleCO {
    @BeanPropertySetter(pattern = "battleList/battle/id")
    private int id;
    @BeanPropertySetter(pattern = "battleList/battle/name")
    private String name;
    @BeanPropertySetter(pattern = "battleList/battle/chapter")
    private String chapter;
    @BeanPropertySetter(pattern = "battleList/battle/levelLimit")
    private int levelLimit;
    @BeanPropertySetter(pattern = "battleList/battle/battle")
    private int battle;
    @BeanPropertySetter(pattern = "battleList/battle/castle")
    private String castle;
    @BeanPropertySetter(pattern = "battleList/battle/boss")
    private int boss;
    @BeanPropertySetter(pattern = "battleList/battle/chapterBackground")
    private int chapterBackground;
    @BeanPropertySetter(pattern = "battleList/battle/background")
    private int background;
    @BeanPropertySetter(pattern = "battleList/battle/userLevel")
    private int userLevel;
    @BeanPropertySetter(pattern = "battleList/battle/petLevel")
    private int petLevel;
    @BeanPropertySetter(pattern = "battleList/battle/petSkillLevels")
    private int petSkillLevels;
    @BeanPropertySetter(pattern = "battleList/battle/pos0")
    private int pos0;
    @BeanPropertySetter(pattern = "battleList/battle/pos1")
    private int pos1;
    @BeanPropertySetter(pattern = "battleList/battle/pos2")
    private int pos2;
    @BeanPropertySetter(pattern = "battleList/battle/pos3")
    private int pos3;
    @BeanPropertySetter(pattern = "battleList/battle/pos4")
    private int pos4;
    @BeanPropertySetter(pattern = "battleList/battle/pos5")
    private int pos5;
    @BeanPropertySetter(pattern = "battleList/battle/pos6")
    private int pos6;
    @BeanPropertySetter(pattern = "battleList/battle/pos7")
    private int pos7;
    @BeanPropertySetter(pattern = "battleList/battle/pos8")
    private int pos8;
    @BeanPropertySetter(pattern = "battleList/battle/honor")
    private int honor;
    @BeanPropertySetter(pattern = "battleList/battle/gold")
    private int gold;
    @BeanPropertySetter(pattern = "battleList/battle/xp")
    private int xp;
    @BeanPropertySetter(pattern = "battleList/battle/heroXp")
    private int heroXp;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getLevelLimit() {
        return levelLimit;
    }

    public void setLevelLimit(int levelLimit) {
        this.levelLimit = levelLimit;
    }

    public int getBattle() {
        return battle;
    }

    public void setBattle(int battle) {
        this.battle = battle;
    }

    public String getCastle() {
        return castle;
    }

    public void setCastle(String castle) {
        this.castle = castle;
    }

    public int getBoss() {
        return boss;
    }

    public void setBoss(int boss) {
        this.boss = boss;
    }

    public int getChapterBackground() {
        return chapterBackground;
    }

    public void setChapterBackground(int chapterBackground) {
        this.chapterBackground = chapterBackground;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public int getPetLevel() {
        return petLevel;
    }

    public void setPetLevel(int petLevel) {
        this.petLevel = petLevel;
    }

    public int getPetSkillLevels() {
        return petSkillLevels;
    }

    public void setPetSkillLevels(int petSkillLevels) {
        this.petSkillLevels = petSkillLevels;
    }

    public int getPos0() {
        return pos0;
    }

    public void setPos0(int pos0) {
        this.pos0 = pos0;
    }

    public int getPos1() {
        return pos1;
    }

    public void setPos1(int pos1) {
        this.pos1 = pos1;
    }

    public int getPos2() {
        return pos2;
    }

    public void setPos2(int pos2) {
        this.pos2 = pos2;
    }

    public int getPos3() {
        return pos3;
    }

    public void setPos3(int pos3) {
        this.pos3 = pos3;
    }

    public int getPos4() {
        return pos4;
    }

    public void setPos4(int pos4) {
        this.pos4 = pos4;
    }

    public int getPos5() {
        return pos5;
    }

    public void setPos5(int pos5) {
        this.pos5 = pos5;
    }

    public int getPos6() {
        return pos6;
    }

    public void setPos6(int pos6) {
        this.pos6 = pos6;
    }

    public int getPos7() {
        return pos7;
    }

    public void setPos7(int pos7) {
        this.pos7 = pos7;
    }

    public int getPos8() {
        return pos8;
    }

    public void setPos8(int pos8) {
        this.pos8 = pos8;
    }

    public int getHonor() {
        return honor;
    }

    public void setHonor(int honor) {
        this.honor = honor;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getHeroXp() {
        return heroXp;
    }

    public void setHeroXp(int heroXp) {
        this.heroXp = heroXp;
    }
}
