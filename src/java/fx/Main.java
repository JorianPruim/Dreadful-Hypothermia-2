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

    private ScrollPane spane = new ScrollPane();


    @Override
    public void start(Stage primaryStage) throws Exception{


        Map b = new Map(1000,15,25,0.003);
        Text t = new Text(b.toVisualString());

        //testing only
        int height = 1000;
        int width = 600;

        primaryStage.setTitle("DH2");


        GridPane pane = new GridPane();
        pane.getColumnConstraints().add(new ColumnConstraints(16));
        pane.getRowConstraints().add(new RowConstraints(16));

        ImageView[][] visualMap = new ImageView[250][250];
        for(int i = 0; i<visualMap.length; i++){
            for(int j = 0; j<visualMap.length; j++){
                int rand = (int) (Math.random() * 9);
                switch (rand) {
                    case 0 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/desert.png",16,16,true,true));
                    case 1 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/forest.png",16,16,true,true));
                    case 2 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/jungle.png",16,16,true,true));
                    case 3 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/plain.png",16,16,true,true));
                    case 4 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/snow.png",16,16,true,true));
                    case 5 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/swamp.png",16,16,true,true));
                    case 6 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/tundra.png",16,16,true,true));
                    case 7 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/taiga.png",16,16,true,true));
                    case 8 -> visualMap[i][j] = new ImageView(new Image("file:src/assets/savanna.png",16,16,true,true));
                    default -> visualMap[i][j] = new ImageView(new Image("file:src/assets/missing.png",16,16,true,true));
                }
                GridPane.setConstraints(visualMap[i][j], j, i);
                pane.getChildren().add(visualMap[i][j]);
            }
        }

        spane.setVvalue(Player.getYCoordinate());
        spane.setHvalue(Player.getXCoordinate());



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
                setWindow(Player.getXCoordinate(), Player.moveUp());
                break;
            case "a":
                setWindow(Player.moveLeft(), Player.getYCoordinate());
                break;
            case "s":
                setWindow(Player.getXCoordinate(), Player.moveDown());
                break;
            case "d":
                setWindow(Player.moveRight(), Player.getYCoordinate());
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
