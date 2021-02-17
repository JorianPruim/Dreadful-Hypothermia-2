package setup.register;

import java.util.function.Supplier;

public class RegistryEntry<T extends RegistryObject> {


    private final Supplier<T> entrySupplier;
    private final int id;
    private final String name;

    public RegistryEntry(Supplier<T> entry, int id, String name){
        this.entrySupplier = entry;
        this.id = id;
        this.name = name;
    }

    public T get(){
        T out = entrySupplier.get();
        out.onRegister(name);
        return out;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}
