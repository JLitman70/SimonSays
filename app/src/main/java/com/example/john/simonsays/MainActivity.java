package com.example.john.simonsays;

import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final SoundPool.Builder soundbuilder = new SoundPool.Builder();
        final SoundPool sounds = soundbuilder.build();
        final int beepId = sounds.load(this, R.raw.beep,1);

        //--------------------------------------second layout code start------------------------
        Button play = (Button)findViewById(R.id.play_button);
        play.setOnClickListener(this);

        Spinner gamemode = findViewById(R.id.gamemode_dropdown);
        gamemode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //String text = adapterView.getSelectedItem().toString();
                //Log.i("GAMEMODE", text); testing value is appearing
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //String text = adapterView.getSelectedItem().toString();
                //Log.i("GAMEMODE", text); testing value is appearing
            }
        });

        //--------------------------------------second layout code finish----------------------



        //final ImageView blue = (ImageView) findViewById(R.id.imageview_blue);
        //final ImageView green = (ImageView) findViewById(R.id.imageview_green);
        //final ImageView red = (ImageView) findViewById(R.id.imageview_red);
        //final ImageView yellow = (ImageView) findViewById(R.id.imageview_yellow);
        /*
        blue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        blue.setImageResource(R.drawable.blue_pressed);
                        sounds.play(beepId,1.0f,1.0f,1,0,1.3f);
                        //Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        blue.setImageResource(R.drawable.blue);

                        break;
                    }
                }
                return true;
            }
        });

        green.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        green.setImageResource(R.drawable.green_pressed);
                        sounds.play(beepId,1.0f,1.0f,1,0,1.2f);

                        // Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        green.setImageResource(R.drawable.green);

                        break;
                    }
                }
                return true;
            }
        });

        red.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        red.setImageResource(R.drawable.red_pressed);
                        sounds.play(beepId,1.0f,1.0f,1,0,1.1f);

                        //Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        red.setImageResource(R.drawable.red);

                        break;
                    }
                }
                return true;
            }
        });

        yellow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        yellow.setImageResource(R.drawable.yellow_pressed);
                        sounds.play(beepId,1.0f,1.0f,1,0,1.0f);

                        //Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case MotionEvent.ACTION_UP:{
                        yellow.setImageResource(R.drawable.yellow);

                        break;
                    }
                }
                return true;
            }
        });

        */

    }

    @Override
    public void onClick(View view) {

    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
