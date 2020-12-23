package setup.world;

import setup.register.RegistryObject;

public abstract class Building implements RegistryObject {

    public abstract void tick();

    public abstract void onPrimaryInteract(Player p);

    public abstract void onSecondaryInteract(Player p);
}
