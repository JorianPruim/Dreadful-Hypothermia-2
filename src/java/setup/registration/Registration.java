package setup.registration;

import world.Building;
import world.Tile;

public class Registration {
    

    public static final Register<Tile> TILES = new Register<Tile>();
    public static final Register<Building> BUILDINGS = new Register<Building>();
    //etc


    //public static final Tile PLAINS = TILES.register("plains",new Plains());


    
}
