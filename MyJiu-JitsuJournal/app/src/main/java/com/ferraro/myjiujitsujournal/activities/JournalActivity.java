package com.ferraro.myjiujitsujournal.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
        List<Move> journalMoves = journal.getMoves();
        for(Move move: journalMoves) {
            List_file.add(move.getName());
        }
        //Create an adapter for the listView and add the ArrayList to the adapter.
        list.setAdapter(new ArrayAdapter<String>(JournalActivity.this, android.R.layout.simple_list_item_1,List_file));
        list.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
            {
                //args2 is the listViews Selected index
            }
        });
    }
}
