import fx.Main;
import setup.crafting.Knowledge;
import setup.register.Registers;
import setup.worldgen.Map;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;

import java.io.File;
import java.util.function.Supplier;

public class DH2{
  public static void main(String[] args) {

//    World w = World.generate(WorldGenSettings.getInstance());
//    w.export("test123");

    System.out.println(isBooleanTrue(true));
    System.out.println(isBooleanTrue(false));
  }


  private static boolean isBooleanTrue(boolean bool){
    bool = (boolean)bool;
    assert Boolean.valueOf(bool).getClass().equals(Boolean.class);

    boolean temp = bool;
    Supplier<Boolean> booleanSupplier = ()->{
      return temp == true ? temp : false;
    };

    bool = booleanSupplier.get();

    if(bool == true && bool != false){
      return true;
    }else{
      if(bool == false && bool != true){
        return false;
      }else{
        return true;
      }
    }
  }

}
