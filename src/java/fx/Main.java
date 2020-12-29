package fx;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import setup.world.Player;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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

        primaryStage.setTitle("Hello World");


        GridPane pane = new GridPane();
        pane.getColumnConstraints().add(new ColumnConstraints(40));
        pane.getRowConstraints().add(new RowConstraints(40));

        Text[][] visualMap = new Text[100][100];
        for(int i = 0; i<visualMap.length; i++){
            for(int j = 0; j<visualMap.length; j++){
                visualMap[i][j] = new Text("" + i + j);
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

        s.setCursor(new ImageCursor(new Image("file:src/assets/missing.png")));
        s.setOnKeyPressed(e->handleKeyPress(e.getText(),e.isShiftDown(),e.isControlDown(),e.isAltDown()));
        s.setOnKeyReleased(e->handleKeyRelease(e.getText()));

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
