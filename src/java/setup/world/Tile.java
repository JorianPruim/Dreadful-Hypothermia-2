package setup.world;

import objects.GameObject;
import setup.player.Player;
import setup.register.Registers;
import setup.register.RegistryObject;
import setup.worldgen.WorldGenSettings;

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
    //private int heat;//one implementation possibility
    //private int hum;



    public void onPrimaryInteract(Player p){
        if(this.building!=null){
            this.building.onPrimaryInteract(p);
        }
        return;
    }
    public void onSecondaryInteract(Player p){
        if(this.building!=null){
            this.building.onSecondaryInteract(p);
        }
        return;
    }
    public void build(Building b){
        if(this.building == null)this.building = b;
    }
    public void destroy(){
        this.building = null;
    }

    public Building getBuilding() {
        return building;
    }
}
