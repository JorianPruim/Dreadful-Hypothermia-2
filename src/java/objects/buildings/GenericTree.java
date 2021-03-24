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
        if(Stream.of("jungle","forest","taiga","swamp").anyMatch(e-> e.equals(host.getName()))){
            return seedContract<250;
        }else if(Stream.of("savanna","tundra", "snow").anyMatch(e->e.equals(host.getName()))){
            return seedContract<2;
        }else if(host.getName().equals("plains")){
            return seedContract<6;
        }else{
            return false;
        }
    }


    @Override
    public String getAssetName() {
        //Whack.
        return this.getName() + this.host.getName();
    }

    @Override
    public void primaryInteract(Player p) {

        if(p.getInventory().getSelected() != null && p.getInventory().getSelected().hasType("axe")){
            p.getInventory().addToInv(Registers.WOOD.get(),5);
            this.destroy();
        }else{
            p.getInventory().addToInv(Registers.STICK.get());
            if(Math.random()<0.2)this.destroy();
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
