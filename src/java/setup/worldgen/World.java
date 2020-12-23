package setup.worldgen;

import setup.world.Tile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class World {

    final int size;
    final Tile[][] tiles;
    Map heat;
    Map hum;


    private World(Tile[][] tiles){
        this.tiles = tiles; this.size = tiles.length;
        for(Tile[] tilerow : tiles){
            for(Tile tile : tilerow){
                //System.out.println(tile);
            }
        }
    }

    public static World generate(WorldGenSettings settings){
        Map heat = new Map(settings.size/8,settings.minHeatDropletSize,settings.maxHeatDropletSize,settings.heatDropletDensity);
        Map humidity = new Map(settings.size/8,settings.minHumidityDropletSize,settings.maxHumidityDropletSize,settings.humidityDropletDensity);
        Tile[][] tiles = new Tile[settings.size][settings.size];
        for (int i = 0; i < settings.size; i++) {
            for (int j = 0; j < settings.size; j++) {

                //TODO


            }
        }



        World out = new World(tiles);
        out.heat = heat;
        out.hum = humidity;
        return out;

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



    @Override
    public String toString() {
        return "{"+heat.toString()+","+hum.toString()+"}";
    }
}