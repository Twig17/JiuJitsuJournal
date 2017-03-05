package com.ferraro.myjiujitsujournal.mjjj;

import com.ferraro.myjiujitsujournal.database.IDatabase;

/**
 * Created by Nick on 2/25/2017.
 */
public class Engine {

    private static Engine instance = null;
    private IDatabase database = null;
    private User thisUser;

    public static Engine getInstance() {
        if(instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    private Engine(){

    }

    public User getThisUser() {
        return thisUser;
    }

    public void setThisUser(User thisUser) {
        this.thisUser = thisUser;
    }

    public IDatabase getDatabase() {
        return database;
    }
}
