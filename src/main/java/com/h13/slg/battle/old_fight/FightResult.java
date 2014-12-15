package com.h13.slg.battle.old_fight;

import java.util.*;

/**
 */
public class FightResult {
    public final static int WIN = 0;
    public final static int LOSE = 1;

    private int status;

    private List<List<Object>> rounds = new LinkedList<List<Object>>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void addLog(int round, Object log) {
        List<Object> list = null;
        if (rounds.size() <= round) {
            list = new LinkedList<Object>();
            rounds.add(list);
        } else {
            list = rounds.get(round);
        }
        list.add(log);

    }

    public List<List<Object>> getRounds() {
        return rounds;
    }

    public void setRounds(List<List<Object>> rounds) {
        this.rounds = rounds;
    }
}
