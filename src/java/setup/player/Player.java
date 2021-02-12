package setup.player;

import setup.worldgen.World;

public class Player{

    //private int size() = 250;
    private World world;
    private double walkSpeed = 3;
    private double xCoordinate;
    private double yCoordinate;
    private Inventory inv;

    

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
        if (!(xCoordinate + delX > world.getSize()) && !(xCoordinate + delX < 0) && !(yCoordinate + delY > world.getSize()) && !(yCoordinate + delY < 0)) {
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






    //huge todo


    //+ getSelectedItem
    //+ getHealth
    //+
}
