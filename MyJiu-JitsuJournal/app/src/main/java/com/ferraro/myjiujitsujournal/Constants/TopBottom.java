package com.ferraro.myjiujitsujournal.Constants;

/**
 * Created by Nick on 3/6/2017.
 */
public enum TopBottom {
    TOP("Top"),
    BOTTOM("Bottom"),
    STANDING("Standing");

    private String name;

    TopBottom(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
