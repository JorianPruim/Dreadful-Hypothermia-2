package setup.world;

import fx.Main;

public class Player {
    public double walkSpeed = 0.004; //needs to be set to the amount of tiles / *certain number*
    private double xCoordinate = 0.5; //needs to be set to the players last xCoordinate when he quits the game
    private double yCoordinate = 0.5; //needs to be set to the players last yCoordinate when he quits the game

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }

    public void moveUp() {
        if(yCoordinate - walkSpeed < 0){
            System.out.println("you reached the edge of this world");
            yCoordinate = 0.00;
        } else {
            yCoordinate -= walkSpeed;
        }
    }
    public void moveLeft() {
        if(xCoordinate - walkSpeed < 0){
            System.out.println("you reached the edge of this world");
            xCoordinate = 0.00;
        } else {
            xCoordinate -= walkSpeed;
        }
    }
    public void moveDown() {
        if(yCoordinate + walkSpeed > 1){
            System.out.println("you reached the edge of this world");
            yCoordinate = 0.00;
        } else {
            yCoordinate += walkSpeed;
        }
    }
    public void moveRight() {
        if(xCoordinate + walkSpeed > 1){
            System.out.println("you reached the edge of this world");
            xCoordinate = 0.00;
        } else {
            xCoordinate += walkSpeed;
        }
    }
    //huge todo

    //+ addToInv
    //+ getSelectedItem
    //+ getInv
    //+ getHealth
    //+
}
