package com.ferraro.myjiujitsujournal.database;

import com.ferraro.myjiujitsujournal.Constants.Position;
import com.ferraro.myjiujitsujournal.Constants.TopBottom;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.Move;

/**
 * Created by Nick on 3/7/2017.
 */
public class CreateDefaultJournal {

    public static Journal createJournal() {
        Journal defaultJournal = new Journal("Default Journal");
        int i=0;
        while(i < 10) {

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
            i++;
        }
        return defaultJournal;
    }

}
