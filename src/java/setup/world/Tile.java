package setup.world;

public class Tile {

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
    private void build(Building b){
        if(this.building == null)this.building = b;
    }
    private void destroy(){
        this.building = null;
    }


}
