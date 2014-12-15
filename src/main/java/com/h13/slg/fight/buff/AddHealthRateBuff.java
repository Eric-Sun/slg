package com.h13.slg.fight.buff;

import com.h13.slg.fight.FContext;
import com.h13.slg.fight.FUserRole;

/**
 * add health rate for global
 * <p/>
 * For Only one FUserRole
 */
public class AddHealthRateBuff extends BaseBuff {


    @Override
    protected void doTrigger(FContext fContext) {

        int rate = new Integer(args[0]);

        FUserRole currentFUserRole = getCurrentFUserRole(fContext);

        int addedHealth = currentFUserRole.getBaseHealth() * rate / 100;
        currentFUserRole.setAddedHealth(currentFUserRole.getAddedHealth() + addedHealth);

    }

    @Override
    protected String getCurrentTriggerTime() {
        return BuffTriggerTime.BEFORE_FIGHT;
    }


}
