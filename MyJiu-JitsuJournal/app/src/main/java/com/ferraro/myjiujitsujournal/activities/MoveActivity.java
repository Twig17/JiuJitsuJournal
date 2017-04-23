package com.ferraro.myjiujitsujournal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.Constants.Position;
import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.Move;
import com.ferraro.myjiujitsujournal.mjjj.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MoveActivity extends ActionBarActivity {

    private Engine engine;
    private ListView list;
    private Move thisMove;
    private List<String> list_file;
    private ArrayAdapter<String> arrayAdapter;
    private Journal journal;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = Engine.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        textToSpeech =new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        textToSpeech.setSpeechRate(new Float(.75));

        String moveIdToOpen = getIntent().getStringExtra(MyConstants.MOVE_TO_OPEN_ID);
        String moveNameToOpen = getIntent().getStringExtra(MyConstants.MOVE_TO_OPEN_NAME);
        String movePositionToOpen = getIntent().getStringExtra(MyConstants.MOVE_TO_OPEN_POSITION);
        Button editMoveButton =(Button)findViewById(R.id.edit_button);
        Button copyMoveButton =(Button)findViewById(R.id.copy_button);

        if(moveIdToOpen.equals(engine.getDefaultJournal().getId())){
            journal = engine.getDefaultJournal();
            editMoveButton.setVisibility(View.GONE);
            copyMoveButton.setVisibility(View.VISIBLE);
        }else if(moveIdToOpen.equals(engine.getMyJournal().getId())) {
            journal = engine.getMyJournal();
            editMoveButton.setVisibility(View.VISIBLE);
            copyMoveButton.setVisibility(View.GONE);
        }

        Move tempMove = new Move(moveNameToOpen, Position.get(movePositionToOpen));
        for(Move m : journal.getMoves()) {
            if(m.equals(tempMove)) {
                thisMove = m;
            }
        }

        TextView moveNameText =(TextView)findViewById(R.id.move_name_title);
        moveNameText.setText(moveNameToOpen);

        TextView moveDescriptionText =(TextView)findViewById(R.id.moveDescriptionText);
        if(!TextUtils.isEmpty(thisMove.getDescription())) {
            moveDescriptionText.setText(moveDescriptionText.getText() + thisMove.getDescription());
        } else {
            moveDescriptionText.setVisibility(View.INVISIBLE);
        }

        TextView moveGiText =(TextView)findViewById(R.id.moveGiText);
        moveGiText.setText(moveGiText.getText() + thisMove.getGiNoGi().getValue());

        TextView movePositionText =(TextView)findViewById(R.id.movePostionText);
        String positionDisplayString = "";
        if(!Position.STANDING.equals(thisMove.getPosition())) {
            positionDisplayString =  movePositionText.getText() + thisMove.getTopBottom().getValue()
                    + " of " + thisMove.getPosition().getValue();
        } else {
            positionDisplayString = movePositionText.getText() + thisMove.getPosition().getValue();
        }
        movePositionText.setText(positionDisplayString);


        list_file = new ArrayList<String>();
        list = (ListView)findViewById(R.id.moveListView);

        int counter = 1;
        int positionOfMove = journal.getMoves().indexOf(thisMove);
        for(String step : journal.getMoves().get(positionOfMove).getSteps()) {
            list_file.add(counter + ": "+ step);
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
                String toSpeak = arg0.getItemAtPosition(arg2).toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if(journal.isMyJournal()) {
            getMenuInflater().inflate(R.menu.menu_move, menu);
        }
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
        if (id == R.id.remove_move) {
            removeThisMove();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPause(){
        if(textToSpeech !=null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }

    public void playAllSteps(View view) {
        for(String step: thisMove.getSteps()) {
            String toSpeak = step.toString();
            Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
            textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_ADD, null);
            textToSpeech.playSilence(1000, TextToSpeech.QUEUE_ADD, null);
        }
    }

    public void copyMoveToMyJournal(View view){
        Journal myJournal = engine.getMyJournal();
        if(!myJournal.getMoves().contains(thisMove)) {
            myJournal.addMove(thisMove);
            engine.setMyJournal(myJournal);
        }
    }

    public void editMove(View view) {
        Intent journalIntent = new Intent(this, EditMoveActivity.class);

        journalIntent.putExtra(MyConstants.MOVE_TO_OPEN_ID, thisMove.getId());
        //journalIntent.putExtra(MyConstants.MOVE_TO_OPEN_NAME, move);
        //journalIntent.putExtra(MyConstants.MOVE_TO_OPEN_POSITION, position);
        startActivity(journalIntent);
    }

    private void removeThisMove() {
        journal.removeMove(thisMove);
        engine.setMyJournal(journal);
        finish();
    }
}
