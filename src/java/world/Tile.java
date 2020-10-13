package world;

import worldgen.Biome;

public class Tile {

  public static String getMapData(int[][] map){
        //The output String
        String finalString = "";
        //Integer with the previous output. If there is a sequence of multiple fields having the same value, it will store it like this: (amount of same values)(Character)
        int lastInput = 0;
        //Will count the amount of same values. Is incremented every time the value of lastInput is equal to the current input. Otherwise will be reset to 1
        int sequence = 1;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map.length; j++){
                if (lastInput != map[i][j] && sequence == 1) {
                    //We got another input than we stored in lastInput, and we don't have a sequence (sequence == 1)
                    //So we put just the corresponding character to our input value in finalString
                    finalString += (char) (78 + map[i][j]);
                    lastInput = map[i][j];
                } else if (lastInput != map[i][j] && sequence > 1){
                    //This time we got a sequence, and this sequence has ended (since lastInput is not equal to the current input)
                    //First we put the sequence in finalString
                    finalString += (int)sequence;
                    //And then the corresponding character
                    finalString += (char)(78 + map[i][j]);
                    lastInput = map[i][j];
                    sequence = 1;
                } else {
                    //If this runs, the lastInput will be equal to the current input
                    //All we have to do is increment the sequence
                    sequence++;
                }
            }
        }
        return finalString;
      //not tested, but should do the job
    }

}
