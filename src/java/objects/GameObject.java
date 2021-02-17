package objects;

import setup.register.RegistryObject;

public class GameObject implements RegistryObject {
    private String name;
    public GameObject(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
