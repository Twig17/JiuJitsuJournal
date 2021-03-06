package com.ferraro.myjiujitsujournal.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.Constants.Gi;
import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.Constants.Position;
import com.ferraro.myjiujitsujournal.Constants.TopBottom;
import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.Journal;
import com.ferraro.myjiujitsujournal.mjjj.Move;
import com.ferraro.myjiujitsujournal.mjjj.R;

import java.util.ArrayList;
import java.util.List;

public class EditMoveActivity extends ActionBarActivity {
    private final Context context = this;
    private Engine engine;
    private Journal journal;
    private List<String> list_steps;
    private ArrayAdapter<String> arrayAdapterSteps;
    private ListView listView;
    private Move moveToEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = Engine.getInstance();
        journal = engine.getMyJournal();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_move);
        listView = (ListView)findViewById(R.id.moveListView);

        //Create dropdown lists
        populateSpinners();

        //Create an adapter for the listView and add the ArrayList to the adapter.
        list_steps = new ArrayList<String>();
        arrayAdapterSteps = new ArrayAdapter<String>(EditMoveActivity.this, android.R.layout.simple_list_item_1,list_steps);
        listView.setAdapter(arrayAdapterSteps);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int arg2, long arg3) {
                createStepOnClickListener((String)arg0.getItemAtPosition(arg2));
            }
        });

        createButtonListener();

        String moveToBeEdited = getIntent().getStringExtra(MyConstants.MOVE_TO_OPEN_ID);
        if(!TextUtils.isEmpty(moveToBeEdited)) {
            populateMoveDataIntoForm(moveToBeEdited);
        }
        String x = ";";

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_move, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addAnotherStep(View view) {
        arrayAdapterSteps.add("New Item");
        listView.setSelection(arrayAdapterSteps.getCount() - 1);
    }

    private void createButtonListener() {
        Button button = (Button) findViewById(R.id.add_new_step_button);

        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.activity_popup_add_user, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.new_step_input_field);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        String result = userInput.getText().toString().trim();
                                        if (!TextUtils.isEmpty(result)) {
                                            arrayAdapterSteps.add(result);
                                            listView.setSelection(arrayAdapterSteps.getCount() - 1);
                                        }
                                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }

    private void createStepOnClickListener(final String step) {
        // get prompts.xml view
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.activity_popup_modify_step, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText userInput = (EditText) promptsView
                .findViewById(R.id.edit_step_input_field);
        userInput.setText(step);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                // edit text
                                String result = userInput.getText().toString().trim();
                                if (!TextUtils.isEmpty(result)) {
                                    int stepPosition = arrayAdapterSteps.getPosition(step);
                                    arrayAdapterSteps.remove(step);
                                    arrayAdapterSteps.insert(result, stepPosition);
                                    listView.setSelection(stepPosition);
                                }
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void saveMove(View view) {
        if(validateForm()) {
            if(moveToEdit == null) {
                journal.addMove(getNewMove());
            }
            else {
                updateEditMove(moveToEdit);
                journal.updateMove(moveToEdit);
            }
            engine.setMyJournal(journal);
            finish();
        }
    }

    public void cancel(View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Are you sure you want to close, move will be lost?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    private boolean validateForm() {
        boolean result = true;
        Move newMove = getNewMove();
        EditText moveNameInput = (EditText) findViewById(R.id.edit_name_title);
        EditText moveDescriptionInput = (EditText) findViewById(R.id.moveDescriptionTextView);
        Spinner movePositionInput = (Spinner) findViewById(R.id.editPositionSpinner);
        TextView moveStepsTextInput = (TextView) findViewById(R.id.moveListTextView);
        ListView moveStepsInput = (ListView) findViewById(R.id.moveListView);

        newMove.setName(moveNameInput.getText().toString().trim());
        newMove.setDescription(moveDescriptionInput.getText().toString().trim());
        newMove.setPosition(Position.get(movePositionInput.getSelectedItem().toString()));

        if(TextUtils.isEmpty(moveNameInput.getText().toString())) {
            moveNameInput.setError("Mandatory Field");
            result = false;
        }else if(moveNameInput.getText().toString().length() > 50) {
            moveNameInput.setError("Max length is 50");
            result = false;
        }
        if(moveDescriptionInput.getText().toString().length() > 200) {
            moveDescriptionInput.setError("Max length is 200");
            result = false;
        }
        if(moveStepsInput.getCount() == 0) {
            moveStepsTextInput.setText("Steps:You haven't added any steps yet");
            moveStepsTextInput.setTextColor(Color.RED);
            result = false;
        }else {
            moveStepsTextInput.setText("Steps:");
            moveStepsTextInput.setTextColor(Color.BLACK);
        }
        if(journal.getMoves().contains(newMove) && !newMove.equals(moveToEdit)){
            moveNameInput.setError("You already have that Name for current position");
            result = false;
        }

        return result;
    }

    private Move getNewMove() {
        Move newMove = new Move();
        EditText moveNameInput = (EditText) findViewById(R.id.edit_name_title);
        EditText moveDescriptionInput = (EditText) findViewById(R.id.moveDescriptionTextView);
        Spinner movePositionInput = (Spinner) findViewById(R.id.editPositionSpinner);
        Spinner moveTopBottomInput = (Spinner) findViewById(R.id.editTopBottomSpinner);
        Spinner moveGiInput = (Spinner) findViewById(R.id.editGiSpinner);

        newMove.setName(moveNameInput.getText().toString().trim());
        newMove.setDescription(moveDescriptionInput.getText().toString().trim());
        newMove.setPosition(Position.get(movePositionInput.getSelectedItem().toString()));
        newMove.setTopBottom(TopBottom.get(moveTopBottomInput.getSelectedItem().toString()));
        newMove.setGiNoGi(Gi.get(moveGiInput.getSelectedItem().toString()));
        for(String step: list_steps) {
            newMove.addStep(step);
        }
        return newMove;
    }

    private void updateEditMove(Move move) {
        EditText moveNameInput = (EditText) findViewById(R.id.edit_name_title);
        EditText moveDescriptionInput = (EditText) findViewById(R.id.moveDescriptionTextView);
        Spinner movePositionInput = (Spinner) findViewById(R.id.editPositionSpinner);
        Spinner moveTopBottomInput = (Spinner) findViewById(R.id.editTopBottomSpinner);
        Spinner moveGiInput = (Spinner) findViewById(R.id.editGiSpinner);

        move.setName(moveNameInput.getText().toString().trim());
        move.setDescription(moveDescriptionInput.getText().toString().trim());
        move.setPosition(Position.get(movePositionInput.getSelectedItem().toString()));
        move.setTopBottom(TopBottom.get(moveTopBottomInput.getSelectedItem().toString()));
        move.setGiNoGi(Gi.get(moveGiInput.getSelectedItem().toString()));
    }

    private void populateSpinners() {
        ArrayList<String> positionValues = new ArrayList<String>();
        for(Position position: Position.values()) {
            positionValues.add(position.getValue());
        }
        Spinner positionSpinner = (Spinner)  findViewById(R.id.editPositionSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, positionValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        positionSpinner.setAdapter(adapter);

        ArrayList<String> topBottomValues = new ArrayList<String>();
        for(TopBottom topBottom: TopBottom.values()) {
            topBottomValues.add(topBottom.getValue());
        }
        Spinner topBottomSpinner = (Spinner)  findViewById(R.id.editTopBottomSpinner);
        ArrayAdapter adapterTopBottom = new ArrayAdapter(this, android.R.layout.simple_spinner_item, topBottomValues);
        adapterTopBottom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topBottomSpinner.setAdapter(adapterTopBottom);

        ArrayList<String> giValues = new ArrayList<String>();
        for(Gi gi: Gi.values()) {
            giValues.add(gi.getValue());
        }
        Spinner giSpinner = (Spinner)  findViewById(R.id.editGiSpinner);
        ArrayAdapter adapterGi = new ArrayAdapter(this, android.R.layout.simple_spinner_item, giValues);
        adapterGi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        giSpinner.setAdapter(adapterGi);
    }


    private void populateMoveDataIntoForm(String editMoveId) {
        moveToEdit = null;
        for(Move move: journal.getMoves())
        {
            if(editMoveId.equals(move.getId())){
                moveToEdit = move;
            }
        }
        if(moveToEdit != null) {
            EditText moveNameInput = (EditText) findViewById(R.id.edit_name_title);
            moveNameInput.setText(moveToEdit.getName());
            EditText moveDescriptionInput = (EditText) findViewById(R.id.moveDescriptionTextView);
            moveDescriptionInput.setText(moveToEdit.getDescription());
            Spinner movePositionInput = (Spinner) findViewById(R.id.editPositionSpinner);
            for(int i = 0; i <= Position.values().length; i++){
                if(moveToEdit.getPosition().getValue().equals(
                        Position.values()[i].getValue())) {
                    movePositionInput.setSelection(i);
                    break;
                }
            }
            Spinner moveTopBottomInput = (Spinner) findViewById(R.id.editTopBottomSpinner);
            for(int i = 0; i <= TopBottom.values().length; i++){
                if(moveToEdit.getTopBottom().getValue().equals(
                        TopBottom.values()[i].getValue())) {
                    moveTopBottomInput.setSelection(i);
                    break;
                }
            }
            Spinner moveGiInput = (Spinner) findViewById(R.id.editGiSpinner);
            for(int i = 0; i <= Gi.values().length; i++){
                if(moveToEdit.getGiNoGi().getValue().equals(
                        Gi.values()[i].getValue())) {
                    moveGiInput.setSelection(i);
                    break;
                }
            }
            arrayAdapterSteps.addAll(moveToEdit.getSteps());
            listView.setSelection(arrayAdapterSteps.getCount() - 1);
        }
    }

}
