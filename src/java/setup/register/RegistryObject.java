package setup.register;

import java.util.ArrayList;
import java.util.List;

public class RegistryObject {

    protected String name;
    protected List<String> types = new ArrayList<>();

    public void onRegister(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }
    public void addType(String type){
        types.add(type);
    }
    public boolean hasType(String c){
        return types.contains(c);
    }

    //plz no instance...
    protected RegistryObject(){}

    public boolean equals(RegistryObject obj) {
        return this.getName().equals(obj.getName());
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof RegistryObject && this.getName().equals(obj.toString());
    }
}
