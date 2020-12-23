package setup.crafting;

import setup.register.RegistryObject;

public class Knowledge implements RegistryObject {

    public static Knowledge ROOT = new Knowledge(); //Base of all science
    public Knowledge parent;
    public String description;
    public int id;

    public Knowledge(){
        this(ROOT);
    }
    public Knowledge(Knowledge parent){
        this.parent = parent;
    }


}
