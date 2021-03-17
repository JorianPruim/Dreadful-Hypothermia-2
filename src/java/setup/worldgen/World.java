package setup.worldgen;

import setup.player.Player;
import setup.register.Registers;
import setup.world.Tile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

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
        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {

                //Place tiles according to biome type
                tiles[i][j] = Tile.from(heat.get((int)i/8,(int)j/8),humidity.get(i/8,j/8), settings.thresholds);
                tiles[i][j].setCoords(i,j);
                if(Registers.TREE.get().doesGenerate(tiles[i][j],s.nextInt(1000))){
                    tiles[i][j].build(Registers.TREE.get());
                }
                if(Registers.PEBBLE.get().doesGenerate(tiles[i][j],s.nextInt(1000))){
                    tiles[i][j].build(Registers.PEBBLE.get());
                }
                //TODO


            }
        }



        World out = new World(tiles);
        out.heat = heat;
        out.hum = humidity;
        return out;

    }

    public Tile get(int x, int y){
        return tiles[x][y];
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
