package setup.registration;

import setup.IMapObject;

public class RegisteredObject<RO extends IMapObject> {
    
    private RO obj;
    private int id;
    private String name;

    public RegisteredObject(RO obj, int id, String name){
        this.id = id;
        this.name = name;
        this.obj = obj;
    }

    public RO getProto(){
        return obj;
    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

}
