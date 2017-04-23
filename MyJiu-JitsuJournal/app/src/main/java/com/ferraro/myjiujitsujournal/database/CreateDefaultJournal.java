package com.ferraro.myjiujitsujournal.database;

import com.ferraro.myjiujitsujournal.Constants.Gi;
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

        Move move1 = new Move("Takedown");
        move1.addStep("Get grips on elbow and the opposite side collar");
        move1.addStep("Pull elbow towards you, at the same time push their collar away");
        move1.addStep("Step with foot on side you are gripping the elbow past your opponent");
        move1.addStep("Start to lean forward over your opponents arm");
        move1.addStep("Swing your back leg forward all the way past your opponent");
        move1.addStep("Bring your leg back and connect your calf with theirs, " +
                "push down using your grip by their collar to drop them to the ground");
        move1.setTopBottom(TopBottom.STANDING);
        move1.setPosition(Position.STANDING);
        move1.setGiNoGi(Gi.GI);
        defaultJournal.addMove(move1);

        Move move2 = new Move("A Choke");
        move2.addStep("Open your opponents gui on one side");
        move2.addStep("Use your hand on the opposite side of the side you opened to get a " +
                "cross grip on their collar, get the grip as far up as you can. Behind their neck is the best");
        move2.addStep("Can finish by getting a cross grip on their shoulder");
        move2.addStep("Another finish is get a cross grip on their gui collar");
        move2.setPosition(Position.CLOSED);
        move2.setTopBottom(TopBottom.BOTTOM);
        move2.setGiNoGi(Gi.GI);
        defaultJournal.addMove(move2);

        Move move3 = new Move("Guard pass");
        move3.addStep("Grab both pant legs just below their hips");
        move3.addStep("Push down on their legs");
        move3.addStep("Move one of your knees by your opponents butt");
        move3.addStep("Move your other leg back as far as you can straight behind you");
        move3.addStep("If their guard isn't open push down on their leg on the same side as the " +
                "leg you have behind you");
        move3.addStep("Bring your back leg forward onto their leg and pass their guard");
        move3.setTopBottom(TopBottom.TOP);
        move3.setPosition(Position.CLOSED);
        move3.setGiNoGi(Gi.GI);
        defaultJournal.addMove(move3);

        Move move4 = new Move("Ezekiel Choke");
        move4.addStep("Stack your opponent");
        move4.addStep("Make sure you are far enough up, you want your chest to be over their head");
        move4.addStep("Have your legs extended so your butt is in the air");
        move4.addStep("Wrap one arm behind their head");
        move4.addStep("With your arm behind their head, grab the inside of your other sleeve with four fingers");
        move4.addStep("Take your arm NOT behind their head and make a fist with it");
        move4.addStep("Move your fist over to their neck and start to apply pressure");
        move4.addStep("You can start to extend your arm straight to apply more pressure to get the tap");
        move4.setPosition(Position.CLOSED);
        move4.setTopBottom(TopBottom.TOP);
        move4.setGiNoGi(Gi.GI);
        defaultJournal.addMove(move4);

        Move move6 = new Move("Triangle");
        move6.addStep("Get a hold of one of your opponents arms and pull it across your body, " +
                "Your grips should be the arm that is same side as their arm you grab should old their wrist," +
                "And your other arm should be grabbing over their arm and holding their elbow.");
        move6.addStep("You want to pull their arm far enough so their elbow is past your hips, aim to get their hand on your shoulder");
        move6.addStep("Open your guard and put your leg on your opponents hip on the side of the arm your are holding," +
                "push your knee on their shoulder to prevent them from pulling back their arm");
        move6.addStep("Start to push off of your opponents hip to rotate yourself. " +
                "You want to end up looking straight into your opponents ear, with your body perpendicular to theirs");
        move6.addStep("At the same time as you rotate you want bring your other leg that is still behind your opponents still," +
                "and bring that leg up onto your opponents shoulder. You want the back of your knee to " +
                "be against your opponents neck");
        move6.addStep("Bring your leg that started on their hip up and lock it with your other leg, " +
                "you want the back of that legs knee to be wrapped around your ankle that is behind your opponents head");
        move6.addStep("Squeeze your legs together to help finish");
        move6.setPosition(Position.CLOSED);
        move6.setTopBottom(TopBottom.BOTTOM);
        move6.setGiNoGi(Gi.GI);
        defaultJournal.addMove(move6);

        Move move7 = new Move("Triangle Setup 2");
        move7.addStep("Grab behind your opponents head with one hand");
        move7.addStep("Use your other hand to grab one of the wrists/sleeves of your opponent");
        move7.addStep("Push the arm you gripped into your opponents chest");
        move7.addStep("One the same side that your gripped their arm bring your leg up to behind their head");
        move7.addStep("You want the back of your knee to be against their neck");
        move7.addStep("Bring up your other leg and lock the triangle");
        move7.addStep("You want the ankle of the leg behind their head to be in the back of your other knee");
        move7.setTopBottom(TopBottom.BOTTOM);
        move7.setPosition(Position.CLOSED);
        move7.setGiNoGi(Gi.GI);
        defaultJournal.addMove(move7);

        Move move8 = new Move("Scissor Sweep");
        move8.addStep("Get a grip on one of your opponents arms");
        move8.addStep("One the same side as the arm you are now gripping, get your second grip on the collar");
        move8.addStep("Pull them forward to try and get their center of mass over your body");
        move8.addStep("At the same time as you pull them forward you want to take your leg on the side your are NOT" +
                "gripping them and bring it in front of them");
        move8.addStep("You want your leg bent with your shin pressed against their chest");
        move8.addStep("Then move your leg on the side that you are gripping them so the back of your knee" +
                "is pressed against their knee");
        move8.addStep("If you are facing a larger opponent you can also put the bottom of you foot against their knee to get more power");
        move8.addStep("Now move your legs in a scissor motion, pushing their legs out from under them, " +
                "while your other legs moves their torso next to you onto the ground");
        move8.addStep("Follow through with the leg that was on their chest move on the ground next to them and assume the full mount position");
        move8.setPosition(Position.CLOSED);
        move8.setTopBottom(TopBottom.BOTTOM);
        move8.setGiNoGi(Gi.GI);
        defaultJournal.addMove(move8);

        Move move9 = new Move("Americana");
        move9.addStep("Grab one of your opponents wrists with the same as arm as your, grab their right with your right or their left with your left");
        move9.addStep("Bring your other arm under your opponents arm that you are holding, and grab your own wrist with it");
        move9.addStep("Pull your arms down towards your hip");
        move9.addStep("You want to get their elbow against their side ribs");
        move9.addStep("Once there start to pull straight up to rotate their shoulder till they tap");
        move9.setPosition(Position.SIDE_CONTROL);
        move9.setTopBottom(TopBottom.TOP);
        move9.setGiNoGi(Gi.GI);
        defaultJournal.addMove(move9);

        return defaultJournal;
    }

}
