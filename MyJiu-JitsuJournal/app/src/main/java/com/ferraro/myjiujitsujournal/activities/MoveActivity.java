package com.ferraro.myjiujitsujournal.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.Constants.Position;
import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.Move;
import com.ferraro.myjiujitsujournal.mjjj.R;

import java.util.ArrayList;
import java.util.List;


public class MoveActivity extends ActionBarActivity {

    private Engine engine;
    private ListView list;
    private Move thisMove;
    private List<String> list_file;
    private ArrayAdapter<String> arrayAdapter;
    private Journal journal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = Engine.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        String moveIdToOpen = getIntent().getStringExtra(MyConstants.MOVE_TO_OPEN_ID);
        String moveNameToOpen = getIntent().getStringExtra(MyConstants.MOVE_TO_OPEN_NAME);
        String movePositionToOpen = getIntent().getStringExtra(MyConstants.MOVE_TO_OPEN_POSITION);

        if(moveIdToOpen.equals(engine.getDefaultJournal().getId())){
            journal = engine.getDefaultJournal();
        }else if(moveIdToOpen.equals(engine.getMyJournal().getId())) {
            journal = engine.getMyJournal();
        }

        Move tempMove = new Move(moveNameToOpen, Position.get(movePositionToOpen));
        for(Move m : journal.getMoves()) {
            if(m.equals(tempMove)) {
                thisMove = m;
            }
        }

        TextView moveNameText =(TextView)findViewById(R.id.move_name_title);
        moveNameText.setText(moveNameToOpen);

        list_file = new ArrayList<String>();
        list = (ListView)findViewById(R.id.moveListView);

        int counter = 1;
        for(String step: journal.getMoves().get(0).getSteps()) {
            list_file.add("Step " + counter + ": "+ step);
            counter++;
        }

        //Create an adapter for the listView and add the ArrayList to the adapter.
        arrayAdapter = new ArrayAdapter<String>(MoveActivity.this, android.R.layout.simple_list_item_1, list_file);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,long arg3)
            {
                //what to do on click
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_move, menu);
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
}
