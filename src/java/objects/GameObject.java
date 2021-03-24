package objects;

import setup.register.RegistryObject;

public class GameObject extends RegistryObject {

    public GameObject(){
        super();
        this.addType("object");
    }

    //Look: i did flexibility thing
    public String getAssetName(){
        return name;
    }

    public String toString(){
        return name;
    }
}
