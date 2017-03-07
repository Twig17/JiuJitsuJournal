package com.ferraro.myjiujitsujournal.mjjj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 3/5/2017.
 */
public class Moves implements Serializable {
    private String name;
    private List<String> steps;

    public Moves(String name) {
        this.name = name;
        steps = new ArrayList<String>();
    }

    public void addStep(String newStep){
        steps.add(newStep);
    }

    public void removeStep(String step) {
        steps.remove(step);
    }

    public void updateStep(String oldStep, String newStep){
        int location  = steps.indexOf(oldStep);
        steps.set(location, newStep);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moves moves = (Moves) o;

        return !(name != null ? !name.equals(moves.name) : moves.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
