package com.example.john.simonsays;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class HighScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        /*
        * Below are the variables fo use in the HighScores Activity, they are as follows:
        * Listview list                 ->A reference to our listview widget
        * ArrayList<Score> <name>       ->A list that contains score entries for various modes
        * ArrayAdapter<score> <name>    ->An adapter for each list to use for quick switching in the
        *                                 listview
        * */
        final ListView list = (ListView) findViewById(R.id.listView_Scores);
        ArrayList<Score> HighScoreOriginal = new ArrayList<>();
        ArrayList<Score> HighScoreEasy = new ArrayList<>();
        ArrayList<Score> HighScoreMedium = new ArrayList<>();
        ArrayList<Score> HighScoreHard = new ArrayList<>();
        final ArrayAdapter<Score> list_adapter_original = new ArrayAdapter<Score>(this, android.R.layout.simple_list_item_1,HighScoreOriginal);
        final ArrayAdapter<Score> list_adapter_easy = new ArrayAdapter<Score>(this, android.R.layout.simple_list_item_1,HighScoreEasy);
        final ArrayAdapter<Score> list_adapter_medium = new ArrayAdapter<Score>(this, android.R.layout.simple_list_item_1,HighScoreMedium);
        final ArrayAdapter<Score> list_adapter_hard = new ArrayAdapter<Score>(this, android.R.layout.simple_list_item_1,HighScoreHard);
        Button button_original = (Button) findViewById(R.id.button_original);
        Button button_easy = (Button) findViewById(R.id.button_easy);
        Button button_medium = (Button) findViewById(R.id.button_medium);
        Button button_hard = (Button) findViewById(R.id.button_hard);
        String[] fileName ={"Simon(Original)","Simon(Easy","Simon(Medium)","Simon(Hard)"};


        /*
        The following block of code creates a file input stream and saves our current HighScoresOrignal
        contents to it, needs to be done in the Game activity, then reloaded here*/
        FileInputStream fis;
        for(int i=0;i<fileName.length;i++) {
            try {
                fis = openFileInput(fileName[i]);
                ObjectInputStream ois = new ObjectInputStream(fis);
                switch(i) {
                    case 0:  HighScoreOriginal.addAll((ArrayList<Score>) ois.readObject());
                    case 1:  HighScoreEasy.addAll((ArrayList<Score>) ois.readObject());
                    case 2:  HighScoreMedium.addAll((ArrayList<Score>) ois.readObject());
                    case 3:  HighScoreHard.addAll((ArrayList<Score>) ois.readObject());
                }
                ois.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        //the following are the Listeners for the buttons that select the high scores list to use
        button_easy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                list.setAdapter(list_adapter_easy);
                list_adapter_easy.notifyDataSetChanged();
            }
        });

        button_medium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                list.setAdapter(list_adapter_medium);
                list_adapter_medium.notifyDataSetChanged();

            }
        });

        button_hard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                list.setAdapter(list_adapter_hard);
                list_adapter_hard.notifyDataSetChanged();
            }
        });

        button_original.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                list.setAdapter(list_adapter_original);
                list_adapter_original.notifyDataSetChanged();
            }
        });



    }
}
