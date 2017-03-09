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

            Move move3 = new Move("Guard pass");
            move3.addStep("First Step");
            move3.addStep("another step");
            move3.addStep("last thing to do");
            move3.setTopBottom(TopBottom.TOP);
            move3.setPosition(Position.CLOSED);
            defaultJournal.addMove(move3);

            Move move4 = new Move("Second Choke");
            move4.addStep("First Step");
            move4.addStep("another step");
            move4.addStep("last thing to do");
            move4.setPosition(Position.CLOSED);
            move4.setTopBottom(TopBottom.TOP);
            defaultJournal.addMove(move4);

            Move move5 = new Move("Takedown2");
            move5.addStep("First Step");
            move5.addStep("another step");
            move5.addStep("last thing to do");
            move5.setTopBottom(TopBottom.STANDING);
            move5.setPosition(Position.STANDING);
            defaultJournal.addMove(move5);

            Move move6 = new Move("Triangle");
            move6.addStep("do this first");
            move6.addStep("then do this");
            move6.addStep("win");
            move6.setPosition(Position.CLOSED);
            move6.setTopBottom(TopBottom.BOTTOM);
            defaultJournal.addMove(move6);

            Move move7 = new Move("Triangle");
            move7.addStep("First Step");
            move7.addStep("another step");
            move7.addStep("last thing to do");
            move7.setTopBottom(TopBottom.TOP);
            move7.setPosition(Position.HALF_GUARD);
            defaultJournal.addMove(move7);

            Move move8 = new Move("Sweep");
            move8.addStep("First Step");
            move8.addStep("another step");
            move8.addStep("last thing to do");
            move8.setPosition(Position.CLOSED);
            move8.setTopBottom(TopBottom.BOTTOM);
            defaultJournal.addMove(move8);

            Move move9 = new Move("Americana");
            move9.addStep("First Step");
            move9.addStep("another step");
            move9.addStep("last thing to do");
            move9.setPosition(Position.HALF_MOUNT);
            move9.setTopBottom(TopBottom.TOP);
            defaultJournal.addMove(move9);
        return defaultJournal;
    }

}
