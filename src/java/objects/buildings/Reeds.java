package objects.buildings;

import setup.player.Player;
import setup.register.Registers;
import setup.world.Tile;

public class Reeds extends GeneratableBuilding {
    @Override
    public void primaryInteract(Player p) {
        this.destroy();
        p.getInventory().addToInv(Registers.ITEM_REEDS.get());
    }

    @Override
    public void secondaryInteract(Player p) {

    }

    @Override
    public boolean doCollide() {
        return false;
    }

    @Override
    public boolean doesGenerate(Tile host, int seedContract) {
        return (!host.getName().equals("water")) && host.hasNeighbour("water") && seedContract < 30;
    }
}
