package com.h13.slg.fight.buff;

import com.h13.slg.fight.FContext;
import com.h13.slg.fight.FUserRole;

/**
 * add defence rate for global
 * <p/>
 * For Only one FUserRole
 */
public class AddDefenceRateBuff extends BaseBuff {


    @Override
    protected void doTrigger(FContext fContext) {

        int rate = new Integer(args[0]);

        FUserRole currentFUserRole = getCurrentFUserRole(fContext);

        int addedDefence = currentFUserRole.getBaseDefence() * rate / 100;
        currentFUserRole.setAddedDefence(currentFUserRole.getAddedDefence() + addedDefence);

    }

    @Override
    protected String getCurrentTriggerTime() {
        return BuffTriggerTime.BEFORE_FIGHT;
    }


}
