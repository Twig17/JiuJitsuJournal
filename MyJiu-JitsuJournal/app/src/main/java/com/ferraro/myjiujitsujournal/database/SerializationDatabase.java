package com.ferraro.myjiujitsujournal.database;

import android.app.Activity;
import android.content.Context;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.mjjj.Gym;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.MyApp;
import com.ferraro.myjiujitsujournal.mjjj.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by Nick on 3/5/2017.
 */
public class SerializationDatabase extends Activity implements IDatabase{
    @Override
    public User getUser() {
        try{
            Context context = MyApp.getContext();
            FileInputStream fis = context.openFileInput(MyConstants.user_file);
            ObjectInputStream is = new ObjectInputStream(fis);
            User user = (User) is.readObject();
            return user;
        }
        catch(Exception e) {
            //log error here
            e.toString();
        }
        return null;
    }

    @Override
    public void saveUser(User user) {
        try {
            Context context = MyApp.getContext();
            FileOutputStream fos = context.openFileOutput(MyConstants.user_file, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(user);
            os.close();
            fos.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Journal getMyJournal() {
        try{
            Context context = MyApp.getContext();
            FileInputStream fis = context.openFileInput(MyConstants.journal_file);
            ObjectInputStream is = new ObjectInputStream(fis);
            Journal journal = (Journal) is.readObject();
            return journal;
        }
        catch(Exception e) {
            //log error here
            e.toString();
        }
        return null;
    }

    @Override
    public Journal getDefaultJournal() {
        return CreateDefaultJournal.createJournal();
    }

    @Override
    public void saveMyJournal(Journal journal) {
        try {
            Context context = MyApp.getContext();
            FileOutputStream fos = context.openFileOutput(MyConstants.journal_file, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(journal);
            os.close();
            fos.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Gym getGymById(String id){
        return null;
    }

    @Override
    public Gym getGymByName(String name){
        for(Gym gym: CreateDefaultGyms.createGyms()){
            if(gym.getName().equals(name)){
                return gym;
            }
        }
        return null;
    }

    @Override
    public List<Gym> getAllGym() {
        //Only create gyms once so id's are not changing
        try{
            Context context = MyApp.getContext();
            FileInputStream fis = context.openFileInput(MyConstants.gyms_file);
            ObjectInputStream is = new ObjectInputStream(fis);
            List<Gym> allGyms = (List<Gym>) is.readObject();
            return allGyms;
        }
        catch(Exception e) {
            //log error here
            e.toString();
        }
        List<Gym> allGyms = CreateDefaultGyms.createGyms();
        saveGyms(allGyms);
        return allGyms;
    }

    public void saveGyms(List<Gym> allGyms) {
        try {
            Context context = MyApp.getContext();
            FileOutputStream fos = context.openFileOutput(MyConstants.gyms_file, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(allGyms);
            os.close();
            fos.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
