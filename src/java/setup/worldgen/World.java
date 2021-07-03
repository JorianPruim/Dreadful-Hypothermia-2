package setup.worldgen;

import objects.ores.Ore;
import setup.player.Player;
import setup.register.Registers;
import setup.world.Tile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class World {

    private final int size;
    private final Tile[][] tiles;
    private Map heat;
    private Map hum;
    public final Player player;


    private World(Tile[][] tiles){
        this.tiles = tiles; this.size = tiles.length;
        for(Tile[] tilerow : tiles){
            for(Tile tile : tilerow){
                //System.out.println(tile);
            }
        }
        this.player = new Player();
        player.bind(this);
    }

    public static World generate(WorldGenSettings settings){
        Random s = new Random(settings.seed);
        Map heat = new Map(settings.size/8,settings.minHeatDropletSize,settings.maxHeatDropletSize,settings.heatDropletDensity,settings.seed);
        Map humidity = new Map(settings.size/8,settings.minHumidityDropletSize,settings.maxHumidityDropletSize,settings.humidityDropletDensity,settings.seed);
        Tile[][] tiles = new Tile[settings.size][settings.size];

        WorldGenSettings.OreMapSettings[] oreSettings = new WorldGenSettings.OreMapSettings[]{settings.copper};
        Map[] ores = Arrays.stream(oreSettings).map(e->new Map(e,settings.size,settings.seed)).toArray(Map[]::new);/*new Map[]{new Map(oreSettings[0], settings.size, settings.seed)};*/

        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {

                //Place tiles according to biome type

                double localHeat = 0, localHum = 0;
                for (int k = -settings.dropletBlur; k <= settings.dropletBlur; k++) {
                    for (int l = -settings.dropletBlur; l <= settings.dropletBlur; l++) {
                        double f = Math.pow(Math.max(Math.abs(k), Math.abs(l)) + 1, 2);
                        if((i+k>0&&j+l>0) && (i+k<settings.size && j+l<settings.size)) {
                            localHeat += heat.get((i + k)/8, (j + l)/8) / f;
                            localHum += humidity.get((i + k)/8, (j + l)/8) / f;

                        }

                    }
                }

                tiles[i][j] = Tile.from((int)localHeat,(int)localHum, settings.thresholds);


                //Now for the minerals the earth has to offer...
                for (int k = 0; k < ores.length; k++) {
                    if(ores[k].get(i,j)>0){
                        if(tiles[i][j].getSub() != null) {
                            tiles[i][j].getSub().add(new Ore(oreSettings[k].yield,getOreYield(s,oreSettings[k].minYield,oreSettings[k].maxYield)));
                        }
                    }
                }


                //TODO


            }
        }
        addDrip(s,tiles,settings);



        World out = new World(tiles);

        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {
                tiles[i][j].setCoords(i,j,out);
                setTileData(s, tiles, i, j);
            }
        }

        out.heat = heat;
        out.hum = humidity;
        return out;

    }

    private static int getOreYield(Random s, int min, int max){
        if(min>max)throw new IllegalArgumentException();
        return s.nextInt(max-min)+min;
    }
    private static void addDrip(Random s, Tile[][] tiles, WorldGenSettings settings) {
        Map lakefall = new Map(tiles.length, 20,50,1e-2,s.nextInt());
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                final String nome = tiles[i][j].getName();
                if(lakefall.getData()[i][j]>3 && Stream.of("desert","savanna","swamp").noneMatch(nome::equals)){
                    tiles[i][j] = Registers.WATER.get();
                }else if(lakefall.getData()[i][j]>2 && nome.equals("swamp")){
                    tiles[i][j] = Registers.WATER.get();
                }
            }
        }
    }

    private static void setTileData(Random s, Tile[][] tiles, int i, int j) {
        if(Registers.TREE.get().doesGenerate(tiles[i][j], s.nextInt(1000))){
            tiles[i][j].build(Registers.TREE.get());
        }
        if(Registers.PEBBLE.get().doesGenerate(tiles[i][j], s.nextInt(1000))){
            tiles[i][j].build(Registers.PEBBLE.get());
        }
        if(Registers.REEDS.get().doesGenerate(tiles[i][j], s.nextInt(100))){
            tiles[i][j].build(Registers.REEDS.get());
        }
    }

    public Tile get(int x, int y){
        try {
            return tiles[x][y];
        }catch(IndexOutOfBoundsException e){
            return Registers.WATER.get();
        }
    }





    public static World of(String in){
        //TODO
        return null;
    }

    public static World from(File file){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String s = reader.readLine();
            System.out.println(s);
            return World.of(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void export(String savename){
        Path file = null;
        String out = this.toString();
        byte[] bytes = out.getBytes();
        try{
            if(!Files.isDirectory(Path.of("src","saves"))){
                Files.createDirectory(Path.of("src","saves"));
            }
            file = Files.createFile(Paths.get("src","saves",savename));
        }catch (FileAlreadyExistsException e){
            e.printStackTrace();
            //todo: write fileoverwritewarnings and stuff
            System.err.println("File already exists");
        }catch (IOException e){
            e.printStackTrace();
            //TODO: write crashreports
            System.err.println("Something went wrong. Please send the crash report to trashcan@dh2.com");
        }
        if(file!=null){
            try(OutputStream outstream = new BufferedOutputStream(Files.newOutputStream(file,CREATE,APPEND))){
                outstream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.err.println("Empty path");
        }

    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "{"+heat.toString()+","+hum.toString()+"}";
    }
}
