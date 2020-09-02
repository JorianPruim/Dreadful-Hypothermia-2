package setup.registration;

import java.util.ArrayList;
import java.util.List;

import setup.IMapObject;

public class Register<T extends IMapObject> {
    
    private final List<RegisteredObject<T>> data;
    private int idCounter = 0;

    public Register(){
        this.data = new ArrayList<RegisteredObject<T>>();
    }

    private int nextInt(){
        return idCounter++;
    }

    public T register(String name, T item){
        data.add(new RegisteredObject<T>(item, nextInt(), name));
        return item;
    }



    
}
