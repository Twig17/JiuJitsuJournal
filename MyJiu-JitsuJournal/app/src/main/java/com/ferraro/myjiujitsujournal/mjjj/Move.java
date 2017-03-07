package com.ferraro.myjiujitsujournal.mjjj;

import com.ferraro.myjiujitsujournal.Constants.Position;
import com.ferraro.myjiujitsujournal.Constants.TopBottom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nick on 3/5/2017.
 */
public class Move implements Serializable {
    private String name;
    private String description;
    private Position position;
    private TopBottom topBottom;
    private List<String> steps;

    public Move(String name) {
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

    public List<String> getSteps() {
        return steps;
    }

    public TopBottom getTopBottom() {
        return topBottom;
    }

    public void setTopBottom(TopBottom topBottom) {
        this.topBottom = topBottom;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        return !(name != null ? !name.equals(move.name) : move.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
