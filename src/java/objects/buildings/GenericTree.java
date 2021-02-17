package objects.buildings;

import setup.player.Player;

public class GenericTree extends GeneratableBuilding {
    public GenericTree(String name) {
        super(name);
    }

    @Override
    public void tick() {
        return;
    }

    @Override
    public void onPrimaryInteract(Player p) {

    }

    @Override
    public void onSecondaryInteract(Player p) {

    }


}
