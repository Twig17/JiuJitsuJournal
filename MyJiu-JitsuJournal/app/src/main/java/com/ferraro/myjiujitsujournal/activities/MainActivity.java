package com.ferraro.myjiujitsujournal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.R;
import com.ferraro.myjiujitsujournal.mjjj.User;


public class MainActivity extends ActionBarActivity {

    Engine engine;
    User thisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = engine.getInstance();
        thisUser = engine.getThisUser();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeMessageText =(TextView)findViewById(R.id.welcome_message);
        String welcomeMessage = welcomeMessageText.getText().toString();
        welcomeMessage = thisUser.getUsername() + "'" + welcomeMessage;
        welcomeMessageText.setText(welcomeMessage);

        Button myJournalButton =(Button)findViewById(R.id.myJournalButton);
        myJournalButton.setText(thisUser.getUsername() + "'s" + myJournalButton.getText());

        Button myGymButton =(Button)findViewById(R.id.myGymButton);
        myGymButton.setText(thisUser.getUsername() + "'s"  +myGymButton.getText());
    }

    public void showDefaultJournal(View view) {
        Intent journalIntent = new Intent(this, JournalActivity.class);
        journalIntent.putExtra(MyConstants.JOURNAL_TO_OPEN, engine.getDefaultJournal().getId());
        startActivity(journalIntent);
    }

    public void showMyJournal(View view) {
        Intent journalIntent = new Intent(this, JournalActivity.class);
        Journal j = engine.getMyJournal();
        String s = j.getId();
        journalIntent.putExtra(MyConstants.JOURNAL_TO_OPEN, engine.getMyJournal().getId());
        startActivity(journalIntent);
    }

    public void showGym(View view){
        //TODO put if statement to show either your gym or a list of gyms
        Intent gymIntent = new Intent(this, GymActivity.class);
        startActivity(gymIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
