package com.ferraro.myjiujitsujournal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.mjjj.Engine;
import com.ferraro.myjiujitsujournal.mjjj.Gym;
import com.ferraro.myjiujitsujournal.mjjj.R;

public class GymActivity extends ActionBarActivity {
    Gym thisGym;
    Engine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        engine = Engine.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);
        String gymId = getIntent().getStringExtra(MyConstants.GYM_ID_TO_DISPLAY);
        if(gymId == null) {
            return;
        }
        getDisplayGym(gymId);
        if(thisGym == null) {
            return;
        }

        TextView gymNameView =(TextView)findViewById(R.id.gymNameDisplay);
        gymNameView.setText(thisGym.getName());

        ImageView imageScheduleView =(ImageView)findViewById(R.id.scheduleView);
        imageScheduleView.setImageDrawable(getResources().getDrawable(thisGym.getScheduleImageName()));
        imageScheduleView.setTag(thisGym.getScheduleImageName());

        ImageView imageIconView =(ImageView)findViewById(R.id.logoView);
        imageIconView.setImageDrawable(getResources().getDrawable(thisGym.getIconImageName()));
        imageIconView.setTag(thisGym.getIconImageName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gym, menu);
        return true;
    }

    public void viewImage(View view){
        ImageView clickedImageView = (ImageView) view;
        int imageId =  (int)clickedImageView.getTag();
        Intent imageViewIntent = new Intent(this, ImageViewerActivity.class);
        imageViewIntent.putExtra(MyConstants.FILE_NAME_TO_VIEW, imageId);
        startActivity(imageViewIntent);
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

    private void getDisplayGym(String id) {
        for(Gym gym: engine.getDatabase().getAllGym()) {
            if(gym.getId().equals(id)) {
                thisGym = gym;
            }
        }
    }
}
