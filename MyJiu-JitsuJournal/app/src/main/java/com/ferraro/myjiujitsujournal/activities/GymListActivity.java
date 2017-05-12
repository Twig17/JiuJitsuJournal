package com.ferraro.myjiujitsujournal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.helpers.CustomGymAdapter;
import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.Gym;
import com.ferraro.myjiujitsujournal.mjjj.R;

import java.util.ArrayList;
import java.util.List;

public class GymListActivity extends ActionBarActivity {

    private Engine engine;
    private ListView list;
    private CustomGymAdapter adapter;
    private List<String> gymIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = Engine.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_list);

        list = (ListView)findViewById(R.id.allGymsListView);
        displayGyms();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gym_list, menu);
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

    private void displayGyms() {
        List<Gym> allGyms = engine.getDatabase().getAllGym();
        //loop through all moves in the journal and show a list of them
        gymIds = new ArrayList<String>();
        List<String> gymNames = new ArrayList<String>();
        List<Integer> gymLogoIds = new ArrayList<Integer>();
        List<String> gymLocations = new ArrayList<String>();
        for(Gym gym: allGyms) {
            gymIds.add(gym.getId());
            gymNames.add(gym.getName());
            gymLogoIds.add(gym.getIconImageName());
            gymLocations.add(gym.getLocation());
        }
        //Create an adapter for the listView and add the ArrayList to the adapter.
        adapter=new CustomGymAdapter(this, gymNames.toArray(new String[gymNames.size()]),
                gymLogoIds.toArray(new Integer[gymLogoIds.size()]), gymLocations.toArray(new String[gymLocations.size()]));
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position,long id)
            {
                startGymActivity(position);
            }
        });
        EditText inputSearchGyms = (EditText) findViewById(R.id.gymSearchBar);

        //Add search bar to the listView
        inputSearchGyms.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                GymListActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }

    private void startGymActivity(int position) {
        Intent gymIntent = new Intent(this, GymActivity.class);
        gymIntent.putExtra(MyConstants.GYM_ID_TO_DISPLAY, gymIds.get(position));
        startActivity(gymIntent);
    }

}
