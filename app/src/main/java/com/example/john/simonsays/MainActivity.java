package com.example.john.simonsays;

import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SoundPool.Builder soundbuilder = new SoundPool.Builder();
        final SoundPool sounds = soundbuilder.build();
        final int beepId = sounds.load(this, R.raw.beep,1);
        final ImageView blue = (ImageView) findViewById(R.id.imageview_blue);
        final ImageView green = (ImageView) findViewById(R.id.imageview_green);
        final ImageView red = (ImageView) findViewById(R.id.imageview_red);
        final ImageView yellow = (ImageView) findViewById(R.id.imageview_yellow);
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
}
