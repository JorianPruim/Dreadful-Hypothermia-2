package setup.register;

import objects.buildings.GenericTree;
import setup.crafting.Knowledge;
import setup.world.Generatable;
import setup.world.Player;
import setup.world.Tile;

public class Registers {
    public static Registry<Generatable> GEN = new Registry<Generatable>();
    public static Registry<Knowledge> KWL = new Registry<Knowledge>();

    public static void init(){

        //entries go here..
        //public static final RegistryEntry<ItemWood> WOODITEM = ITM.register(ItemWood::new,"wood");
    }

}
