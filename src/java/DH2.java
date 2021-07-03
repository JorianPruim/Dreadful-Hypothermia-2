import fx.Main;
import objects.items.Item;
import setup.player.Player;
import setup.register.Registers;
import setup.world.Tile;
import setup.worldgen.Map;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Predicate;

public class DH2 {
  public static void main(String[] args) {

//    World w = World.generate(WorldGenSettings.getInstance());
//    w.export("test123");
      Map m = new Map(1000,5,10,5e-2,0);
    System.out.println(Arrays.deepToString(m.getData()));

  }

  private DH2(){}
}