package com.ferraro.myjiujitsujournal.database;

import android.app.Activity;
import android.content.Context;

import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.R;
import com.ferraro.myjiujitsujournal.mjjj.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Nick on 3/5/2017.
 */
public class SerializationDatabase extends Activity implements IDatabase{
    @Override
    public User getUser() {
        try{
            Context context = getApplicationContext();
            FileInputStream fis = context.openFileInput(getString(R.string.user_file));
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
            Context context = getApplicationContext();
            FileOutputStream fos = context.openFileOutput(getString(R.string.user_file), Context.MODE_PRIVATE);
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
            Context context = getApplicationContext();
            FileInputStream fis = context.openFileInput(getString(R.string.journal_file));
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
        try{
            Context context = getApplicationContext();
            FileInputStream fis = context.openFileInput(getString(R.string.defaultJournal_file));
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
    public void saveMyJournal(Journal journal) {
        try {
            Context context = getApplicationContext();
            FileOutputStream fos = context.openFileOutput(getString(R.string.journal_file), Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(journal);
            os.close();
            fos.close();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
