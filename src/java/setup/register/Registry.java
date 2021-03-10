package setup.register;


import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class Registry<T extends RegistryObject> extends ArrayList<RegistryEntry<T>> {

    int current = 0;

    public int getNext(){
        return current++;
    }

    public <R extends T> RegistryEntry<R> register(Supplier<R> provider, String name){
        System.out.println("Registered "+name+" under id " + current);
        RegistryEntry<R> entry = new RegistryEntry<>(provider,getNext(),name);
        this.add((RegistryEntry<T>) entry);
        return entry;
    }

    public RegistryEntry<T> getById(int id){
        for(RegistryEntry<T> e: this){
            if(e.getId()==id){
                return e;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public RegistryEntry<T> getByName(String name){
        for(RegistryEntry<T> e : this){
            if(e.getName().equals(name)){
                return e;
            }
        }
        throw new IllegalArgumentException();
    }

    public void forEachObject(Consumer<? super T> consumer){
        forEach(e->consumer.accept(e.get()));
    }



}
