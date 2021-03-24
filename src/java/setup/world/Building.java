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
        this.addType("building");
    }

    public void onPrimaryInteract(Player p){
        System.out.println(p.getXCoordinate() + " " +host.x);
        if(Math.pow(p.getXCoordinate()-host.x,2) + Math.pow(p.getYCoordinate()-host.y,2) < Math.pow(p.reach,2)){
            primaryInteract(p);
        }
    }

    public void onSecondaryInteract(Player p){
        if(Math.pow(p.getXCoordinate()-host.x,2) + Math.pow(p.getYCoordinate()-host.y,2) < Math.pow(p.reach,2)){
            secondaryInteract(p);
        }
    }

    public abstract void primaryInteract(Player p);

    public abstract void secondaryInteract(Player p);

    public abstract boolean doCollide();
}
