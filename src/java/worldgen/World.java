package worldgen;

import world.Tile;

public class World {

    int size;
    Tile[][] tiles;


    private World(Tile[][] tiles){this.tiles = tiles; this.size = tiles.length;}

    private static World generate(WorldGenSettings settings){
        Map heat = new Map(settings.size/8,settings.minHeatDropletSize,settings.maxHeatDropletSize,settings.heatDropletDensity);
        Map humidity = new Map(settings.size/8,settings.minHumidityDropletSize,settings.maxHumidityDropletSize,settings.humidityDropletDensity);
        Tile[][] tiles = new Tile[settings.size][settings.size];
        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {

            }
        }



        return null;
    }



    /*
    1: Nepal biomes
    2: generate assets
    3: fewest
     */


}
