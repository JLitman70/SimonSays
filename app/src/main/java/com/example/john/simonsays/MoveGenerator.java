package com.example.john.simonsays;

import java.util.Random;

/**
 * Created by JohnL on 3/9/2018.
 */

public class MoveGenerator {
    //This will generate a random number to select tokens
    public Random rand = new Random();
    String token;
    String alphabet;
    boolean isRandom;

    public MoveGenerator(String mode){
        if(mode.equals("easy")){
            alphabet ="RG";
            isRandom = false;
        }else if(mode.equals("medium")){
            alphabet ="RGB";
            isRandom = false;
        }else if(mode.equals("normal")){
            alphabet ="RGBY";
            isRandom = false;
        }else if(mode.equals("hard")){
            alphabet ="RGBY";
            isRandom = true;
        }

        token = "";
    }
    //add token adds a token to our string
    public void addToken(int length){
        int index = rand.nextInt(alphabet.length());

        if(isRandom){
            length+=token.length();
            clearToken();
        }
        for(int i=0;i<length;i++) {
            if (index == 0) {
                token += "R";
            } else if (index == 1) {
                token += "G";
            } else if (index == 2) {
                token += "B";
            } else if (index == 3) {
                token += "Y";
            }
            index = rand.nextInt(alphabet.length());
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

