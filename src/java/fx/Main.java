package fx;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import setup.world.Player;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import setup.worldgen.Map;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;

public class Main extends Application {

    private final ScrollPane spane = new ScrollPane();
    private final Player dummy = new Player();

    @Override
    public void start(Stage primaryStage) throws Exception{


        //testing only
        int height = 1000;
        int width = 600;

        primaryStage.setTitle("DH2");



        World renderedWorld = World.generate(WorldGenSettings.getInstance());
        
        int imgsize = 16;

        GridPane pane = new GridPane();
        pane.getColumnConstraints().add(new ColumnConstraints(imgsize));
        pane.getRowConstraints().add(new RowConstraints(imgsize));

        ImageView[][] visualMap = new ImageView[250][250];
        for(int i = 0; i<visualMap.length; i++){
            for(int j = 0; j<visualMap.length; j++){
                String biome = renderedWorld.get(i,j).getName();
                try{
                    visualMap[i][j] = new ImageView(new Image("file:src/assets/"+biome+".png",imgsize,imgsize,true,true));
                }catch (Exception e){
                    e.printStackTrace();
                    visualMap[i][j] = new ImageView(new Image("file:src/assets/missing.png",imgsize,imgsize,true,true));
                }
                GridPane.setConstraints(visualMap[i][j], j, i);
                pane.getChildren().add(visualMap[i][j]);
            }
        }

        spane.setVvalue(dummy.getYCoordinate());
        spane.setHvalue(dummy.getXCoordinate());



//        GridPane.setConstraints(t, 0, 25);
//        pane.getChildren().add(t);

        spane.setContent(pane);
        Scene s = new Scene(spane, height, width);

        s.setCursor(new ImageCursor(new Image("file:src/assets/cursor.png")));
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
                dummy.moveUp();
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                break;
            case "a":
                dummy.moveLeft();
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                break;
            case "s":
                dummy.moveDown();
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                break;
            case "d":
                dummy.moveRight();
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                break;
            default:
                return;
        }
    }

    private void setWindow(double xCoordinate, double yCoordinate){
        spane.setHvalue(xCoordinate);
        spane.setVvalue(yCoordinate);
    }


    private void handleKeyRelease(String character){
        //todo
    }




    public static void main(String[] args) {


        launch(args);
    }


}
