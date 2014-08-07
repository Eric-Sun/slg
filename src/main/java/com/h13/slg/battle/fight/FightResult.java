package com.h13.slg.battle.fight;

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
        if (rounds.size() <= round - 1) {
            list = new LinkedList<Object>();
            rounds.add(list);
        } else {
            list = rounds.get(round - 1);
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
