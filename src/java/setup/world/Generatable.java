package setup.world;

import setup.register.RegistryObject;


public interface Generatable extends RegistryObject {

    public boolean doesGenerate(Tile host);

}
