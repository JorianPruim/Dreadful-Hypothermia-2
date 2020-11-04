package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        World w = World.generate(WorldGenSettings.getInstance());

        primaryStage.setTitle("Hello World");
        StackPane layout = new StackPane();
        layout.getChildren().add(new Text(w.toString()));
        primaryStage.setScene(new Scene(layout, 2000, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {


        launch(args);
    }
}
