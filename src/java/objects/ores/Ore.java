package objects.ores;

import objects.GameObject;
import objects.items.Item;
import setup.world.ISubType;

public class Ore extends GameObject implements ISubType {
    Item yield;
    int size;
    public Ore(Item yield, int veinSize){
        this.yield = yield;
        this.size = veinSize;
    }

    public Ore(){

    }
}
