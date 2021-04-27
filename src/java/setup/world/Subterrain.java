package setup.world;

import java.util.ArrayList;
import java.util.List;

public class Subterrain{

    public List<ISubType> assets;

    public Subterrain(){
        this.assets = new ArrayList<>();
    }

    public void add(ISubType e){
        assets.add(e);
    }

}
