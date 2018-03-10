package com.example.john.simonsays;

import java.util.Random;

/**
 * Created by JohnL on 3/9/2018.
 */

public class MoveGenerator {
    //This will generate a random number to select tokens
    public Random rand = new Random();
    String token;

    public MoveGenerator(){
        token = "";
    }
    //add token adds a token to our string
    public void addToken(){
        int index = rand.nextInt(4);

        if(index == 0){
            token+="R";
        }else if(index==1){
            token+="G";
        }else if(index ==2){
            token+="B";
        }else if(index ==3){
            token+="Y";
        }

    }
    //clears a token
    public void clearToken(){
        token = "";
    }
    //matches a token
    public boolean match(String played){
        if (token.equals(played)){
            return true;
        }else{
            return false;
        }
    }


}

