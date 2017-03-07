package com.ferraro.myjiujitsujournal.database;

import android.app.Activity;
import android.content.Context;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.Constants.Position;
import com.ferraro.myjiujitsujournal.Constants.TopBottom;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.Move;
import com.ferraro.myjiujitsujournal.mjjj.MyApp;
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
        Journal defaultJournal = new Journal("Default Journal");

        Move move1 = new Move("Some Takedown");
        move1.addStep("First Step");
        move1.addStep("another step");
        move1.addStep("last thing to do");
        move1.setTopBottom(TopBottom.STANDING);
        move1.setPosition(Position.STANDING);
        defaultJournal.addMove(move1);

        Move move2 = new Move("A Choke");
        move2.addStep("do this first");
        move2.addStep("then do this");
        move2.addStep("win");
        move2.setPosition(Position.CLOSED);
        move2.setTopBottom(TopBottom.BOTTOM);
        defaultJournal.addMove(move2);

        return defaultJournal;
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
}
