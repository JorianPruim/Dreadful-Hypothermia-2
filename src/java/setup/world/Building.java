package setup.world;

import objects.GameObject;
import setup.player.Player;
import setup.register.RegistryObject;

public abstract class Building extends GameObject{

    protected Tile host;

    public void setHost(Tile host) {
        this.host = host;
    }

    public void destroy(){
        this.host.destroy();
    }

    public Building() {
        super();
    }

    public abstract void onPrimaryInteract(Player p);

    public abstract void onSecondaryInteract(Player p);

    public abstract boolean doCollide();
}
