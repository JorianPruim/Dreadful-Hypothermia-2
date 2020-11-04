package worldgen;

public class WorldGenSettings {

    public int size = 1000;
    public int maxHeatDropletSize = 40;
    public int minHeatDropletSize = 25;
    public int maxHumidityDropletSize = 50;
    public int minHumidityDropletSize = 20;
    public double heatDropletDensity = 1e-2;
    public double humidityDropletDensity = 1e-2;

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

}
