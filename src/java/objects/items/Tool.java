package objects.items;

public abstract class Tool extends Item{
    public Tool(){
        super();
        this.addType("tool");
    }

    public Tool(double size, double weight) {
        super(size, weight);
        this.addType("tool");
    }
}
