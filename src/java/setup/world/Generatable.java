package setup.world;

import setup.register.RegistryObject;


public interface Generatable extends RegistryObject {

    boolean doesGenerate(Tile host);

}
