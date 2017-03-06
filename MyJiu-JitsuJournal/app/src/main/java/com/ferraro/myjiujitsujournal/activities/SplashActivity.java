package com.ferraro.myjiujitsujournal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.User;

import java.util.concurrent.TimeUnit;

/**
 * Created by Nick on 2/24/2017.
 */
public class SplashActivity extends AppCompatActivity {

    Engine engine;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = engine.getInstance();
        super.onCreate(savedInstanceState);
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));

        Context context = getApplicationContext();
        boolean previouslyStarted = true;

        try {
            //try to load the user file. If not there then it is first time using the app
            user = engine.getDatabase().getUser();
            if(user == null) {
                throw new Exception("No user found");
            }
        }
        catch(Exception e) {
            previouslyStarted = false;
        }

        if(!previouslyStarted) {
            engine.setThisUser(user);
            Intent setupIntent = new Intent(this, UsernameSetupActivity.class);
            startActivity(setupIntent);
            finish();
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
