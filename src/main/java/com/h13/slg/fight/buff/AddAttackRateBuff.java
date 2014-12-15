package com.h13.slg.fight.buff;

import com.h13.slg.fight.FContext;
import com.h13.slg.fight.FUser;
import com.h13.slg.fight.FUserRole;
import com.h13.slg.fight.FightConstants;

/**
 * add attack rate for global
 * <p/>
 * For Only one FUserRole
 */
public class AddAttackRateBuff extends BaseBuff {


    @Override
    protected void doTrigger(FContext fContext) {

        int rate = new Integer(args[0]);

        FUserRole currentFUserRole = getCurrentFUserRole(fContext);


        int addedAttack = currentFUserRole.getBaseAttack() * rate / 100;
        currentFUserRole.setAddedAttack(currentFUserRole.getAddedAttack() + addedAttack);

    }

    @Override
    protected String getCurrentTriggerTime() {
        return BuffTriggerTime.BEFORE_FIGHT;
    }


}
