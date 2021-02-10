package setup.player;

public class Player {

    private int size = 250;//TODO Replace with World and all references by World.getSize or related
    private double walkSpeed = 3;
    private double xCoordinate = (double)size/2;
    private double yCoordinate = (double)size/2;
    private Inventory inv;

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

    public Inventory getInventory(){
        return inv;
    }






    //huge todo


    //+ getSelectedItem
    //+ getHealth
    //+
}
