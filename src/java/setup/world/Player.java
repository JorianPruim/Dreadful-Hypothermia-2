package setup.world;

import fx.Main;
import util.Direction;

public class Player {

    private double walkSpeed;
    private double xCoordinate = 0.5;
    private double yCoordinate = 0.5;
    //public Inventory inv;

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }

    public void move(double delX, double delY){
        if (!(xCoordinate + delX > 1) && !(xCoordinate + delX < 0) && !(yCoordinate + delY > 1) && !(yCoordinate + delY < 0)) {
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
