package sample.worldgen;

import worldgen.Map;
import worldgen.WorldGenSettings;
import world.Tile;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
            }
        }
    }

    private static World generate(WorldGenSettings settings){
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
        int times = 0;
        int toPower = 0;
        for(int i = 0; i < in.length(); i++){
            if (Character.isDigit(in.charAt(i))) {
                times = (int)(Math.pow(10, toPower)*times) + (in.charAt(i)-48);
                toPower++;
            } else if (Character.isLetter(in.charAt(i))){
                if (times == 0) //put the right int (in.charAt(i) - 78) on the spot;
                for(int j = 0; j < times; j++){
                    //in.charAt(i) - 78
                }
                times = 0;
                toPower = 0;
            }
        }
        return null;
    }

    public static void export(String savename){
        Path file = null;
        String out = "Test 123";
        byte[] bytes = out.getBytes();
        try{
            file = Files.createFile(Paths.get("assets/"+savename+".txt"));
        }catch (FileAlreadyExistsException e){
            e.printStackTrace();
            //todo: write fileoverwritewarnings and stuff
            System.err.println("File already exists");
        }catch (IOException e){
            e.printStackTrace();
            //TODO: write crashreports
            System.err.println("Something went wrong. Please send the crash report to trashcan@gamewithnoname.com");
        }
        if(file!=null){
            try(OutputStream outstream = new BufferedOutputStream(Files.newOutputStream(file,CREATE,APPEND))){
                outstream.write(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Empty path");
        }

    }


}
