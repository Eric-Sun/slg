package com.h13.slg.battle.helper;

import com.google.common.base.Strings;
import com.h13.slg.battle.co.UserTeamCO;
import com.h13.slg.battle.fight.*;
import com.h13.slg.config.cache.MonsterCache;
import com.h13.slg.config.co.BattleCO;
import com.h13.slg.config.co.MonsterCO;
import com.h13.slg.config.fetcher.BattleConfigFetcher;
import com.h13.slg.config.fetcher.MonsterConfigFetcher;
import com.h13.slg.core.ErrorCodeConstants;
import com.h13.slg.core.RequestErrorException;
import com.h13.slg.role.co.UserRoleCO;
import com.h13.slg.role.helper.UserRoleHelper;
import com.h13.slg.user.hepler.UserStatusHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 14-3-25
 * Time: 下午5:42
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FightHelper {

    @Autowired
    TeamHelper teamHelper;
    @Autowired
    MonsterConfigFetcher monsterConfigFetcher;
    @Autowired
    BattleConfigFetcher battleConfigFetcher;
    @Autowired
    FightHandler fightHandler;
    @Autowired
    UserRoleHelper userRoleHelper;

    @Autowired
    UserStatusHelper userStatusHelper;

    /**
     * pve
     *
     * @param battleId 战斗对象id
     */
    public FightResult pve(int uid, long battleId) throws RequestErrorException {

        BattleCO battleCO = battleConfigFetcher.get(battleId + "");
        FightUnit defenceFightUnit = new FightUnit();
        for (int i = 0; i < 9; i++) {
            try {
                String monsterId = BeanUtils.getSimpleProperty(battleCO, "pos" + i);
                if (Strings.isNullOrEmpty(monsterId))
                    continue;
                MonsterCO monsterCO = monsterConfigFetcher.get(monsterId);
                int attack = monsterCO.getAttack();
                int defence = monsterCO.getDefence();
                int health = monsterCO.getHealth();

                FightPosition fightPosition = new FightPosition();
                fightPosition.setAttack(attack);
                fightPosition.setDefence(defence);
                fightPosition.setHealth(health);
                fightPosition.setType(FightPosition.MONSTER);
                fightPosition.setId(new Integer(monsterId));
                fightPosition.setName(monsterCO.getName());

                defenceFightUnit.add(i, fightPosition);
            } catch (Exception e) {
                throw new RequestErrorException(ErrorCodeConstants.COMMON_ERROR, "");
            }
        }


        // reward
        FightReward fightReward = new FightReward();
        fightReward.setGold(battleCO.getGold());
        fightReward.setHeroXp(battleCO.getHeroXp());
        fightReward.setHonor(battleCO.getHonor());
        fightReward.setXp(battleCO.getXp());

        // my team
        FightUnit attackFightUnit = new FightUnit();
        UserTeamCO userTeamCO = teamHelper.get(uid);
        List<Integer> teamData = userTeamCO.getData();
        for (int i = 0; i < teamData.size(); i++) {
            int urid = new Integer(teamData.get(i) + "");
            if (urid == 0)
                continue;
            UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urid);
            FightPosition fightPosition = new FightPosition();
            fightPosition.setAttack(userRoleCO.getAttack());
            fightPosition.setDefence(userRoleCO.getDefence());
            fightPosition.setHealth(userRoleCO.getHealth());
            fightPosition.setId(urid);
            fightPosition.setName(userRoleCO.getRoleName());
            fightPosition.setType(FightPosition.ROLE);
            attackFightUnit.add(i, fightPosition);
        }
        FightResult fightResult = fightHandler.fight(attackFightUnit, defenceFightUnit);


        // reward
        if (fightResult.getStatus() == FightResult.WIN) {
            userStatusHelper.addGold(uid, fightReward.getGold());
            userStatusHelper.addXp(uid, fightReward.getXp());
            userStatusHelper.addHonor(uid, fightReward.getHonor());


            // 所有的将领增加武将经验
            for (FightPosition fightPosition : attackFightUnit.getAllPos()) {
                if (fightPosition == null)
                    continue;
                int urid = fightPosition.getId();
                userRoleHelper.addXp(uid, urid, fightReward.getHeroXp());
            }

        }

        // 扣掉粮食
        userStatusHelper.subtractFood(uid, 100);
        return fightResult;
    }


    public void pvp() {

    }


    private void doFight() {

    }
}
