package com.example.john.simonsays;

import android.media.SoundPool;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


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
        final MoveGenerator gen = new MoveGenerator();

        //Testing purposes!!!!!!!!!!!!!!!!!!!!!!======================================
        final TextView _testview = (TextView) findViewById(R.id.textview_moves);
        final Button _testbtn = (Button) findViewById(R.id.button_test);

        _testbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                gen.addToken();
                _testview.setText(gen.token);
                // Making the motion event objects
                final MotionEvent motionEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis()+100, MotionEvent.ACTION_DOWN, 0.0f, 0.0f, 0);
                final MotionEvent motionEvent2 = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis()+100, MotionEvent.ACTION_UP, 0.0f, 0.0f, 0);


                for(int i=0;i<gen.token.length();i++) {
                    int timehelp = i;
                    final ImageView temp;
                    if (gen.token.charAt(i) == 'R') {
                        temp = red;
                    } else if (gen.token.charAt(i) == 'G') {
                        temp = green;
                    } else if (gen.token.charAt(i) == 'B') {
                        temp = blue;
                    } else if (gen.token.charAt(i) == 'Y') {
                        temp = yellow;
                    } else {
                        temp = null;
                    }


                    // dispatcing events on a delay
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            temp.dispatchTouchEvent(motionEvent);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    temp.dispatchTouchEvent(motionEvent2);
                                }
                            }, 700);


                        }
                    }, 1000*(timehelp+1));


                }




            }
        });

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!======================================

        blue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                switch (arg1.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        blue.setImageResource(R.drawable.blue_pressed);
                        sounds.play(beepId,1.0f,1.0f,1,0,1.3f);
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
    }
}
