package objects.items;

import objects.GameObject;
import setup.register.RegistryObject;

public class Item extends GameObject {

    private final double size;
    private final double weight;
    private String name;

    public Item(double size, double weight, String name){
        super(name);
        this.size = size;
        this.weight = weight;
    }
    public Item(double size, double weight){
        this(size,weight,null);
    }

    public Item(){
        this(0,0,null);
    }

    public double getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }
}
