package objects.items;

import objects.GameObject;
import setup.register.RegistryObject;

public class Item extends GameObject {

    private final double size;
    private final double weight;

    public Item(double size, double weight){
        super();
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

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Item && ((Item) obj).getName().equals(this.getName());
    }
}
