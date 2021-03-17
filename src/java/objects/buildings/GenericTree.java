package objects.buildings;

import setup.player.Player;
import setup.register.Registers;
import setup.world.Tile;

import java.util.stream.Stream;

public class GenericTree extends GeneratableBuilding {
    public GenericTree() {
        super();
    }

    @Override
    public boolean doesGenerate(Tile host,int seedContract) {
        if(Stream.of("jungle","forest","taiga").anyMatch(e-> e.equals(host.getName()))){
            return seedContract<250;
        }else if(Stream.of("plains","savanna","tundra","swamp").anyMatch(e->e.equals(host.getName()))){
            return seedContract<2;
        }else{
            return false;
        }
    }


    @Override
    public void primaryInteract(Player p) {

        if(p.getInventory().addToInv(Registers.WOOD.get(),5) != 5){
            this.destroy();
        }
    }

    @Override
    public void secondaryInteract(Player p) {

    }

    @Override
    public boolean doCollide() {
        return false;
    }


}
