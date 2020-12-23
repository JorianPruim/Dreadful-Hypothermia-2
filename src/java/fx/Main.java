package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import setup.worldgen.Map;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;

public class Main extends Application {

    private ScrollPane spane = new ScrollPane();
    private double xCoordinate;
    private double yCoordinate;

    @Override
    public void start(Stage primaryStage) throws Exception{


        Map b = new Map(1000,15,25,0.003);
        Text t = new Text(b.toVisualString());

        //testing only
        int height = 1000;
        int width = 600;
        xCoordinate = 0.5;
        yCoordinate = 0.5;
        primaryStage.setTitle("Hello World");


        GridPane pane = new GridPane();

        Text[][] visualMap = new Text[100][100];
        for(int i = 0; i<visualMap.length; i++){
            for(int j = 0; j<visualMap.length; j++){
                visualMap[i][j] = new Text("" + i + j);
                GridPane.setConstraints(visualMap[i][j], j, i);
                pane.getChildren().add(visualMap[i][j]);
                pane.setHgap(10);
                pane.setVgap(10);
            }
        }

        spane.setVvalue(0.5);
        spane.setHvalue(0.5);


//        GridPane.setConstraints(t, 0, 25);
//        pane.getChildren().add(t);

        spane.setContent(pane);
        Scene s = new Scene(spane, height, width);

        s.setOnKeyPressed(e->handleKeyPress(e.getText(),e.isShiftDown(),e.isControlDown(),e.isAltDown()));
        s.setOnKeyReleased(e->handleKeyRelease(e.getText()));

        primaryStage.setScene(s);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(false);
        primaryStage.show();



    }


    private void handleKeyPress(String character, boolean shift, boolean ctrl, boolean alt) {
        switch(character){
            case "w":
                moveUp();
                break;
            case "a":
                moveLeft();
                break;
            case "s":
                moveDown();
                break;
            case "d":
                moveRight();
                break;
            default:
                break;
        }
    }

    private void moveUp() {
        yCoordinate -= 0.002;
        spane.setVvalue(yCoordinate);
    }
    private void moveLeft() {
        xCoordinate -= 0.002;
        spane.setHvalue(xCoordinate);
    }
    private void moveDown() {
        yCoordinate += 0.002;
        spane.setVvalue(yCoordinate);
    }
    private void moveRight() {
        xCoordinate += 0.002;
        spane.setHvalue(xCoordinate);
    }
    private void handleKeyRelease(String character){
        //todo
    }

    public static void main(String[] args) {


        launch(args);
    }


}
