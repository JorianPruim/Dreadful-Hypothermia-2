package setup.register;

import objects.buildings.GenericTree;
import objects.buildings.Pebble;
import objects.buildings.Reeds;
import objects.buildings.WoodPile;
import objects.items.Axe;
import objects.items.Item;
import objects.items.ItemWood;
import objects.ores.Ore;
import setup.crafting.Knowledge;
import setup.crafting.Recipe;
import setup.world.Building;
import setup.world.Tile;

public class Registers {
    public static Registry<Tile> BIO = new Registry<Tile>();
    public static Registry<Knowledge> KWL = new Registry<Knowledge>();
    public static Registry<Building> BLD = new Registry<Building>();
    public static Registry<Item> ITM = new Registry<Item>();
    public static Registry<Recipe> RCP = new Registry<Recipe>();
    public static Registry<Ore> ORE = new Registry<Ore>();

    //entries go here.
    //public static final RegistryEntry<Type> NAME = REGISTRY.register(Constructor::reference, name);

    public static final RegistryEntry<Tile> DESERT = BIO.register(Tile::new, "desert");
    public static final RegistryEntry<Tile> FOREST = BIO.register(Tile::new, "forest");
    public static final RegistryEntry<Tile> JUNGLE = BIO.register(Tile::new, "jungle");
    public static final RegistryEntry<Tile> PLAINS = BIO.register(Tile::new, "plains");
    public static final RegistryEntry<Tile> SNOW = BIO.register(Tile::new, "snow");
    public static final RegistryEntry<Tile> SWAMP = BIO.register(Tile::new, "swamp");
    public static final RegistryEntry<Tile> TUNDRA = BIO.register(Tile::new, "tundra");
    public static final RegistryEntry<Tile> TAIGA = BIO.register(Tile::new, "taiga");
    public static final RegistryEntry<Tile> SAVANNA = BIO.register(Tile::new, "savanna");
    public static final RegistryEntry<Tile> WATER = BIO.register(Tile::new,"water");


    public static RegistryEntry<Ore> ORE_COPPER = ORE.register(Ore::new,"copper");

    public static RegistryEntry<Item> ITEM_COPPER = ITM.register(()->new Item(0.1,0.4),"copper");

    public static final RegistryEntry<GenericTree> TREE = BLD.register(GenericTree::new,"tree");
    public static final RegistryEntry<ItemWood> WOOD =ITM.register(ItemWood::new,"wood");
    public static final RegistryEntry<WoodPile> WOODPILE = BLD.register(WoodPile::new,"woodpile");
    public static final RegistryEntry<Item> STICK = ITM.register(()->new Item(0.1,0.1), "stick");
    public static final RegistryEntry<Item> AXE_SIMPLE = ITM.register(Axe::new,"axesimple");

    public static final RegistryEntry<Reeds> REEDS = BLD.register(Reeds::new,"reeds");
    public static final RegistryEntry<Item> ITEM_REEDS = ITM.register(()->new Item(0.1,0.2),"reeds");


    public static final RegistryEntry<Pebble> PEBBLE = BLD.register(Pebble::new,"pebble");
    public static final RegistryEntry<Item> ITEM_PEBBLE = ITM.register(()->new Item(0.2,0.5),"pebble");


    public static final RegistryEntry<Recipe> RECIPE_AXE = RCP.register(()->new Recipe(AXE_SIMPLE.get(), ITEM_REEDS.get(),STICK.get(),ITEM_PEBBLE.get()),"r_axe");


}
