package util;

import javafx.scene.image.Image;

public class ImgFinder {

    public static Image get(String location, String path, int size){
        Image img = new Image("file:src/assets/"+path+"/"+location+".png", size, size, true, true);
        if(img.isError()){
            img = new Image("file:src/assets/"+path+"/missing.png", size, size, true, true);
            if(img.isError()){
                img = new Image("file:src/assets/tiles/missing.png", size, size, true, true);
            }
        }
        return img;
    }

    public static Image get(String location, int size){
        Image img = new Image("file:src/assets/"+location+".png", size, size, true, true);
        if(img.isError()){
            img = new Image("file:src/assets/tiles/missing.png", size, size, true, true);
        }
        return img;
    }

}
