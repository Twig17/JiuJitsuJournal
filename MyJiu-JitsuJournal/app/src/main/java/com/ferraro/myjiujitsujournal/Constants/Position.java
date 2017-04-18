package com.ferraro.myjiujitsujournal.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nick on 3/6/2017.
 */
public enum Position {
        CLOSED("Closed Guard"),
        FULL_MOUNT("Full Mount"),
        HALF_GUARD("Half Guard"),
        SIDE_CONTROL("Side Control"),
        OPEN_GUARD("Open Guard"),
        STANDING("Standing");

        private static final Map<String, Position> lookup = new HashMap<String, Position>();

        static {
                for (Position p : Position.values()) {
                        lookup.put(p.getValue(), p);
                }
        }

        private String name;

        Position(String name) {
                this.name = name;
        }

        public String getValue() {
                return name;
        }

        public static Position get(String name) {
                return lookup.get(name);
        }
}
