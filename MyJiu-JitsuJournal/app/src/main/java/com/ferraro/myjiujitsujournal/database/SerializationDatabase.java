package com.ferraro.myjiujitsujournal.database;

import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.User;

/**
 * Created by Nick on 3/5/2017.
 */
public class SerializationDatabase implements IDatabase{
    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public Journal getMyJournal() {
        return null;
    }

    @Override
    public Journal getDefaultJournal() {
        return null;
    }

    @Override
    public void saveMyJournal(Journal journal) {

    }
}
