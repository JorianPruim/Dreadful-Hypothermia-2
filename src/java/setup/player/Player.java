package setup.player;

import setup.crafting.Knowledge;
import setup.worldgen.World;

import java.util.ArrayList;
import java.util.List;

public class Player{

    //private int size = 250;
    private World world;
    private double walkSpeed = 0.5;
    private double xCoordinate;
    private double yCoordinate;
    private final Inventory inv;

    public double HP = 0; // The player has no life. The game was rigged from the start ect...
    public double health = 100;
    public double reach = 3;

    public List<Knowledge> k = new ArrayList<>();

    public Player(){
        this.inv = new Inventory(10,10);
        this.k.add(Knowledge.ROOT);
    }

    public void bind(World world){
        this.world = world;
        this.xCoordinate = this.yCoordinate = ((double)world.getSize())/2;
    }
    public void bind(World world, double x, double y){
        this.world = world;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }

    public void move(double delX, double delY){
        if (!(xCoordinate + delX > world.getSize()) && !(xCoordinate + delX < 0) && !(yCoordinate + delY > world.getSize()) && !(yCoordinate + delY < 0) && (world.get((int)Math.floor(xCoordinate+delX),(int)Math.floor(yCoordinate+delY)).getBuilding()==null) || !world.get((int)Math.floor(xCoordinate+delX),(int)Math.floor(yCoordinate+delY)).getBuilding().doCollide()) {
            xCoordinate+=delX;
            yCoordinate+=delY;
        }
    }

    public void moveIn(byte direction){
        move(walkSpeed * (direction & 0b0001), walkSpeed * (direction & 0b0010)/2);
        move(-walkSpeed * (direction & 0b0100)/4, -walkSpeed * (direction & 0b1000)/8);
    }

    public void updateWalkSpeed(double factor){
        walkSpeed*=factor;
    }

    public Inventory getInventory(){
        return inv;
    }

    public boolean knows(Knowledge kn){
        return !(k.stream().filter(e->e.getName().equals(kn.getName())).toArray().length==0);
    }





    //huge todo


    //+ getSelectedItem
    //+ getHealth
    //+
}
