import util.NotImplementedException;
import worldgen.Map;
import worldgen.World;

public class InformaticaGame {
  public static void main(String[] args) {
    Map humidity = new Map(125,10,30,1e-2);
    Map heat = new Map(125,10,30,1e-2);

    String heatData = world.toString.getMapData(heat.toIntegerDeepArray());
    String humidityData = world.toString.getMapData(humidity.toIntegerDeepArray());

    System.out.println(heatData);
    System.out.println(humidityData);

  }

}
