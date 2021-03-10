package fx;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.DialogPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import setup.player.Player;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import setup.world.Tile;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;
import util.TemporaryHack;


public class Main extends Application {
    private final int size = 250;
    private final GridPane root = new GridPane();
    private final ScrollPane spane = new ScrollPane();
    private final GridPane pane = new GridPane();
    private final GridPane interactionPane = new GridPane();
    private Player dummy = new Player(); //TODO: retrieve player from save file.
    private final int renderDistance = 10;
    private final ImageView[][] visualMap = new ImageView[renderDistance*2][renderDistance*2];
    private final int imgsize = 32;
    private final World renderedWorld = World.generate(WorldGenSettings.getInstance());
    @Override
    public void start(Stage primaryStage) throws Exception{
        dummy = renderedWorld.player;

        //testing only
        int height = 1000;
        int width = 600;

        primaryStage.setTitle("DH2");


        pane.getColumnConstraints().add(new ColumnConstraints(imgsize));
        pane.getRowConstraints().add(new RowConstraints(imgsize));



        spane.setVvalue(0.5);
        spane.setHvalue(0.5);

        pane.setPrefSize(imgsize*renderDistance, imgsize*renderDistance);

        /*for (int i = 0; i<size; i++){
            visualMap[i][i] = getAsset(i, i);
            GridPane.setConstraints(visualMap[i][i], i, i);
            pane.getChildren().add(visualMap[i][i]);
            System.out.println(i);
        }*/



        render((int)dummy.getXCoordinate(), (int)dummy.getYCoordinate());


        spane.setVvalue(dummy.getYCoordinate()/size);
        spane.setHvalue(dummy.getXCoordinate()/size);

        Text text1 = new Text("dit wordt de inventory");
        Text text2 = new Text("dit s de building interacton nogwattes");
        Text text3 = new Text("gezondheid en welzijn");

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(10);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(15);
        RowConstraints row3 = new RowConstraints();
        row3.setPercentHeight(20);
        interactionPane.getRowConstraints().addAll(row1, row2, row3);


        GridPane.setConstraints(text1, 1, 1);
        GridPane.setConstraints(text2, 1, 2);
        GridPane.setConstraints(text3, 1, 3);
        interactionPane.getChildren().addAll(text1, text2, text3);

//        GridPane.setConstraints(t, 0, 25);
//        pane.getChildren().add(t);

        ColumnConstraints column1 = new ColumnConstraints();
        RowConstraints row10 = new RowConstraints();
        column1.setPercentWidth(80);
        row10.setPercentHeight(100);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        root.getRowConstraints().addAll(row10);
        root.getColumnConstraints().addAll(column1, column2);
        spane.setContent(pane);
        GridPane.setConstraints(spane, 0, 0);
        GridPane.setConstraints(interactionPane, 1, 0);
        root.getChildren().addAll(spane, interactionPane);
        Scene s = new Scene(root, height, width);

        s.setCursor(new ImageCursor(new Image("file:src/assets/tiles/missing.png")));
        s.setOnMouseClicked(e-> render((int)dummy.getXCoordinate(), (int)dummy.getYCoordinate()));
        s.setOnKeyPressed(e->handleKeyPress(e.getText(),e.isShiftDown(),e.isControlDown(),e.isAltDown()));
        s.setOnKeyReleased(e->handleKeyRelease(e.getText()));

        spane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        spane.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        primaryStage.setScene(s);
        primaryStage.getIcons().add(new Image("file:src/assets/tiles/missing.png"));
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(false);
        primaryStage.show();




    }

