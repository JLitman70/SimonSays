package com.example.john.simonsays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Button play = (Button)findViewById(R.id.play_button);
        play.setOnClickListener(this);

        final Spinner gamemode = findViewById(R.id.gamemode_dropdown);
        gamemode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               //String text = adapterView.getSelectedItem().toString();
               //Log.i("GAMEMODE", text);//testing value is appearing

               //  = adapterView.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //String text = adapterView.getSelectedItem().toString();
                //Log.i("GAMEMODE", text); testing value is appearing
            }
        });

        //--------------------------------------second layout code finish----------------------

    }

    @Override
    public void onClick(View view) {
        //Intent gameIntent = new Intent(this,MainActivity.class);
        //startActivity(gameIntent);
        //!!!!!!!!!!!!!!!!CAUSES ERROR OPENING MAINACTIVITY!!!!!!!!!!!!!!
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          {
              //statements seem to be working, highscore is untested'ish' because i did not want to mess with the layout (as it appears blank)
              if(item.getItemId()== R.id.highscores_activity){
                  Intent highscoreIntent = new Intent(this,HighScoresActivity.class);
                  startActivity(highscoreIntent);

              }
              else if(item.getItemId()== R.id.credits_activity) {
                //working even though it shows blank activity that could be anything, already tested
                  Intent creditsIntent = new Intent(this, CreditsActivity.class);
                  startActivity(creditsIntent);
              }
          }
        return super.onOptionsItemSelected(item);
      }


}
