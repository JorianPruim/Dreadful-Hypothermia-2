package world;

import setup.IMapObject;

public class Tile implements IMapObject{

    private int id;
    private Position pos;


    public Position getPos(){
        return pos;
    }

    @Override
    public int getID() {
        return id;
    }

    

}
