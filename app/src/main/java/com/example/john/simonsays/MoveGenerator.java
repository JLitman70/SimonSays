package com.example.john.simonsays;

import java.util.Random;

/**
 * Created by JohnL on 3/9/2018.
 */

/*Our move generator class is responsible for generating a set of moves for the player to try and
* replicate, the move class consist of the following members:
* -Random rand      -> A random number generator used to select symbols fo our token
* -String token     -> A string of symbols, representing the order of the moves
* -String alphabet  -> A string representing the possible symbols that can be generated
* -boolean isRandom -> A bool representing wheather or not the token is regenerated or built from
*
* Next is a list of the Functions of MoveGenerator and what they do
* -MoveGenerator(String mode)   -> A constructor that sets the game mode based on a passed string
* -AddToken(int length)         -> A function that adds length number of symbols from the alphabet to
* the token based on the current rules
* -clearToken()                   -> A function that resets the token to ""
* -match(???)                     -> !!!========NEEDS AN IMPLEMENTATION========!!!
* */
public class MoveGenerator {
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
    public void clearToken(){
        token = "";
    }
    //public boolean match(){return true;}

}

