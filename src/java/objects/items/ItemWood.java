package objects.items;

import setup.player.Player;
import setup.register.Registers;
import setup.world.Tile;

public class ItemWood extends Item implements Placeable{
    public ItemWood(){
        super(3,3);
    }

    @Override
    public void onPlace(Tile tile, Player player) {
        if(tile.build(Registers.WOODPILE.get())){
            player.getInventory().removeFromInv(Registers.WOOD.get());
        }
    }
}
