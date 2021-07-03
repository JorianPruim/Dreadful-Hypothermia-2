package util;

import javafx.scene.image.Image;

import java.util.HashMap;

public class ImgFinder {

    private static final HashMap<String, HashMap<String, Image>> cache = new HashMap<>();
    static{
        cache.put("ROOT",new HashMap<>());
    }

    public static Image get(String location, String path, int size){
        if(cache.containsKey(path) && cache.get(path).containsKey(location)){
            return cache.get(path).get(location);
        }else{
            if(!cache.containsKey(path)){
                cache.put(path,new HashMap<>());
            }
            Image img = new Image("file:src/assets/"+path+"/"+location+".png", size, size, true, true);
            if(img.isError()){
                img = new Image("file:src/assets/"+path+"/missing.png", size, size, true, true);
                if(img.isError()){
                    img = new Image("file:src/assets/tiles/missing.png", size, size, true, true);
                }
            }
            cache.get(path).put(location, img);
            return img;

        }
    }

    public static Image get(String location, int size){
        if(cache.containsKey("ROOT") && cache.get("ROOT").containsKey(location)){
            return cache.get("ROOT").get(location);
        }else{
            Image img = new Image("file:src/assets/"+location+".png", size, size, true, true);
            if(img.isError()){
                img = new Image("file:src/assets/tiles/missing.png", size, size, true, true);
            }
            return img;
        }
    }

}
