package setup.world;

import fx.Main;
import util.Direction;

public class Player {

    private int size = 250;
    private double walkSpeed = 1;
    private double xCoordinate = size/2;
    private double yCoordinate = size/2;
    //public Inventory inv;

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }

    public void move(double delX, double delY){
        if (!(xCoordinate + delX > size) && !(xCoordinate + delX < 0) && !(yCoordinate + delY > size) && !(yCoordinate + delY < 0)) {
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








    //huge todo

    //+ addToInv
    //+ getSelectedItem
    //+ getInv
    //+ getHealth
    //+
}
