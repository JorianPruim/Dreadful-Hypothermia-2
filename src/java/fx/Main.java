package fx;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import setup.world.Player;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import setup.worldgen.Map;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;

public class Main extends Application {
    private final int size = 250;
    private final ScrollPane spane = new ScrollPane();
    private final GridPane pane = new GridPane();
    private final Player dummy = new Player();
    private final ImageView[][] visualMap = new ImageView[size][size];
    private final int renderDistance = 20;
    private final int imgsize = 32;
    private final World renderedWorld = World.generate(WorldGenSettings.getInstance());
    @Override
    public void start(Stage primaryStage) throws Exception{


        //testing only
        int height = 1000;
        int width = 600;

        primaryStage.setTitle("DH2");


        pane.getColumnConstraints().add(new ColumnConstraints(imgsize));
        pane.getRowConstraints().add(new RowConstraints(imgsize));



        spane.setVvalue(0.5);
        spane.setHvalue(0.5);

        pane.setPrefSize(imgsize*size, imgsize*size);

        for (int i = 0; i<size; i++){
            visualMap[i][i] = getAsset(i, i);
            GridPane.setConstraints(visualMap[i][i], i, i);
            pane.getChildren().add(visualMap[i][i]);
        }

        for(int i = 0; i<renderDistance+1; i++) {
            for (int j = 0; j <renderDistance+1; j++) {
                visualMap[size/2 - renderDistance/2 + i][size/2 - renderDistance/2 + j] = getAsset(i, j);
                GridPane.setConstraints(visualMap[size/2 - renderDistance/2 + i][size/2 - renderDistance/2 + j], size/2 - renderDistance/2 + i, size/2 - renderDistance/2 + j);
                pane.getChildren().add(visualMap[size/2 - renderDistance/2 + i][size/2 - renderDistance/2 + j]);
            }
        }


        spane.setVvalue(dummy.getYCoordinate());
        spane.setHvalue(dummy.getXCoordinate());



//        GridPane.setConstraints(t, 0, 25);
//        pane.getChildren().add(t);

        spane.setContent(pane);
        Scene s = new Scene(spane, height, width);

        s.setCursor(new ImageCursor(new Image("file:src/assets/missing.png")));
        s.setOnMouseClicked(e->renderDown(dummy.getXCoordinate(), dummy.getYCoordinate()));
        s.setOnKeyPressed(e->handleKeyPress(e.getText(),e.isShiftDown(),e.isControlDown(),e.isAltDown()));
        s.setOnKeyReleased(e->handleKeyRelease(e.getText()));

        spane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        spane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        primaryStage.setScene(s);
        primaryStage.getIcons().add(new Image("file:src/assets/missing.png"));
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(false);
        primaryStage.show();




    }

    //TODO: extract to controller class?
    private void handleKeyPress(String character, boolean shift, boolean ctrl, boolean alt) {
        switch(character){
            case "w":
                dummy.moveIn((byte) 2);
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                break;
            case "a":
                dummy.moveIn((byte) 4);
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                break;
            case "s":
                dummy.moveIn((byte) 1);
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                renderDown(dummy.getXCoordinate(), dummy.getYCoordinate());
                break;
            case "d":
                dummy.moveIn((byte) 8);
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                break;
            default:
                return;
        }
    }

    private void setWindow(double xCoordinate, double yCoordinate){
        System.out.println(xCoordinate + " " + yCoordinate);
        spane.setHvalue(xCoordinate/size);
        spane.setVvalue(yCoordinate/size);
    }

    private void renderDown(double xCoordinate, double yCoordinate){
        System.out.println((int)xCoordinate);
        int x = (int) xCoordinate-renderDistance/2;
        int y = (int) yCoordinate+renderDistance/2;
        for(int i = 0; i<renderDistance+1; i++){
            visualMap[x+i][y] = getAsset(x+i, y);
            GridPane.setConstraints(visualMap[x+i][y], x+i, y);
            pane.getChildren().add(visualMap[x+i][y]);
        }

    }

    private ImageView getAsset(int x, int y){
        String biome = renderedWorld.get((int) (dummy.getXCoordinate()) - renderDistance + x, (int) (dummy.getYCoordinate()) - renderDistance + y).getName();
        try {
            return new ImageView(new Image("file:src/assets/" + biome + ".png", imgsize, imgsize, true, true));
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageView(new Image("file:src/assets/missing.png", imgsize, imgsize, true, true));
        }
    }

    private void handleKeyRelease(String character){
        //todo
    }




    public static void main(String[] args) {


        launch(args);
    }


}
