package worldgen;

import java.util.Arrays;
import java.util.Random;

public class Map{

    int[][] data;

    public Map(int size, int minDropSize, int maxDropSize, double dropletDensity){
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

    private static int getRandomSize(int lower, int upper){
        Random random = new Random();
        return random.nextInt(upper-lower)+lower;
    }

    private static int subtractOrReplace(int in){
        return in>-1?in-1:-1;
    }

    private static int addOrReplace(int in){
        return in<1?in+1:1;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for(int[] row : data){
            out.append(Arrays.toString(row));
            out.append("\n");
        }
        return out.toString().replaceAll(","," ").replaceAll("0","N").replaceAll("[0-9]","H").replaceAll("-H","C").replaceAll("H[HN]+","H").replaceAll("C[HN]+","C");
    }
}
