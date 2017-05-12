package com.ferraro.myjiujitsujournal.mjjj;

import java.io.Serializable;

/**
 * Created by Nick on 2/26/2017.
 */
public class User implements Serializable{

    private String username;
    private Gym myGym;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gym getMyGym() {
        return myGym;
    }

    public void setMyGym(Gym myGym) {
        this.myGym = myGym;
    }
}
