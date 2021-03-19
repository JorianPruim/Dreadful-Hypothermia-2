package objects.items;

import setup.player.Player;
import setup.world.Tile;

public interface Placeable {
    void onPlace(Tile tile, Player player);
}
