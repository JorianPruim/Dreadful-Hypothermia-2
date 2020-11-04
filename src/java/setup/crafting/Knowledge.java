package setup.crafting;

public class Knowledge {

    public static Knowledge ROOT = new Knowledge(); //Base of all science
    public Knowledge parent;

    public Knowledge(){
        this(ROOT);
    }
    public Knowledge(Knowledge parent){
        this.parent = parent;
    }

}
