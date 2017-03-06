package com.ferraro.myjiujitsujournal.mjjj;

import com.ferraro.myjiujitsujournal.database.IDatabase;
import com.ferraro.myjiujitsujournal.database.SerializationDatabase;

/**
 * Created by Nick on 2/25/2017.
 */
public class Engine {

    private static Engine instance = null;
    private IDatabase database = null;
    private User thisUser;
    private Journal myJournal;
    private Journal defaultJournal;

    public static Engine getInstance() {
        if(instance == null) {
            instance = new Engine();
        }
        return instance;
    }

    private Engine(){
        database = new SerializationDatabase();
        thisUser = database.getUser();
        defaultJournal = database.getDefaultJournal();
        myJournal = database.getMyJournal();
    }

    public User getThisUser() {
        return thisUser;
    }

    public void setThisUser(User thisUser) {
        database.saveUser(thisUser);
        this.thisUser = thisUser;
    }

    public IDatabase getDatabase() {
        return database;
    }

    public Journal getMyJournal() {
        return myJournal;
    }

    public void setMyJournal(Journal myJournal) {
        database.saveMyJournal(myJournal);
        this.myJournal = myJournal;
    }

    public Journal getDefaultJournal() {
        return defaultJournal;
    }

}
