package world;


public class Position {

    private final int x;
    private final int z;
    //-World owner?

    public Position(int x, int z){
        this.x = x;
        this.z = z;
    }

    public int getX(){
        return x;
    }
    public int getZ(){
        return z;
    }
}
