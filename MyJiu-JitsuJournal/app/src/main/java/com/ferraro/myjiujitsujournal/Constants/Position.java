package com.ferraro.myjiujitsujournal.Constants;

/**
 * Created by Nick on 3/6/2017.
 */
public enum Position {
        CLOSED("Closed Guard"),
        HALF_MOUNT("Half Mount"),
        HALF_GAARD("Half Guard"),
        FULL_MOUNT("Full Mount"),
        FULL_GUARD("Full Guard"),
        STANDING("Standing");

        private String name;

        Position(String name) {
                this.name = name;
        }

        public String getValue() {
                return name;
        }
}
