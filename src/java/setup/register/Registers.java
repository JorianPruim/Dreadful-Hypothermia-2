package setup.register;

import setup.crafting.Knowledge;
import setup.player.Player;
import setup.world.Generatable;
import setup.world.Tile;

public class Registers {
    public static Registry<Tile> BIO = new Registry<Tile>();
    public static Registry<Knowledge> KWL = new Registry<Knowledge>();



    //entries go here.
    // public static final RegistryEntry<Item> WOODITEM = ITM.register(Item::new,"wood");

    public static final RegistryEntry<Tile> DESERT = BIO.register(Tile::new, "desert");
    public static final RegistryEntry<Tile> FOREST = BIO.register(Tile::new, "forest");
    public static final RegistryEntry<Tile> JUNGLE = BIO.register(Tile::new, "jungle");
    public static final RegistryEntry<Tile> PLAINS = BIO.register(Tile::new, "plains");
    public static final RegistryEntry<Tile> SNOW = BIO.register(Tile::new, "snow");
    public static final RegistryEntry<Tile> SWAMP = BIO.register(Tile::new, "swamp");
    public static final RegistryEntry<Tile> TUNDRA = BIO.register(Tile::new, "tundra");
    public static final RegistryEntry<Tile> TAIGA = BIO.register(Tile::new, "taiga");
    public static final RegistryEntry<Tile> SAVANNA = BIO.register(Tile::new, "savanna");




}
