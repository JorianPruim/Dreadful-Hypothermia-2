package objects.buildings;

import setup.world.Building;
import setup.world.Generatable;
import setup.world.Tile;

public abstract class GeneratableBuilding extends Building implements Generatable {


    @Override
    public boolean doesGenerate(Tile host) {
        return false;
    }


}
