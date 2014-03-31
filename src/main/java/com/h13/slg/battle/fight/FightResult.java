package com.h13.slg.battle.fight;

import java.util.*;

/**
 */
public class FightResult {
    public final static int WIN = 0;
    public final static int LOSE = 1;

    private int status;

    private Map<Integer, List<FightLog>> rounds = new HashMap<Integer, List<FightLog>>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addLog(int round, FightLog log) {
        List<FightLog> list = rounds.get(round);
        if (list == null)
            list = new LinkedList<FightLog>();
        list.add(log);
        rounds.put(round, list);
    }

    public Map<Integer, List<FightLog>> getRounds() {
        return rounds;
    }

    public void setRounds(Map<Integer, List<FightLog>> rounds) {
        this.rounds = rounds;
    }
}
