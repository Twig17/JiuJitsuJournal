package com.ferraro.myjiujitsujournal.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.mjjj.Engine;
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
    }

    public void showDefaultJournal(View view) {
        Intent journalIntent = new Intent(this, JournalActivity.class);
        startActivity(journalIntent);
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
