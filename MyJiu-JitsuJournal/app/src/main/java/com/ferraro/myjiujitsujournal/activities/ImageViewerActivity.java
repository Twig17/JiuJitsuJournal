package com.ferraro.myjiujitsujournal.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.ferraro.myjiujitsujournal.Constants.MyConstants;
import com.ferraro.myjiujitsujournal.mjjj.R;


public class ImageViewerActivity extends ActionBarActivity {

    private ImageView imageView;
    private ZoomControls zoom;
    private ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        int imageFileId = getIntent().getIntExtra(MyConstants.FILE_NAME_TO_VIEW, 0);
        img =(ImageView)findViewById(R.id.imageView);
        img.setImageDrawable(getResources().getDrawable(imageFileId));


        // set touchlistener
        img.setOnTouchListener(new View.OnTouchListener() {
            float mx;
            float my;

            public boolean onTouch(View arg0, MotionEvent event) {

                float curX, curY;

                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        mx = event.getX();
                        my = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        curX = event.getX();
                        curY = event.getY();
                        img.scrollBy((int) (mx - curX), (int) (my - curY));
                        mx = curX;
                        my = curY;
                        break;
                    case MotionEvent.ACTION_UP:
                        curX = event.getX();
                        curY = event.getY();
                        img.scrollBy((int) (mx - curX), (int) (my - curY));
                        break;
                }

                return true;
            }
        });

        zoom = (ZoomControls) findViewById(R.id.zoomControls1);
        zoom.setOnZoomInClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                float x = img.getScaleX();
                float y = img.getScaleY();

                img.setScaleX((float) (x+1));
                img.setScaleY((float) (y+1));
            }
        });

        zoom.setOnZoomOutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                float x = img.getScaleX();
                float y = img.getScaleY();
                if(x > 1 && y > 1) {
                    img.setScaleX((float) (x - 1));
                    img.setScaleY((float) (y - 1));
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_viewer, menu);
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
