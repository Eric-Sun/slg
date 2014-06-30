package com.h13.slg.battle.fight;

import java.util.*;

/**
 */
public class FightResult {
    public final static int WIN = 0;
    public final static int LOSE = 1;

    private int status;

    private List<List<FightLog>> rounds = new LinkedList<List<FightLog>>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addLog(int round, FightLog log) {
        List<FightLog> list = null;
        if (rounds.size() <= round - 1) {
            list = new LinkedList<FightLog>();
            rounds.add(list);
        } else {
            list = rounds.get(round - 1);
        }
        list.add(log);

    }

    public List<List<FightLog>> getRounds() {
        return rounds;
    }

    public void setRounds(List<List<FightLog>> rounds) {
        this.rounds = rounds;
    }
}
