配置文件文档
=============
accessory.xml armor.xml weapon.xml
-------------
+ 这三个配置文件都是为了（武器/饰品/盔甲）在强化和升级之后的参数，其中strength为强化的等级，purple1为升级1级，purple16为升级16级
+ 此配置可以看做一个二维的值，为强化等级和升级等级的二维，value为参数，（攻击，防御或者血量）

--------

battle.xml
--------
+ 为pve的战斗相关配置
+ pve分为三个层级，章节--城堡---将领
    + id：为battle的id，唯一
    + name：将领名称
    + chapter: 章节
    + levelLimit：等级限制，只有在大于等于这个等级之后才可以进行战斗
    + battle：章节编号
    + castle: 城堡名称
    + boss：主怪的monster编号（关联monster的配置文件，为pve中的将领的id列表）
    + chapterBackground：章节背景
    + background：战斗背景
    + UserLevel：不用关注
    + petLevel：不用关注
    + petSkillLevel：不用关注
    + pos0-pos8：为每个pos的怪物的id
    + honor：战斗成功奖励的荣誉值
    + gold：战斗成功奖励的金币
    + xp：战斗成功用户经验的奖励
    + heroXp：战斗成功的英雄经验奖励（用于英雄的升级）

-------

equip.xml
-------------
+ 为装备的配置文件
+ 每一种类型的装备（武器、饰品或者盔甲）一共有16升级等级，xml一共有16个片段，每个片段代表一个等级
+ 每个等级包含着该等级的武器相关信息，盔甲的相关信息，饰品的相关信息
    + level：等级
    + weaponMaterial1：升级该武器需要的材料1的数量（材料1，2为升级该武器需要的两种材料，分别命名为1和2）
    + weaponMaterialType1：升级该武器需要的材料1的id
    + weaponMaterial2：同上，就是2
    + weaponMaterialType2：同上，就是2
    + weaponGold：不用考虑
    + weaponSuccess：升级武器的成功率
    + armorMaterial1：同上
    + armorMaterialType1：同上
    + armorMaterial2：    同上
    + armorMaterialType2： 同上
    + armorGold：          同上
    + armorSuccess：        同上
    + accessoryMaterial1:   同上
    + accessoryMaterialType1: 同上
    + accessoryMaterial2:    同上
    + accessoryMaterialType2:同上
    + accessoryGold:      同上
    + accessorySuccess:   同上
    + weaponUrl:武器的url地址
    + weaponName：武器名称
    + weaponDesc：武器简介
    + magicUrl：不用管
    + magicName：不用管
    + magicDesc：不用管
    + armorUrl：同上
    + armorName：同上
    + armorDesc:同上
    + accessoryUrl：同上
    + accessoryName：同上
    + accessoryDesc：同上
    + sell：卖的金币数量
    + buyable：不用管
    + equipAddSuccByFail：每次失败之后增加的成功率百分比
    + attributeLimit：不用管

-------

global.xml
------
+ 全局配置文件

------

level.xml
-----
+ 用户等级的配置
    + level：等级
    + xp：升级到下一级需要的经验
    + roleNum：可以上阵的将领数量
    + goldMax：城堡中的金币最大可以积攒到多少
    + goldPerHour：城堡中每个小时积攒的金币数量
    + foodMax：农田中的粮食最大可以积攒到都少
    + foodPerHour：农田中每个小时积攒的粮食的数量
    + trainingXp：不用管
    + mineOutputFactor： 不用管
    + personOutPut：不用管
    + prisonOutPut：不用管
    + equipScore：不用管
    + goldBuy：可以在商店中购买的金币的数量
    + offlineXp：不用管
    + serverWarSupport：不用管
    + battleWorldSupport：不用管

material.xml
------
+ 为材料类的配置文件
    + id：id
    + name：材料名称
    + color：材料的品质  （暂时不用管）
    + sell：如果卖的话换成多少金币
    + buyable：是否可以购买（1为可以）
    + canUse：不用管
    + useType：不用管
    + useEffect：不用管
    + useNeed：不用管
    + endTime：不用管
    + useInterval:不用管
    + url：url
    + desc：材料的描述

