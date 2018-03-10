package com.example.john.simonsays;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class MainMenu extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
