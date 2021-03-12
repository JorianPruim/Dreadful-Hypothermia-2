package fx;

import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
import util.ImgFinder;


public class Main extends Application {
    private final int size = 250;
    
    
    private final GridPane root = new GridPane();
    private final ScrollPane mapField = new ScrollPane();
    private final GridPane field = new GridPane();
    private final Pane invDialog = new Pane();
    private final Pane bldDialog = new Pane();
    private final Pane envDialog = new Pane();

    Border defaultBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN));


    private Player dummy;// = new Player(); //TODO: retrieve player from save file.
    private final int renderDistance = 10;
    private final ImageView[][] visualMap = new ImageView[renderDistance*2][renderDistance*2];
    private final int imgsize = 32;
    private final World renderedWorld = World.generate(WorldGenSettings.getInstance());
    @Override
    public void start(Stage primaryStage) throws Exception{

        dummy = renderedWorld.player;

        field.setPrefSize(imgsize*renderDistance, imgsize*renderDistance);
        mapField.setContent(field);


        render((int)dummy.getXCoordinate(), (int)dummy.getYCoordinate());





        Text t1 = new Text("field 1"), t2 = new Text("field 2"), t3 = new Text("field 3");
        invDialog.getChildren().add(t1); bldDialog.getChildren().add(t2); envDialog.getChildren().add(t3);
        
        RowConstraints r1 = new RowConstraints(), r2 = new RowConstraints(), r3 = new RowConstraints();
        ColumnConstraints c1 = new ColumnConstraints(), c2 = new ColumnConstraints();
        r1.setPercentHeight(20); r2.setPercentHeight(30); r3.setPercentHeight(50);
        c1.setPercentWidth(70); c2.setPercentWidth(30);

        
        GridPane.setConstraints(mapField,0,0,1,3);
        GridPane.setConstraints(invDialog,1,0);GridPane.setConstraints(bldDialog,1,1);GridPane.setConstraints(envDialog,1,2);
        invDialog.setBorder(defaultBorder);bldDialog.setBorder(defaultBorder);envDialog.setBorder(defaultBorder);

        root.getRowConstraints().addAll(r1,r2,r3);
        root.getColumnConstraints().addAll(c1,c2);
        root.getChildren().addAll(mapField,invDialog,bldDialog,envDialog);
        root.setBorder(defaultBorder);

        Scene s = new Scene(root);

        s.setCursor(new ImageCursor(new Image("file:src/assets/tiles/missing.png")));
        s.setOnMouseClicked(e-> render((int)dummy.getXCoordinate(), (int)dummy.getYCoordinate()));
        s.setOnKeyPressed(e->handleKeyPress(e.getText(),e.isShiftDown(),e.isControlDown(),e.isAltDown()));
        s.setOnKeyReleased(e->handleKeyRelease(e.getText()));

        mapField.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        mapField.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);

        primaryStage.setScene(s);
        primaryStage.setTitle("DH2");
        primaryStage.getIcons().add(new Image("file:src/assets/tiles/missing.png"));
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
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
        /*mapField.setHvalue(xCoordinate/size);
        mapField.setVvalue(yCoordinate/size);*/
    }

    @Deprecated
    private void renderOld(double xd, double yd){
        int x = (int)xd;
        int y = (int)yd;
        field.getChildren().clear();
        for(int i = -renderDistance; i<renderDistance; i++){
            for (int j = -renderDistance; j < renderDistance; j++) {
                visualMap[renderDistance+i][renderDistance+j] = getAsset(x+i,y+j);
                GridPane.setConstraints(visualMap[renderDistance+i][renderDistance+j],x+i,y+j);
                field.getChildren().add(visualMap[renderDistance+i][renderDistance+j]);
            }
        }
        System.out.println("("+x+","+y+")");
    }

    //Technically this method *should* take World renderedWorld as parameter too
    private void render(int x, int y){
        field.getChildren().clear();
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
                if(x+i == (int)renderedWorld.player.getXCoordinate() && y+j == (int)renderedWorld.player.getYCoordinate()){
                    plane[renderDistance+i][renderDistance+j].getChildren().add(new ImageView(ImgFinder.get("player",imgsize)));
                }
                GridPane.setConstraints(plane[renderDistance+i][renderDistance+j],x+i,y+j);
                field.getChildren().add(plane[renderDistance+i][renderDistance+j]);

            }
        }

    }

    private Group tileRender(Tile tile){
        Group render = new Group();
        Image img = ImgFinder.get(tile.getName(),"tiles", imgsize);
        render.getChildren().add(new ImageView(img));
        if(tile.getBuilding()!=null){
            Image bld = ImgFinder.get(tile.getBuilding().getName(),"buildings", imgsize);
            render.getChildren().add(new ImageView(bld));
        }
        //add render layer for entities

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