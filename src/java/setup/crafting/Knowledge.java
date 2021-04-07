package setup.crafting;

import setup.register.RegistryObject;

public class Knowledge extends RegistryObject {

    public static Knowledge ROOT = new Knowledge(); //Base of all science
    public Knowledge parent;
    public String description;
    public int id;
    public String name;

    public Knowledge(){
        this(ROOT,"root");
    }
    public Knowledge(Knowledge parent, String name){
        this.parent = parent;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }






}
