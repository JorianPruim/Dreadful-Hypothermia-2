package setup.register;

public class RegistryEntry<T> {


    private final T entry;
    private final int id;
    private final String name;

    public RegistryEntry(T entry, int id, String name){
        this.entry = entry;
        this.id = id;
        this.name = name;
    }

    public T get(){
        return entry;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}
