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

    @Override
    public void start(Stage primaryStage) throws Exception{

        Map b = new Map(1000,15,25,0.003);
        Text t = new Text(b.toVisualString());

        //testing only
        int height = 1000;
        int width = 600;
        primaryStage.setTitle("Hello World");


        GridPane pane = new GridPane();

        Text[][] visualMap = new Text[25][25];
        for(int i = 0; i<visualMap.length; i++){
            for(int j = 0; j<visualMap.length; j++){
                visualMap[i][j] = new Text("" + i + j);
                GridPane.setConstraints(visualMap[i][j], j, i);
                pane.getChildren().add(visualMap[i][j]);
            }
        }

//        GridPane.setConstraints(t, 0, 25);
//        pane.getChildren().add(t);


        Scene s = new Scene(pane, height, width);

        primaryStage.setScene(s);
        primaryStage.show();

    }

    public static void main(String[] args) {


        launch(args);
    }
}
