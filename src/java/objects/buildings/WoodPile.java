package objects.buildings;

import setup.player.Inventory;
import setup.player.Player;
import setup.register.Registers;
import setup.world.Building;

public class WoodPile extends Building {


    private final Inventory container = new Inventory(60,20);

    {//I finally managed to find the difference between initializers and constructors
        container.addToInv(Registers.WOOD.get());
    }

    @Override
    public void primaryInteract(Player p) {
        if(container.removeFromInv(Registers.WOOD.get())){
            p.getInventory().addToInv(Registers.WOOD.get());
            if(container.getLength()==0){
                this.destroy();
            }
        }
    }

    @Override
    public void secondaryInteract(Player p) {
        if (p.getInventory().removeFromInv(Registers.WOOD.get())) {
            if(!container.addToInv(Registers.WOOD.get())){
                p.getInventory().addToInv(Registers.WOOD.get());
            }
        }
    }

    @Override
    public boolean doCollide() {
        return true;
    }
}
