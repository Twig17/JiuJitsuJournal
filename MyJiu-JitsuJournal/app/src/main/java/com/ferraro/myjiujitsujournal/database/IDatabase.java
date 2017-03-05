package com.ferraro.myjiujitsujournal.database;

import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.User;

/**
 * Created by Nick on 2/24/2017.
 */
public interface IDatabase {

    public User getUser();
    public void saveUser(User user);
    public Journal getMyJournal();
    public Journal getDefaultJournal();
    public void saveMyJournal(Journal journal);
}
