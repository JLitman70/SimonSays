package com.example.john.simonsays;

import java.io.Serializable;

/**
 * Created by JohnL on 3/14/2018.
 *
 * The following is a class to encapsulate the fields for a 'Score'
 * this class will be used to instantiate the high scores collections in the other activities
 * .toString() will be overwritten to displaye to the HighScores ListView.
 */

public class Score implements Serializable {
    public String player;
    public int score;

    public Score(String player, int score) {
        this.player = player;
        this.score = score;
    }
    public Score(){}

    public String toString(){
        return player + "\t\t\t" +score;
    }
}
