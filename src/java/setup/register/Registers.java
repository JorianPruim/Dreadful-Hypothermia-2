package setup.register;

import objects.buildings.GenericTree;
import objects.buildings.Pebble;
import objects.buildings.WoodPile;
import objects.items.Item;
import objects.items.ItemWood;
import setup.crafting.Knowledge;
import setup.player.Player;
import setup.world.Building;
import setup.world.Generatable;
import setup.world.Tile;


import java.util.function.Supplier;

public class Registers {
    public static Registry<Tile> BIO = new Registry<Tile>();
    public static Registry<Knowledge> KWL = new Registry<Knowledge>();
    public static Registry<Building> BLD = new Registry<Building>();
    public static Registry<Item> ITM = new Registry<Item>();

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


    public static final RegistryEntry<GenericTree> TREE = BLD.register(GenericTree::new,"tree");
    public static final RegistryEntry<ItemWood> WOOD =ITM.register(ItemWood::new,"wood");
    public static final RegistryEntry<WoodPile> WOODPILE = BLD.register(WoodPile::new,"woodpile");

    public static final RegistryEntry<Pebble> PEBBLE = BLD.register(Pebble::new,"pebble");
    public static final RegistryEntry<Item> ITEMPEBBLE = ITM.register(()->new Item(0.2,0.5),"pebble");



}
