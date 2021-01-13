package setup.register;

import java.util.function.Supplier;

public class RegistryEntry<T> {


    private final Supplier<T> entrySupplier;
    private final int id;
    private final String name;

    public RegistryEntry(Supplier<T> entry, int id, String name){
        this.entrySupplier = entry;
        this.id = id;
        this.name = name;
    }

    public T get(){
        return entrySupplier.get();
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}
