package setup.world;

import objects.GameObject;
import setup.player.Player;
import setup.register.RegistryObject;

public abstract class Building extends GameObject {

    public Building() {
        super();
    }

    public abstract void tick();

    public abstract void onPrimaryInteract(Player p);

    public abstract void onSecondaryInteract(Player p);

    public abstract boolean doCollide();
}
