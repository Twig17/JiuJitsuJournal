package com.ferraro.myjiujitsujournal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
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
    private List<Move> List_file;
    private List<String> list_file_position;
    private ArrayAdapter<String> arrayAdapterPosition;
    private ArrayAdapter<Move> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = Engine.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        String journalToOpen = getIntent().getStringExtra(MyConstants.JOURNAL_TO_OPEN);
        Button addMoveButton =(Button)findViewById(R.id.add_new_button);

        if(journalToOpen.equals(engine.getDefaultJournal().getId())){
            journal = engine.getDefaultJournal();
            addMoveButton.setVisibility(View.GONE);
        }else if(journalToOpen.equals(engine.getMyJournal().getId())) {
            journal = engine.getMyJournal();
            addMoveButton.setVisibility(View.VISIBLE);
        }

        TextView journalNameText =(TextView)findViewById(R.id.journal_name);
        journalNameText.setText(journal.getName());

        List_file = new ArrayList<Move>();
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
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
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

    public void CreateListView(View view) {
        CreateListView();
    }

    private void CreateListView()
    {
        //Reset values to original settings
        TextView journalNameText =(TextView)findViewById(R.id.journal_name);
        journalNameText.setText(journal.getName());
        List_file.clear();
        EditText searchBar = (EditText) findViewById(R.id.moveSearchBar);
        searchBar.setText("");
        Spinner mySpinner=(Spinner) findViewById(R.id.jouranlMovesSpinner);
        mySpinner.setVisibility(View.VISIBLE);
        Button showAllButton=(Button) findViewById(R.id.show_positions_button);
        showAllButton.setVisibility(View.GONE);
        String text = mySpinner.getSelectedItem().toString();

        if("Moves".equals(text)){
            displayMoves(null);
        }
        else if("Positions".equals(text)){
            displayPositions();
        }
    }

    private void displayMoves(Position positionFilter) {
        List_file.clear();
        List<Move> journalMoves = journal.getMoves();
        if(positionFilter != null) {
            TextView journalNameText = (TextView) findViewById(R.id.journal_name);
            journalNameText.setText(journal.getName() + "\n" + positionFilter.getValue());
        }
        //loop through all moves in the journal and show a list of them
        for(Move move: journalMoves) {
            if(positionFilter == null){
               List_file.add(move);
            }else if(move.getPosition() == positionFilter){
                List_file.add(move);
            }
        }
        //Create an adapter for the listView and add the ArrayList to the adapter.
        arrayAdapter = new ArrayAdapter<Move>(JournalActivity.this, android.R.layout.simple_list_item_1,List_file);
        list.setAdapter(arrayAdapter);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //start new view to view the steps in the move
                Move moveSelected = (Move) arg0.getItemAtPosition(arg2);
                startMoveActivity(moveSelected.getName(), moveSelected.getPosition().getValue());

            }
        });

        EditText inputSearchPosition = (EditText) findViewById(R.id.moveSearchBarPosition);
        EditText inputSearchMove = (EditText) findViewById(R.id.moveSearchBar);

        inputSearchMove.setVisibility(View.VISIBLE);
        inputSearchPosition.setVisibility(View.GONE);

        EditText inputSearch = (EditText) findViewById(R.id.moveSearchBar);
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                JournalActivity.this.arrayAdapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });
    }

    private void startMoveActivity(String move, String position){
        Intent journalIntent = new Intent(this, MoveActivity.class);

        journalIntent.putExtra(MyConstants.MOVE_TO_OPEN_ID, journal.getId());
        journalIntent.putExtra(MyConstants.MOVE_TO_OPEN_NAME, move);
        journalIntent.putExtra(MyConstants.MOVE_TO_OPEN_POSITION, position);
        startActivity(journalIntent);
    }

    private void displayPositions() {
        list_file_position = new ArrayList<String>();
        List<Move> journalMoves = journal.getMoves();
        //loop through all moves in the journal and show a list of them
        for(Position position: Position.values()) {
            boolean hasMoves = false;
            for(Move move: journalMoves) {
                if(move.getPosition() == position) {
                   hasMoves = true;
                }
            }
            if(hasMoves) {
                list_file_position.add(position.getValue());
            }
        }
        //Create an adapter for the listView and add the ArrayList to the adapter.
        arrayAdapterPosition = new ArrayAdapter<String>(JournalActivity.this, android.R.layout.simple_list_item_1,list_file_position);
        list.setAdapter(arrayAdapterPosition);
        list.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2,long arg3)
            {
                //start new view to view the steps in the move
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                Spinner mySpinner=(Spinner) findViewById(R.id.jouranlMovesSpinner);
                mySpinner.setVisibility(View.GONE);
                Button showAllButton=(Button) findViewById(R.id.show_positions_button);
                showAllButton.setVisibility(View.VISIBLE);
                String selectedFromList =(String) (list.getItemAtPosition(arg2));
                Position position = Position.get(selectedFromList);
                displayMoves(position);
            }
        });
        EditText inputSearchPosition = (EditText) findViewById(R.id.moveSearchBarPosition);
        EditText inputSearchMove = (EditText) findViewById(R.id.moveSearchBar);

        inputSearchMove.setVisibility(View.GONE);
        inputSearchPosition.setVisibility(View.VISIBLE);

        //Add search bar to the listView
        inputSearchPosition.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                JournalActivity.this.arrayAdapterPosition.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

    }

    public void addMove(View view) {
        Intent journalIntent = new Intent(this, EditMoveActivity.class);
        startActivity(journalIntent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        Button showAllButton=(Button) findViewById(R.id.show_positions_button);
        if(View.VISIBLE == showAllButton.getVisibility()) {
            CreateListView();
        } else {
            finish();
        }

    }

}
