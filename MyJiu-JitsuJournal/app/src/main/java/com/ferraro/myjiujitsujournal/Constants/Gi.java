package com.ferraro.myjiujitsujournal.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 3/6/2017.
 */
public enum Gi {
    GI("Gi"),
    NOGI("NoGi");

    private String name;

    private static final Map<String, Gi> lookup = new HashMap<String, Gi>();

    static {
        for (Gi p : Gi.values()) {
            lookup.put(p.getValue(), p);
        }
    }

    Gi(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }


    public static Gi get(String name) {
        return lookup.get(name);
    }
}
