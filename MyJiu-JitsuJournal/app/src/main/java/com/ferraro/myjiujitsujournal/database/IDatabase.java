package com.ferraro.myjiujitsujournal.database;

import com.ferraro.myjiujitsujournal.mjjj.Gym;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.User;

import java.util.List;

/**
 * Created by Nick on 2/24/2017.
 */
public interface IDatabase {

    User getUser();
    void saveUser(User user);
    Journal getMyJournal();
    Journal getDefaultJournal();
    void saveMyJournal(Journal journal);
    List<Gym> getAllGym();
    Gym getGymById(String id);
    Gym getGymByName(String name);
}
