package com.example.john.simonsays;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    int score = 0;
    String name ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*The following block of code instantiates Widget objects linked to the widgets in the XML,
        * so we can manipulate them. These items are named as appropriately as possible
        */
        //==========================================================================================
        final SoundPool.Builder soundbuilder = new SoundPool.Builder();
        final SoundPool sounds = soundbuilder.build();
        final int beep_red = sounds.load(this, R.raw.beep_red,1);
        final int beep_blue = sounds.load(this, R.raw.beep_blue,1);
        final int beep_green = sounds.load(this, R.raw.beep_green,1);
        final int beep_yellow = sounds.load(this, R.raw.beep_yellow,1);
        final int beep_correct = sounds.load(this, R.raw.beep_correct,1);
        final int beep_incorrect = sounds.load(this, R.raw.beep_incorrect,1);
        final ImageView imageview_blue = (ImageView) findViewById(R.id.imageview_blue);
        final ImageView imageview_green = (ImageView) findViewById(R.id.imageview_green);
        final ImageView imageview_red = (ImageView) findViewById(R.id.imageview_red);
        final ImageView imageview_yellow = (ImageView) findViewById(R.id.imageview_yellow);
        final MoveGenerator gen = new MoveGenerator(getIntent().getStringExtra("gamemode_type"));
        //final TextView _testview = (TextView) findViewById(R.id.textview_moves);
        final Button button_hint = (Button) findViewById(R.id.button_test);
        final String playername = getIntent().getStringExtra("nameText"); //users name from main menu
        name = playername;
        //==========================================================================================
        /*Below is our thread object being created to demonstrate the pattern the user should push
        * the Thread automatically runs once, but lays dormant until the user invokes the button_hint
        * onClick method*/
        //==========================================================================================
        final Thread demo = new Thread() {
            @Override
            public void run() {
                int timehelp = 0;
                for (int i = 0; i < gen.token.length(); i++) {
                    timehelp = i;
                    final ImageView temp;
                    final Drawable draw_up;
                    final Drawable draw_down;
                    final int beep_cur;
                    if (gen.token.charAt(i) == 'R') {
                        temp = imageview_red;
                        draw_up = getDrawable(R.drawable.red);
                        draw_down = getDrawable(R.drawable.red_pressed);
                        beep_cur = beep_red;
                    } else if (gen.token.charAt(i) == 'G') {
                        temp = imageview_green;
                        draw_up = getDrawable(R.drawable.green);
                        draw_down = getDrawable(R.drawable.green_pressed);
                        beep_cur = beep_green;
                    } else if (gen.token.charAt(i) == 'B') {
                        temp = imageview_blue;
                        draw_up = getDrawable(R.drawable.blue);
                        draw_down = getDrawable(R.drawable.blue_pressed);
                        beep_cur = beep_blue;
                    } else if (gen.token.charAt(i) == 'Y') {
                        temp = imageview_yellow;
                        draw_up = getDrawable(R.drawable.yellow);
                        draw_down = getDrawable(R.drawable.yellow_pressed);
                        beep_cur = beep_yellow;
                    } else {
                        temp = null;
                        draw_up = null;
                        draw_down = null;
                        beep_cur = 0;
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            temp.setImageDrawable(draw_down);
                            sounds.play(beep_cur, 1.0f, 1.0f, 1, 0, 1.0f);
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    temp.setImageDrawable(draw_up);
                                }
                            }, 700);
                        }
                    }, 1000 * (timehelp + 1));
                    if(timehelp==gen.token.length()-1) {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                button_hint.setEnabled(true);
                            }
                        }, 1000 * (timehelp + 2));
                    }
                }
            }
        };
        //==========================================================================================
        /*below are values being set and used for testing, as well as setting up the game*/
        //==========================================================================================
        gen.addToken(1);
        //_testview.setText(gen.token);
        button_hint.setEnabled(false);
        demo.run();
        //==========================================================================================
        /*This is the definition for the onCLick of the button function, it is set up so that you can
        * only have the hint playing once at a time, so that multiple instances of the demonstration
        * don't overlap and obfuscate the signals*/
        //==========================================================================================
        button_hint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                button_hint.setEnabled(false);
                demo.run();
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button_hint.setEnabled(true);
                    }
                }, (gen.token.length()+1)*1000);
                }
        });
        //==========================================================================================
        /*The following block sets the touch listeners to the imageviews so they behave like buttons
        * it sets a delay for the on touch events so they change sprites, play a noise and change back
        * when you remove your finger
        * ==========================================================================================*/

        imageview_blue.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if(button_hint.isEnabled()){
                    switch (arg1.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            imageview_blue.setImageResource(R.drawable.blue_pressed);
                            sounds.play(beep_blue,1.0f,1.0f,1,0,1.0f);
                            break;
                        }
                        case MotionEvent.ACTION_UP:{
                            imageview_blue.setImageResource(R.drawable.blue);
                            if (gen.match('B')){
                                if(gen.current==gen.token.length()){
                                    sounds.play(beep_correct, 1.0f, 1.0f, 1, 0, 1.0f);
                                    gen.addToken(1);
                                    button_hint.performClick();
                                   // _testview.setText(gen.token);
                                    gen.current=0;
                                    score++;
                                }
                            }else{
                                //incorrect
                                if(score!=0) {
                                    Score curr = new Score(playername, gen.token.length() - 1);
                                    ArrayList curr_list = new ArrayList<>();
                                    FileInputStream fis;
                                    try {
                                        fis = openFileInput(getIntent().getStringExtra("gamemode_type"));
                                        ObjectInputStream ois = new ObjectInputStream(fis);
                                        curr_list.addAll((ArrayList<Score>) ois.readObject());
                                        ois.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    curr_list.add(curr);
                                    FileOutputStream fos;
                                    try {
                                        fos = openFileOutput(getIntent().getStringExtra("gamemode_type"), Context.MODE_PRIVATE);
                                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                                        oos.reset();
                                        oos.writeObject(curr_list);
                                        oos.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                gen.current=0;
                                gen.clearToken();
                                sounds.play(beep_incorrect, 1.0f, 1.0f, 1, 0, 1.0f);
                                gen.addToken(1);
                                button_hint.performClick();
                                //_testview.setText(gen.token);
                                score=0;
                            }
                            break;
                        }
                    }
                    return true;
                }
                return true;
            }
        });
        imageview_green.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if(button_hint.isEnabled()) {
                    switch (arg1.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            imageview_green.setImageResource(R.drawable.green_pressed);
                            sounds.play(beep_green, 1.0f, 1.0f, 1, 0, 1.0f);
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            imageview_green.setImageResource(R.drawable.green);
                            if (gen.match('G')){
                                if(gen.current==gen.token.length()) {
                                    sounds.play(beep_correct, 1.0f, 1.0f, 1, 0, 1.0f);
                                    gen.addToken(1);
                                    button_hint.performClick();
                                    //_testview.setText(gen.token);
                                    gen.current=0;
                                    score++;
                                }
                            }else{
                                //incorrect
                                if(score!=0) {
                                    Score curr = new Score(playername, gen.token.length() - 1);
                                    ArrayList curr_list = new ArrayList<>();
                                    FileInputStream fis;
                                    try {
                                        fis = openFileInput(getIntent().getStringExtra("gamemode_type"));
                                        ObjectInputStream ois = new ObjectInputStream(fis);
                                        curr_list.addAll((ArrayList<Score>) ois.readObject());
                                        ois.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    curr_list.add(curr);
                                    FileOutputStream fos;
                                    try {
                                        fos = openFileOutput(getIntent().getStringExtra("gamemode_type"), Context.MODE_PRIVATE);
                                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                                        oos.reset();
                                        oos.writeObject(curr_list);
                                        oos.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                gen.current=0;
                                gen.clearToken();
                                sounds.play(beep_incorrect, 1.0f, 1.0f, 1, 0, 1.0f);
                                gen.addToken(1);
                                button_hint.performClick();
                                //_testview.setText(gen.token);
                                score=0;
                            }
                            break;
                        }
                    }
                    return true;
                }
                return true;
            }
        });
        imageview_red.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if(button_hint.isEnabled()) {
                    switch (arg1.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            imageview_red.setImageResource(R.drawable.red_pressed);
                            sounds.play(beep_red, 1.0f, 1.0f, 1, 0, 1.0f);
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            imageview_red.setImageResource(R.drawable.red);
                            if (gen.match('R')){
                                if(gen.current==gen.token.length()) {
                                    sounds.play(beep_correct, 1.0f, 1.0f, 1, 0, 1.0f);
                                    gen.addToken(1);
                                    button_hint.performClick();
                                    //_testview.setText(gen.token);
                                    gen.current=0;
                                    score++;
                                }
                            }else{
                                //incorrect
                                if(score!=0){
                                Score curr = new Score(playername,gen.token.length()-1);
                                ArrayList curr_list = new ArrayList<>();
                                FileInputStream fis;
                                try {
                                    fis = openFileInput(getIntent().getStringExtra("gamemode_type"));
                                    ObjectInputStream ois = new ObjectInputStream(fis);
                                    curr_list.addAll((ArrayList<Score>) ois.readObject());
                                    ois.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                curr_list.add(curr);
                                FileOutputStream fos;
                                try {
                                    fos = openFileOutput(getIntent().getStringExtra("gamemode_type"), Context.MODE_PRIVATE);
                                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                                    oos.reset();
                                    oos.writeObject(curr_list);
                                    oos.close();
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }}
                                gen.current=0;
                                gen.clearToken();
                                sounds.play(beep_incorrect, 1.0f, 1.0f, 1, 0, 1.0f);
                                gen.addToken(1);
                                button_hint.performClick();
                                //_testview.setText(gen.token);
                                score=0;
                            }
                            break;
                        }
                    }
                    return true;
                }
                return true;
            }
        });
        imageview_yellow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (button_hint.isEnabled()) {
                    switch (arg1.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                            imageview_yellow.setImageResource(R.drawable.yellow_pressed);
                            sounds.play(beep_yellow, 1.0f, 1.0f, 1, 0, 1.0f);
                            break;
                        }
                        case MotionEvent.ACTION_UP: {
                            imageview_yellow.setImageResource(R.drawable.yellow);
                            if (gen.match('Y')) {
                                if (gen.current == gen.token.length()) {
                                    sounds.play(beep_correct, 1.0f, 1.0f, 1, 0, 1.0f);
                                    gen.addToken(1);
                                    button_hint.performClick();
                                    //_testview.setText(gen.token);
                                    gen.current = 0;
                                    score++;
                                }
                            } else {
                                if(score!=0) {
                                    Score curr = new Score(playername, gen.token.length() - 1);
                                    ArrayList curr_list = new ArrayList<>();
                                    FileInputStream fis;
                                    try {
                                        fis = openFileInput(getIntent().getStringExtra("gamemode_type"));
                                        ObjectInputStream ois = new ObjectInputStream(fis);
                                        curr_list.addAll((ArrayList<Score>) ois.readObject());
                                        ois.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    curr_list.add(curr);
                                    FileOutputStream fos;
                                    try {
                                        fos = openFileOutput(getIntent().getStringExtra("gamemode_type"), Context.MODE_PRIVATE);
                                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                                        oos.reset();
                                        oos.writeObject(curr_list);
                                        oos.close();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                gen.current = 0;
                                gen.clearToken();
                                sounds.play(beep_incorrect, 1.0f, 1.0f, 1, 0, 1.0f);
                                gen.addToken(1);
                                button_hint.performClick();
                                //_testview.setText(gen.token);
                                score=0;
                            }
                            break;
                        }
                    }
                    return true;
                }
                return true;
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(score!=0){
        Score curr = new Score(name,score);
        ArrayList curr_list = new ArrayList<>();
        FileInputStream fis;
        try {
            fis = openFileInput(getIntent().getStringExtra("gamemode_type"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            curr_list.addAll((ArrayList<Score>) ois.readObject());
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        curr_list.add(curr);
        FileOutputStream fos;
        try {
            fos = openFileOutput(getIntent().getStringExtra("gamemode_type"), Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.reset();
            oos.writeObject(curr_list);
            oos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        }
        super.onBackPressed();

    }
}