import fx.Main;
import setup.crafting.Knowledge;
import setup.register.Registers;
import setup.worldgen.Map;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;

import java.io.File;

public class DH2 {
  public static void main(String[] args) {

    World w = World.generate(WorldGenSettings.getInstance());
    w.export("test123");



  }

}
