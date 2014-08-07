战斗的返回日志
=======
+ 日志分为如下的类型：
    + 攻击、防守日志
        + 三围技能
            + 出发技能/技能结束
    + 技能相关日志
        + 增益类技能：
        + 其他技能类型：

+ 攻击、防守日志
    + type=ad
    + attackPos：攻击方的pos
    + defencePos[]:为防守方的列表（有可能是群攻）
    + attackStatus：为攻击方的数组，数组中包含着攻击方的一些相关信息
        + [攻击之后生命值，攻击之后怒气]
    + defenceStatus{}：为一个hash，key为pos的位置，value为一个数组，表示防守方的一些信息
        + [防守方之后的生命，造成的伤害，防守方的防守之后的怒气]

+ 三围技能触发日志
    + type=triggerSkill
    + pos=触发技能的位置
    + name=技能名称
    + owner=attack/defence 哪一方触发的技能
    + skillType=sanwei
    + target：ziji为自己，duifang为对方
    + status:[攻击增加百分比，防御增加百分比，生命值增加百分比]

+ 三围buff触发日志：
    + type=startBuff/stopBuff
    + skillType=sanwei
    + pos:收到技能的位置
    + owner:attack/defence 是哪一方出发的buf
    + status[]:[attack,defence,health]
        + attack:[攻击增加的百分比，攻击的增加值，攻击的最终值]，如果为负数，则为减少
        + defence:[防御增加的百分比，防御的增加值，防御的最终值]，如果为负数，则为减少
        + health：[生命的增加百分比，生命的增加值，生命的最终值]，如果为负数，则为debuff

