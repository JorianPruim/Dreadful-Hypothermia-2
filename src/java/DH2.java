import setup.crafting.Knowledge;
import setup.worldgen.Map;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;

import java.io.File;

public class DH2 {
  public static void main(String[] args) {

    World newWorld = World.generate(WorldGenSettings.getInstance());
    newWorld.export("test");
    World world = World.from(new File("src/assets/test"));


  }

}
