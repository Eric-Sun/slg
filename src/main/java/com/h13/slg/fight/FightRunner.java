package com.h13.slg.fight;

import com.h13.slg.core.SlgConstants;
import com.h13.slg.core.exception.RequestFatalException;
import com.h13.slg.core.exception.RequestUnexpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 战斗的运行着
 */
@Service
public class FightRunner {

    private FContext fContext;

    @Autowired
    private FCreator fCreator;


    /**
     * 战斗的方法，Runner对象对外暴露的方法就是此方法
     *
     * @param request
     * @return
     */
    public FResponse doFight(FRequest request) throws RequestUnexpectedException, RequestFatalException {
        FResponse response = new FResponse();

        // init context .
        initContext(request);

        // Will Fight
        // Convert Attributes to Buffers to All FUserRole
        convertAttributesToBuffs();
        return response;

    }

    /**
     * Convert all attributes to buffs
     *
     * Todo:Add Leader Attributes if any
     * Give each FUserRole global Attributes join UserRole Attributes. then convert to buffs
     *
     *
     */
    private void convertAttributesToBuffs() {

        //


    }


    private void initContext(FRequest request) throws RequestUnexpectedException, RequestFatalException {
        fContext = new FContext();

        // new F Users
        // init attack always User

        FUser defenceUser = null;
        FUser attackUser = fCreator.createByUser(request.getAttackUserId());
        if (request.getFightType().equals(FightConstants.FightType.PVE)) {
            defenceUser = fCreator.createByBattle(request.getDefenceId());
        } else {
            defenceUser = fCreator.createByUser(request.getDefenceId());
        }


        fContext.setAttack(attackUser);
        fContext.setDefence(defenceUser);
    }


}
