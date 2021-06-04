package fx;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import objects.buildings.Station;
import setup.crafting.Recipe;
import setup.player.Inventory;
import setup.player.Player;
import setup.register.Registers;
import setup.register.RegistryObject;
import setup.world.Tile;
import setup.worldgen.World;
import setup.worldgen.WorldGenSettings;
import util.ImgFinder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {


    private final GridPane root = new GridPane();
        private final GridPane field = new GridPane();
            private final ScrollPane mapField = new ScrollPane();
        private final GridPane invDialog = new GridPane();
            private final TilePane invField = new TilePane(Orientation.HORIZONTAL);
            private final GridPane invData = new GridPane();
                private final Text invWeight = new Text();
                private final Text invSpace = new Text();
                private final Text invTotalOccupants = new Text();
        private final GridPane craftDialog = new GridPane();
            private final TilePane craftField = new TilePane(Orientation.HORIZONTAL);
        private final Pane bldDialog = new Pane();
            private final Pane bldInv = new Pane();
            private final Pane bldData = new Pane();
        private final GridPane envDialog = new GridPane();
            private final Pane playerEnvironment = new Pane();
            private final GridPane guideEntry = new GridPane();
                private final Text entry = new Text();



    /*
    CURRENT:
    GridPane root
        GridPane field
            ScrollPane mapField (unused?)
                Group[] render
                    ImageView tile
                    ImageView building/entity
        GridPane invDialog
            TilePane invField
                ImageView[] items
            GridPane invData
                Text invWeight
                Text invSpace
                Text invTotalOccupants
        GridPane craftDialog
            TilePane craftField
                ImageView[] items
        GridPane bldDialog
            Pane bldInv (unused, WIP)
            Pane bldData (unused, WIP)
        GridPane envDialog
            Pane playerEnvironment (WIP)
            GridPane guideEntry
                Text entry (rename -> guideEntryText?)


    REFACTOR PROPOSAL:
    GridPane root
        Group field
            GridPane tiles
                ImageView[] tiles
            (Node?)[] entityPanes
                ImageView[] entities
            GridPane builds
                ImageView[] builds
        GridPane invDialog
            TilePane invField
                ImageView[] items
            GridPane invData
                Text invWeight
                Text invSpace
                Text invTotalOccupants
        GridPane craftDialog
            TilePane craftField
                ImageView[] items
        GridPane bldDialog
            Pane bldInv (unused, WIP)
            Pane bldData (unused, WIP)
        GridPane envDialog
            Pane playerEnvironment (WIP)
            GridPane guideEntry
                Text entry (rename -> guideEntryText?)


    */
    Border defaultBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.THIN));


    private Player dummy;// = new Player(); //TODO: retrieve player from save file.
    private final int renderDistance = 10;
    private final int imgsize = 32;
    private final World renderedWorld = World.generate(WorldGenSettings.getInstance());

    private RegistryObject openEntry;


    @Override
    public void start(Stage primaryStage) {

        dummy = renderedWorld.player;

        field.setPrefSize(imgsize*renderDistance, imgsize*renderDistance);
        mapField.setContent(field);
        mapField.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);
        mapField.vbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.NEVER);


        renderMap((int)dummy.getXCoordinate(), (int)dummy.getYCoordinate());
        renderDialogs();

        setLayout();

        Scene s = setScene();



        tweakPrimaryStage(primaryStage, s);


    }

    private void tweakPrimaryStage(Stage primaryStage, Scene s) {
        primaryStage.setScene(s);
        primaryStage.setTitle("DH2");
        primaryStage.getIcons().add(new Image("file:src/assets/tiles/missing.png"));
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    private Scene setScene() {
        Scene s = new Scene(root);

        s.setCursor(new ImageCursor(new Image("file:src/assets/cursor.png")));
        s.setOnMouseClicked(e-> {
            renderMap((int)dummy.getXCoordinate(), (int)dummy.getYCoordinate());
            renderDialogs();
        });
        s.setOnKeyPressed(e->handleKeyPress(e.getText(),e.isShiftDown(),e.isControlDown(),e.isAltDown()));
        s.setOnKeyReleased(e->handleKeyRelease(e.getText()));
        return s;
    }

    private void setLayout() {

        RowConstraints r1 = new RowConstraints(), r2 = new RowConstraints(), r3 = new RowConstraints(), r4 = new RowConstraints();
        ColumnConstraints c1 = new ColumnConstraints(), c2 = new ColumnConstraints();
        r1.setPercentHeight(25);
        r2.setPercentHeight(25);
        r3.setPercentHeight(25);
        r4.setPercentHeight(25);
        c1.setPercentWidth(70);
        c2.setPercentWidth(30);

        //DIALOG ROOT CONSTRAINTS + BORDERS
        GridPane.setConstraints(mapField,0,0,1,3);
        GridPane.setConstraints(invDialog,1,0);
        GridPane.setConstraints(craftDialog, 1, 1);
        GridPane.setConstraints(bldDialog,1,2);
        GridPane.setConstraints(envDialog,1,3);
        invDialog.setBorder(defaultBorder);
        craftDialog.setBorder(defaultBorder);
        bldDialog.setBorder(defaultBorder);
        envDialog.setBorder(defaultBorder);

        //DIALOG CONSTRAINTS
        //Inventory dialog
        GridPane.setConstraints(invField,0,0);
        GridPane.setConstraints(invData,0,1);
        //Crafting dialog
        GridPane.setConstraints(craftField,0,0);

        //Environment dialog
        GridPane.setConstraints(guideEntry,0,0);
        GridPane.setConstraints(playerEnvironment,0,1);

        invDialog.getChildren().addAll(invField,invData);
        craftDialog.getChildren().addAll(craftField);
        envDialog.getChildren().addAll(guideEntry,playerEnvironment);

        //INVENTORY CONSTRAINTS
        GridPane.setConstraints(invWeight,0,0);
        GridPane.setConstraints(invSpace,0,1);
        GridPane.setConstraints(invTotalOccupants,0,2);
        invField.setPrefTileHeight(imgsize);
        invField.setPrefTileWidth(imgsize);
        invField.setPrefWidth(16+320);

        invData.getChildren().addAll(invWeight,invSpace,invTotalOccupants);

        //CRAFTING CONSTRAINTS


        //ENVIRONMENT CONSTRAINTS



        //ROOT
        root.getRowConstraints().addAll(r1,r2,r3,r4);
        root.getColumnConstraints().addAll(c1,c2);
        root.getChildren().addAll(mapField,invDialog,craftDialog,bldDialog,envDialog);
        root.setBorder(defaultBorder);


    }

    //TODO: extract to controller class?

    private void handleKeyPress(String character, boolean shift, boolean ctrl, boolean alt) {
        switch(character){
            case "w":
                dummy.moveIn((byte) 8);
                //TODO: cleanup
                renderMap((int)dummy.getXCoordinate(),(int)dummy.getYCoordinate());
                break;
            case "a":
                dummy.moveIn((byte) 4);
                renderMap((int)dummy.getXCoordinate(),(int)dummy.getYCoordinate());
                break;
            case "s":
                dummy.moveIn((byte) 2);
                renderMap((int)dummy.getXCoordinate(),(int)dummy.getYCoordinate());
                break;
            case "d":
                dummy.moveIn((byte) 1);
                renderMap((int)dummy.getXCoordinate(),(int)dummy.getYCoordinate());
                break;
            case "q":
                System.out.println("break");
            default:
        }
        renderDialogs();
    }


    private void renderDialogs(){
        renderInventoryDialog();
        renderCraftingDialog();
        renderBuildingDialog();
        renderEnvironmentDialog();
    }

    //Technically this method *should* take World renderedWorld as parameter too

    private void renderMap(int x, int y){
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
    private void renderInventoryDialog(){
        Inventory inv = dummy.getInventory();
        invField.getChildren().clear();
        for (int i = 0; i < inv.getLength(); i++) {
            Group g = new Group();
            ImageView image = new ImageView(ImgFinder.get(inv.getByIndex(i).getName(),"items",imgsize));
            g.getChildren().add(image);
            if(i == inv.getSelectedIndex()){
                g.getChildren().add(new ImageView(ImgFinder.get("select",imgsize)));
            }
            int i2 = i;
            image.setOnMouseClicked(e->{
                switch (e.getButton()){

                    case NONE, MIDDLE, SECONDARY -> {
                    }
                    case PRIMARY -> {
                        dummy.getInventory().select(i2);
                        renderDialogs();
                    }
                }
            });

            invField.getChildren().add(g);
        }
        invWeight.setText("Weight: " + inv.getWeight() + " out of " + inv.getMaxWeight());
        invSpace.setText("Space: " + inv.getOccupiedSpace() + " out of " + inv.getMaxSize() + " occupied");
        invTotalOccupants.setText(inv.getLength() + " items");



    }
    private void renderCraftingDialog(){
        craftField.getChildren().clear();

        Inventory inv = dummy.getInventory();
        List<Station> nearStations = new ArrayList<>();
        //yeh, this will work somehow
        for (int i = (int) (dummy.getXCoordinate()-2); i < dummy.getXCoordinate()+2; i++) {
            for (int j = (int) (dummy.getYCoordinate()-2); j < dummy.getYCoordinate()+2; j++) {
                if(renderedWorld.get(i,j).getBuilding() instanceof Station){
                    nearStations.add((Station) renderedWorld.get(i,j).getBuilding());
                }
            }
        }
        List<Recipe> craftable = new ArrayList<>();
        Registers.RCP.forEachObject(recipe -> {
            if (recipe.isCraftable(dummy, nearStations)) {
                craftable.add(recipe);
            }
        });

        for (Recipe r : craftable) {
            ImageView image = new ImageView(ImgFinder.get(r.out.getName(),"items",imgsize));
            image.setOnMouseClicked(e->{
                r.craft(dummy.getInventory());
                renderDialogs();
            });
            craftField.getChildren().add(image);
        }


    }
    private void renderBuildingDialog(){



    }
    private void renderEnvironmentDialog(){
        guideEntry.getChildren().clear();
        if(openEntry!=null){
            System.out.println("reading guide entry for object " + openEntry.getName());
            File entryFile = new File("src/assets/guide-entries/"+openEntry.getName()+".txt");
            try {
                BufferedReader reader = new BufferedReader(new FileReader(entryFile));
                String entrytext = reader.lines().reduce("",(a,b)->a+"\n"+b);
                entry.setText(entrytext);
            } catch (FileNotFoundException e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(new File("src/assets/guide-entries/none.txt")));
                    String entrytext = reader.lines().reduce("",(a,b)->a+"\n"+b);
                    entry.setText(entrytext);
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                    entry.setText("");
                }
            }
        }else{
            entry.setText("");
        }

        guideEntry.getChildren().add(entry);

    }

    private Group tileRender(Tile tile){
        Group render = new Group();
        Image img = ImgFinder.get(tile.getAssetName(),"tiles", imgsize);
        render.getChildren().add(new ImageView(img));
        if(tile.getBuilding()!=null){
            Image bld = ImgFinder.get(tile.getBuilding().getAssetName(),"buildings", imgsize);
            render.getChildren().add(new ImageView(bld));
        }
        //add render layer for entities

        render.setOnMouseClicked(e->{
            switch(e.getButton()){
                case NONE-> {
                }
                case PRIMARY -> {
                    tile.onPrimaryInteract(dummy);
                    renderDialogs();

                }
                case MIDDLE -> {
                    if(tile.getBuilding()!=null){
                        openEntry = tile.getBuilding();
                    }else if(tile.getOccupant()!=null){
                        openEntry = tile.getOccupant();
                    }else{
                        openEntry = tile;
                    }
                }
                case SECONDARY -> {
                    tile.onSecondaryInteract(dummy);
                    renderDialogs();
                }
            }
        });

        render.setBlendMode(BlendMode.SRC_ATOP);


        return render;

    }







    private void handleKeyRelease(String character){
        //todo
    }




    public static void main(String[] args) {
        try {
            Thread.sleep(-100); //Time travel makes programs run so much faster
        } catch (Exception ignored) {
            //"handle" the IllegalArgumentException
            //tachyon relativity do be hitting different tho
        }

        launch(args);
    }



}
/*
This place is reserved for outdated memes.
 */