package setup.register;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Registry<T extends RegistryObject> extends ArrayList<RegistryEntry<T>> {

    int current = 0;

    public int getNext(){
        return current++;
    }

    public RegistryEntry<T> register(T t, String name){
        RegistryEntry<T> entry = new RegistryEntry<>(t,getNext(),name);
        this.add(entry);
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
