package com.ferraro.myjiujitsujournal.mjjj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nick on 3/5/2017.
 */
public class Journal implements Serializable {
    private String name;
    private String id;
    private List<Move> moves;
    private boolean isMyJournal;

    public Journal(){
        moves = new ArrayList<Move>();
        id = UUID.randomUUID().toString();
    }

    public Journal(String name){
        this.name = name;
        this.isMyJournal = false;
        moves = new ArrayList<Move>();
        id = UUID.randomUUID().toString();
    }

    public Journal(String name,boolean isMyJournal){
        this.name = name;
        this.isMyJournal = isMyJournal;
        moves = new ArrayList<Move>();
        id = UUID.randomUUID().toString();
    }

    public void addMove(Move move) {
        moves.add(move);
    }

    public void removeMove(Move move) {
        moves.remove(move);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public List<Move> getMoves() {
        return new ArrayList<>(moves);
    }

    public boolean isMyJournal() {
        return isMyJournal;
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
