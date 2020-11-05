package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
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


        primaryStage.setTitle("Hello World");


        StackPane pane = new StackPane(t);



        Scene s = new Scene(pane, 2000, 600);

        primaryStage.setScene(s);
        primaryStage.show();

    }

    public static void main(String[] args) {


        launch(args);
    }
}
