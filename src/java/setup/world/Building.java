package setup.world;

import objects.GameObject;
import setup.player.Player;
import setup.register.RegistryObject;

public abstract class Building extends GameObject {

    public Building(String name) {
        super(name);
    }

    public abstract void tick();

    public abstract void onPrimaryInteract(Player p);

    public abstract void onSecondaryInteract(Player p);
}
