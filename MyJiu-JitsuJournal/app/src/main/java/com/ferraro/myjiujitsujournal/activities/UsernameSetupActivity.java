package com.ferraro.myjiujitsujournal.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.activities.MainActivity;
import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.R;
import com.ferraro.myjiujitsujournal.mjjj.User;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UsernameSetupActivity extends ActionBarActivity {

    Engine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_setup);
        engine = Engine.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_username_setup, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void finishUserSetup(View view){
        TextView editTextLayout =(TextView)findViewById(R.id.fill_in_name_field);
        TextView warningTextLayout =(TextView)findViewById(R.id.name_setup_warning_text);
        Context context = getApplicationContext();

        //check value of name is longer than 1 char, otherwise display error
        if( editTextLayout.getText().length() > 1 ) {
            User user = new User(editTextLayout.getText().toString());
            engine.setThisUser(user);
            try {
                FileOutputStream fos = context.openFileOutput(getString(R.string.user_file), Context.MODE_PRIVATE);
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(user);
                os.close();
                fos.close();
            } catch(Exception e) {
                System.out.println(e);
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else{
            warningTextLayout.setVisibility(View.VISIBLE);
            warningTextLayout.setTextColor(Color.RED);
        }
    }
}
