package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.worldgen.World;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        StackPane layout = new StackPane();
        primaryStage.setScene(new Scene(layout, 1000, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        worldgen.Map test = new worldgen.Map(50,4,8,1e-2);
        World world = World.of(new worldgen.Map(50,4,8,1e-2).toString());
        launch(args);
    }
}
