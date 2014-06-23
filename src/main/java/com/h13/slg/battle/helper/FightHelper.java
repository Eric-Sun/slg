package com.h13.slg.battle.helper;

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

    /**
     * pve
     *
     * @param battleId 战斗对象id
     */
    public FightResult pve(long uid, long battleId) throws RequestErrorException {

        BattleCO battleCO = battleConfigFetcher.get(battleId + "");
        FightUnit defenceFightUnit = new FightUnit();
        for (int i = 0; i < 9; i++) {
            try {
                String monsterId = BeanUtils.getSimpleProperty(battleCO, "pos" + i);
                if (monsterId.equals("0"))
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
                fightPosition.setId(new Long(monsterId));

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
            long urid = new Long(teamData.get(i) + "");
            if (urid == 0)
                continue;
            UserRoleCO userRoleCO = userRoleHelper.getUserRole(uid, urid);
            FightPosition fightPosition = new FightPosition();
            fightPosition.setAttack(userRoleCO.getAttack());
            fightPosition.setDefence(userRoleCO.getDefence());
            fightPosition.setHealth(userRoleCO.getHealth());
            fightPosition.setId(urid);
            fightPosition.setType(FightPosition.ROLE);
            attackFightUnit.add(i, fightPosition);
        }
        FightResult fightResult = fightHandler.fight(attackFightUnit, defenceFightUnit);
        return fightResult;
    }


    public void pvp() {

    }


    private void doFight() {

    }
}
