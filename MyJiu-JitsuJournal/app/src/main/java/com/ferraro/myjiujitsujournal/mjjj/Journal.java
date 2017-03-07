package com.ferraro.myjiujitsujournal.mjjj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 3/5/2017.
 */
public class Journal implements Serializable {
    private String name;
    private List<Moves> moves;

    public Journal(){
        moves = new ArrayList<Moves>();
    }

    public void addMove(Moves move) {
        moves.add(move);
    }

    public void removeMove(Moves move) {
        moves.remove(move);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        return !(name != null ? !name.equals(journal.name) : journal.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
