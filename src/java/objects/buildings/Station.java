package objects.buildings;

import setup.player.Player;
import setup.world.Building;

public class Station extends Building {
    @Override
    public void primaryInteract(Player p) {

    }

    @Override
    public void secondaryInteract(Player p) {

    }

    @Override
    public boolean doCollide() {
        return true;
    }
}
