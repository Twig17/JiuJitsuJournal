package com.ferraro.myjiujitsujournal.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 3/6/2017.
 */
public enum TopBottom {
    TOP("Top"),
    BOTTOM("Bottom"),
    STANDING("Standing");

    private String name;

    private static final Map<String, TopBottom> lookup = new HashMap<String, TopBottom>();

    static {
        for (TopBottom p : TopBottom.values()) {
            lookup.put(p.getValue(), p);
        }
    }

    TopBottom(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }

    public static TopBottom get(String name) {
        return lookup.get(name);
    }
}
