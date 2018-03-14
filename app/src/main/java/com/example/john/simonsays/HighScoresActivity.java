package com.example.john.simonsays;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

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

        //testing, adding a player to each list
        HighScoreEasy.add(new Score("John",80));
        HighScoreMedium.add(new Score("Sasha",15));
        HighScoreHard.add(new Score("Daniel",5));
        HighScoreOriginal.add(new Score("Dr. Nicholson",100));


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
