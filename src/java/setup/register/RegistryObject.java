package setup.register;

public class RegistryObject {

    private String name;
    public void onRegister(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }

    //plz no instance...
    protected RegistryObject(){}

}
