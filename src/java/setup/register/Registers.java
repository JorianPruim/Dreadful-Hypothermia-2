package setup.register;

import setup.crafting.Knowledge;
import setup.world.Generatable;
import setup.world.Tile;

public class Registers {
    public static Registry<Tile> BIO = new Registry<Tile>();
    public static Registry<Knowledge> KWL = new Registry<Knowledge>();



    //entries go here.
    // public static final RegistryEntry<ItemWood> WOODITEM = ITM.register(ItemWood::new,"wood");

    public static final RegistryEntry<Tile> DESERT = BIO.register(()-> new Tile(), "desert");
    public static final RegistryEntry<Tile> FOREST = BIO.register(()-> new Tile(), "forest");
    public static final RegistryEntry<Tile> JUNGLE = BIO.register(()-> new Tile(), "jungle");
    public static final RegistryEntry<Tile> PLAINS = BIO.register(()-> new Tile(), "plains");
    public static final RegistryEntry<Tile> SNOW = BIO.register(()-> new Tile(), "snow");
    public static final RegistryEntry<Tile> SWAMP = BIO.register(()-> new Tile(), "swamp");
    public static final RegistryEntry<Tile> TUNDRA = BIO.register(()-> new Tile(), "tundra");
    public static final RegistryEntry<Tile> TAIGA = BIO.register(()-> new Tile(), "taiga");
    public static final RegistryEntry<Tile> SAVANNA = BIO.register(()-> new Tile(), "savanna");




}
