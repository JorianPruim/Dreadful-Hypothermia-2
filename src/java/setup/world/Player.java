package setup.world;

import fx.Main;

public class Player {
    public static double walkSpeed = 0.004; //needs to be set to the amount of tiles / *certain number*
    private static double xCoordinate = 0.5; //needs to be set to the players last xCoordinate when he quits the game
    private static double yCoordinate = 0.5; //needs to be set to the players last yCoordinate when he quits the game

    public static double getXCoordinate(){
        return xCoordinate;
    }

    public static double getYCoordinate(){
        return yCoordinate;
    }

    public static double moveUp() {
        if(yCoordinate - walkSpeed < 0){
            System.out.println("you reached the edge of this world");
            yCoordinate = 0.00;
        } else {
            yCoordinate -= walkSpeed;
        }
        return yCoordinate;
    }
    public static double moveLeft() {
        if(xCoordinate - walkSpeed < 0){
            System.out.println("you reached the edge of this world");
            xCoordinate = 0.00;
        } else {
            xCoordinate -= walkSpeed;
        }
        return xCoordinate;
    }
    public static double moveDown() {
        if(yCoordinate + walkSpeed > 1){
            System.out.println("you reached the edge of this world");
            yCoordinate = 0.00;
        } else {
            yCoordinate += walkSpeed;
        }
        return yCoordinate;
    }
    public static double moveRight() {
        if(xCoordinate + walkSpeed > 1){
            System.out.println("you reached the edge of this world");
            xCoordinate = 0.00;
        } else {
            xCoordinate += walkSpeed;
        }
        return xCoordinate;
    }
    //huge todo

    //+ addToInv
    //+ getSelectedItem
    //+ getInv
    //+ getHealth
    //+
}
