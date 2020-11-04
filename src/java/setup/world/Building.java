package setup.world;

public abstract class Building {

    public abstract void tick();

    public abstract void onPrimaryInteract(Player p);

    public abstract void onSecondaryInteract(Player p);
}
