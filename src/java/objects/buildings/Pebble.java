package objects.buildings;

import setup.player.Player;
import setup.register.Registers;
import setup.world.Tile;

import java.util.stream.Stream;

public class Pebble extends GeneratableBuilding {

    @Override
    public void onPrimaryInteract(Player p) {
        p.getInventory().addToInv(Registers.ITEMPEBBLE.get());
        if (Math.random() < 0.3){
            this.destroy();
        }
    }

    @Override
    public void onSecondaryInteract(Player p) {

    }

    @Override
    public boolean doCollide() {
        return false;
    }

    @Override
    public boolean doesGenerate(Tile host, int seedContract) {
        if(Stream.of("jungle","snow","swamp","tundra","missing").anyMatch(e->e.equals(host.getName()))){
            return false;
        }else{
            return seedContract < 5;
        }
    }

}
