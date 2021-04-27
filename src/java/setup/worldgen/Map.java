package setup.worldgen;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * The Map class represents a mapping of a intrinsic tile value to a setup.world.
 * a {@code int} may represent an arbitrary value like heat or humidity to a location on the setup.world.
 * This class is only used for heatmapping and humiditymapping in setup.worldgen
 *
 */

public class Map{

    int[][] data;
    Random random;




    /**
     * Default constructor returning a {@code Map} with the given architecture.
     * A {@code map} consists of "droplets" with a random size between minDropSize and maxDropSize and have a chance to spawn at a given point with a chance of dropletDensity.
     * @param size the size of the map
     * @param minDropSize the minimal droplet size
     * @param maxDropSize the maximum droplet size
     * @param dropletDensity chance of spawning a droplet at any given point
     */
    public Map(int size, int minDropSize, int maxDropSize, double dropletDensity, int seed){
        this.random = new Random(seed);
        if(minDropSize>=maxDropSize){
            throw new IllegalArgumentException("maxDropSize must be bigger than minDropSize");
        }
        this.data = new int[size][size];
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                if(Math.random()<dropletDensity){
                    generateDroplet(Math.random()<0.5?1:-1,getRandomSize(minDropSize,maxDropSize),i,j);
                }
            }
        }

    }

    public Map(WorldGenSettings.OreMapSettings ore, int size, int seed){
        this(size,ore.minDropSize,ore.maxDropSize,ore.density,seed);
    }


    /**
     * Generates a circular droplet and chances the map accordingly
     * @param value the value to be added to the map (usually 1 or -1)
     * @param radius the size of the circle
     * @param x the x-coordinate of the droplet center
     * @param z the z-coordinate of the droplet center
     */
    private void generateDroplet(int value, int radius, int x, int z){
        for(int i = x-radius; i<x+radius; i++){
            for(int j = z-radius; j<z+radius; j++){
                if(j<0||i<0||j>=data.length||i>=data.length){
                    continue;
                }
                if(Math.sqrt(Math.pow(j-z,2)+Math.pow(i-x,2))<radius){
                    data[i][j] += value;
                }
            }
        }
    }

    /**
     * utility method that generates a number between lower and upper
     * @param lower lower bound
     * @param upper upper bound
     * @return an integer between lower and upper
     */
    private static int getRandomSize(int lower, int upper){
        Random random = new Random();
        return random.nextInt(upper-lower)+lower;
    }

    public int get(int x,int y){
        return data[x][y];
    }

    /**
     * Gives a string representation of a map. At this point, it replaces all numbers to represent a heatmap.
     * @return a String representation
     */

    public String toVisualString() {
        StringBuilder out = new StringBuilder();
        for(int[] row : data){
            out.append(Arrays.toString(row));
            out.append("\n");
        }
        return out.toString().replaceAll(","," ").replaceAll("0","N").replaceAll("[0-9]","H").replaceAll("-H","C").replaceAll("H[HN]+","H").replaceAll("C[HN]+","C");
    }

    @Override
    public String toString() {
        //The output String
        StringBuilder finalString = new StringBuilder();
        //Integer with the previous output. If there is a sequence of multiple fields having the same value, it will store it like this: (amount of same values)(Character)
        int lastInput = 0;
        //Will count the amount of same values. Is incremented every time the value of lastInput is equal to the current input. Otherwise will be reset to 1
        int sequence = 1;

        for(int i = 0; i < this.data.length; i++){
            for(int j = 0; j < this.data.length; j++){
                if (lastInput != this.data[i][j] && sequence == 1) {
                    //We got another input than we stored in lastInput, and we don't have a sequence (sequence == 1)
                    //So we put just the corresponding character to our input value in finalString
                    finalString.append((char) (78 + this.data[i][j]));
                    lastInput = this.data[i][j];
                } else if (lastInput != this.data[i][j] && sequence > 1){
                    //This time we got a sequence, and this sequence has ended (since lastInput is not equal to the current input)
                    //First we put the sequence in finalString
                    finalString.append(sequence);
                    //And then the corresponding character
                    finalString.append((char) (78 + this.data[i][j]));
                    lastInput = this.data[i][j];
                    sequence = 1;
                } else {
                    //If this runs, the lastInput will be equal to the current input
                    //All we have to do is increment the sequence
                    sequence++;
                }
            }
        }
        return finalString.toString();
    }

    public int[][] getData(){
        return data;
    }
}
