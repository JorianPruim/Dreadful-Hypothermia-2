package setup.world;

public class Item {

    private final double size;
    private final double weight;

    public Item(double size, double weight){
        this.size = size;
        this.weight = weight;
    }

    public Item(){
        this(0,0);
    }

    public double getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }




}
