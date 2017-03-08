package com.ferraro.myjiujitsujournal.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.Constants.Position;
import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.Move;
import com.ferraro.myjiujitsujournal.mjjj.R;

import java.util.ArrayList;
import java.util.List;

public class JournalActivity extends ActionBarActivity {

    private Engine engine;
    private ListView list;
    private Journal journal;
    private List<String> List_file;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = Engine.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        journal = engine.getDefaultJournal();
        TextView journalNameText =(TextView)findViewById(R.id.journal_name);
        journalNameText.setText(journal.getName());

        List_file = new ArrayList<String>();
        list = (ListView)findViewById(R.id.listView);
        Spinner mySpinner=(Spinner) findViewById(R.id.jouranlMovesSpinner);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CreateListView();
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        CreateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_journal, menu);
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

    private void CreateListView()
    {
        List_file.clear();
        Spinner mySpinner=(Spinner) findViewById(R.id.jouranlMovesSpinner);
        String text = mySpinner.getSelectedItem().toString();

        EditText searchBar = (EditText) findViewById(R.id.moveSearchBar);
        searchBar.setText("");

        if("Moves".equals(text)){
            displayMoves();
        }
        else if("Positions".equals(text)){
            displayPositions();
        }

        //Add search bar to the listView
        EditText inputSearch = (EditText) findViewById(R.id.moveSearchBar);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                JournalActivity.this.arrayAdapter.getFilter().filter(cs);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) { }
            @Override
            public void afterTextChanged(Editable arg0) { }
        });

    }

    private void displayMoves() {
        List<Move> journalMoves = journal.getMoves();
        //loop through all moves in the journal and show a list of them
        for(Move move: journalMoves) {
            List_file.add(move.getName());
        }
        //Create an adapter for the listView and add the ArrayList to the adapter.
        arrayAdapter = new ArrayAdapter<String>(JournalActivity.this, android.R.layout.simple_list_item_1,List_file);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //start new view to view the steps in the move
            }
        });
    }

    private void displayPositions() {
        List<Move> journalMoves = journal.getMoves();
        //loop through all moves in the journal and show a list of them
        for(Position position: Position.values()) {
            List_file.add(position.getValue());
        }
        //Create an adapter for the listView and add the ArrayList to the adapter.
        arrayAdapter = new ArrayAdapter<String>(JournalActivity.this, android.R.layout.simple_list_item_1,List_file);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
            {
                //start new view to view the steps in the move
            }
        });
    }
}