    //TODO: extract to controller class?
    private void handleKeyPress(String character, boolean shift, boolean ctrl, boolean alt) {
        switch(character){
            case "w":
                dummy.moveIn((byte) 8);
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                //TODO: cleanup
                render((int)dummy.getXCoordinate(),(int)dummy.getYCoordinate());
                break;
            case "a":
                dummy.moveIn((byte) 4);
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                render((int)dummy.getXCoordinate(),(int)dummy.getYCoordinate());
                break;
            case "s":
                dummy.moveIn((byte) 2);
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                render((int)dummy.getXCoordinate(),(int)dummy.getYCoordinate());
                break;
            case "d":
                dummy.moveIn((byte) 1);
                setWindow(dummy.getXCoordinate(), dummy.getYCoordinate());
                render((int)dummy.getXCoordinate(),(int)dummy.getYCoordinate());
                break;
            default:
                return;
        }

    }

    private void setWindow(double xCoordinate, double yCoordinate){
        spane.setHvalue(xCoordinate/size);
        spane.setVvalue(yCoordinate/size);
    }

    @Deprecated
    private void renderOld(double xd, double yd){
        int x = (int)xd;
        int y = (int)yd;
        pane.getChildren().clear();
        for(int i = -renderDistance; i<renderDistance; i++){
            for (int j = -renderDistance; j < renderDistance; j++) {
                visualMap[renderDistance+i][renderDistance+j] = getAsset(x+i,y+j);
                GridPane.setConstraints(visualMap[renderDistance+i][renderDistance+j],x+i,y+j);
                pane.getChildren().add(visualMap[renderDistance+i][renderDistance+j]);
            }
        }
        System.out.println("("+x+","+y+")");
    }

    //Technically this method *should* take World renderedWorld as parameter too
    private void render(int x, int y){
        pane.getChildren().clear();
        Group[][] plane = new Group[renderDistance*2][renderDistance*2];
        if(x-renderDistance<0){
            x+=renderDistance-x;
        }else if(x+renderDistance>renderedWorld.getSize()) {
            x -= renderDistance - (renderedWorld.getSize() - x);
        }
        if(y-renderDistance<0){
            y+=renderDistance-y;
        }else if(y+renderDistance>renderedWorld.getSize()){
            y-=renderDistance-(renderedWorld.getSize()-y);
        }
        for(int i = -renderDistance; i<renderDistance; i++){
            for(int j = -renderDistance; j<renderDistance; j++){
                plane[renderDistance+i][renderDistance+j] = tileRender(renderedWorld.get(x+i,y+j));
                GridPane.setConstraints(plane[renderDistance+i][renderDistance+j],x+i,y+j);
                pane.getChildren().add(plane[renderDistance+i][renderDistance+j]);
            }
        }
    }

    private Group tileRender(Tile tile){
        Group render = new Group();
        Image img = new Image("file:src/assets/tiles/" + tile.getName() + ".png",imgsize,imgsize,true,true);
        if(img.isError()){
            img = new Image("file:src/assets/tiles/missing.png",imgsize,imgsize,true,true);
        }
        render.getChildren().add(new ImageView(img));
        if(tile.getBuilding()!=null){
            Image bld = new Image("file:src/assets/buildings/"+tile.getBuilding().getName()+".png",imgsize,imgsize,true,true);
            if(img.isError()){
                bld = new Image("file:src/assets/buildings/missing.png",imgsize,imgsize,true,true);
            }
            render.getChildren().add(new ImageView(bld));
        }
        render.setBlendMode(BlendMode.SRC_ATOP);
        return render;

    }

    @Deprecated
    private ImageView getAsset(int x, int y){
        String biome = renderedWorld.get(x, y).getName();

        try {
            Image img = new Image("file:src/assets/tiles/" + biome + ".png", imgsize, imgsize, true, true);
            if(img.isError()){
                throw new Exception();
            }
            return new ImageView(img);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ImageView(new Image("file:src/assets/tiles/missing.png", imgsize, imgsize, true, true));
        }
    }

    private void handleKeyRelease(String character){
        //todo
    }




    public static void main(String[] args) {


        launch(args);
    }



}
/*
This place is reserved for outdated memes.
 */