package setup.world;

import objects.GameObject;
import objects.items.Placeable;
import setup.player.Player;
import setup.register.Registers;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;

import java.util.Arrays;
import java.util.stream.Stream;

public class Tile extends GameObject {

    public static Tile from(int heat, int hum, WorldGenSettings.Thresholds thresholds){
        if(heat < thresholds.minMediocreHeatThreshold){
            return hum < thresholds.minMediocreHumidityThreshold ? Registers.TUNDRA.get() : hum > thresholds.maxMediocreHumidityThreshold ? Registers.SNOW.get() : Registers.TAIGA.get();
        }else if(heat > thresholds.maxMediocreHeatThreshold){
            return hum < thresholds.minMediocreHumidityThreshold ? Registers.DESERT.get() : hum > thresholds.maxMediocreHumidityThreshold ? Registers.JUNGLE.get() : Registers.SAVANNA.get();
        }else{
            return hum < thresholds.minMediocreHumidityThreshold ? Registers.PLAINS.get() : hum > thresholds.maxMediocreHumidityThreshold ? Registers.SWAMP.get() : Registers.FOREST.get();
        }
    }


    public Tile(){
        super();
    }

    private boolean isVisible;
    private Building building;
    private Subterrain sub;
    public int x, y;
    private World host;


    public void setCoords(int x, int y, World willItRepresentAHostQuestionmarkIThinkItRepresentsTheHostPeriod){
        this.x = x; this.y = y; this.host = willItRepresentAHostQuestionmarkIThinkItRepresentsTheHostPeriod;
    }


    public void onPrimaryInteract(Player p){
        if(this.building!=null){
            this.building.onPrimaryInteract(p);
        }
        return;
    }
    public void onSecondaryInteract(Player p){
        if(this.building!=null){
            this.building.onSecondaryInteract(p);
        }else if(p.getInventory().getSelected()!=null && p.getInventory().getSelected() instanceof Placeable){
            ((Placeable) p.getInventory().getSelected()).onPlace(this,p);
        }
        return;
    }
    public boolean build(Building b){
        if(this.building == null){
            this.building = b;
            b.setHost(this);
            return true;
        }
        return false;
    }

    public boolean hasNeighbour(String tile){
        return Arrays.asList(host.get(this.x + 1, this.y).getName(), host.get(this.x - 1, this.y).getName(), host.get(this.x, this.y + 1).getName(), host.get(this.x, this.y - 1).getName()).contains(tile);
    }

    public void destroy(){
        this.building = null;
    }

    public Building getBuilding() {
        return building;
    }
    public Subterrain getSub(){return sub;}
    public Entity getOccupant(){
        return null;
    }
}
