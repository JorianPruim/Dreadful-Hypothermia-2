package setup.worldgen;

import objects.items.Item;
import setup.register.Registers;

import java.util.Random;

/*
Here be the magic constants
 */
public class WorldGenSettings {

    public int size = 1000;
    public int maxHeatDropletSize = 10;
    public int minHeatDropletSize = 5;
    public int maxHumidityDropletSize = 10;
    public int minHumidityDropletSize = 5;
    public double heatDropletDensity = 5e-2;
    public double humidityDropletDensity = 5e-2;
    public Thresholds thresholds = Thresholds.get();
    public final int seed = (new Random()).nextInt();

    public OreMapSettings copper = new OreMapSettings(2,4,1e-4, Registers.ITEM_COPPER.get(),100,500);

    private WorldGenSettings(){}
    public static WorldGenSettings getInstance(){
        return new WorldGenSettings();
    }
    public WorldGenSettings setSize(int size){
        this.size = size*8;
        return this;
    }
    public WorldGenSettings setSize(int size, boolean strict){
        if(strict){
            if(size%8==0){
                this.size = size;
                return this;
            }else{
                throw new IllegalArgumentException("Must be a multiple of 8");
            }
        }else{
            return setSize(size);
        }
    }

    public static class Thresholds{
        public static Thresholds get(){
            return new Thresholds();
        }
        public int minMediocreHumidityThreshold = -3;
        public int maxMediocreHumidityThreshold = 3;
        public int minMediocreHeatThreshold = -3;
        public int maxMediocreHeatThreshold = 3;
    }

    public static class OreMapSettings{
        public int minDropSize;
        public int maxDropSize;
        public double density;
        public Item yield;
        public int minYield;
        public int maxYield;
        public OreMapSettings(int p1, int p2, double p3, Item p4, int p5, int p6){
            this.minDropSize = p1;
            this.maxDropSize = p2;
            this.density = p3;
            this.yield = p4;
            this.minYield = p5;
            this.maxYield = p6;
        }
    }


}
