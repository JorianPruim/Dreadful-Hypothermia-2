package setup.world;

import setup.register.RegistryObject;


public interface Generatable {

    boolean doesGenerate(Tile host,int seed);

}
