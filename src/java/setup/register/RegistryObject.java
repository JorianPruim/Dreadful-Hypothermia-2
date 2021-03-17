package setup.register;

public class RegistryObject {

    protected String name;
    public void onRegister(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }

    //plz no instance...
    protected RegistryObject(){}

}
