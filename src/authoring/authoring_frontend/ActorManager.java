package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to manage the different actors in the frontend.
 *
 * @author Allen Qiu
 */
public class ActorManager {
    private ArrayList<Actor> backgroundActors;
    private ArrayList<Actor> playableActors;
    private GameManager gameManager;
    private ScrollPane backgroundTilePane;
    private ScrollPane actorTilePane;
    private BorderPane selectedPane;
    private String programName;
    private static final int WINDOW_WIDTH = 200;
    private static final int WINDOW_HEIGHT = 400;

    /**
     * Constructor for the actor manager.
     * @param gm The GameManager for this game.
     */
    ActorManager(GameManager gm, String name){
        backgroundActors = new ArrayList<>();
        playableActors = new ArrayList<>();
        backgroundTilePane = new ScrollPane();
        actorTilePane = new ScrollPane();
        selectedPane = null;
        gameManager = gm;
        programName = name;

//        try {
//          //  loadDefaultActors();
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * Adds an actor to the actor manager.
     * @param newActor The actor to add.
     * @param isPlayable Whether or not this actor is playable.
     */
    public void addActor(Actor newActor, boolean isPlayable){
        if(isPlayable){
            playableActors.add(newActor);
        }
        else {
            backgroundActors.add(newActor);
        }
    }

    /**
     * Gets a list of actors that are not playable.
     * @return List of non-playable actors.
     */
    private List<Actor> getBackgroundActors(){
        return backgroundActors;
    }

    /**
     * Gets a list of actors that are playable.
     * @return List of playable actors.
     */
    private List<Actor> getPlayableActors(){
        return playableActors;
    }

    /**
     * Rewritten by Michael Glushakov to not rely on the JSON file because making changes to structure was a pain
     * Loads the default 40 or so actors.
     * @throws IOException
     * @throws ParseException
     */
    private void loadDefaultActors() throws IOException, ParseException {
        JSONArray defaults= new JSONArray();
        for(int i=1;i<=42;i+=1){
            JSONObject defalutObject=new JSONObject();
            defalutObject.put("name","default"+i);
            JSONArray animations= new JSONArray();
            JSONObject animation=new JSONObject();
            animation.put("key","idle");
            animation.put("path",i+".png");
            animation.put("spriteRows",1);
            animation.put("spriteCols",1);
            animations.add(animation);
            defalutObject.put("animations",animations);
            defalutObject.put("stats",new JSONArray());
            JSONObject bounds = new JSONObject();
            bounds.put("relX",0);
            bounds.put("relY",0);
            bounds.put("width",5);
            bounds.put("height",5);
            defalutObject.put("bounds",bounds);
            defalutObject.put("isPlayer",false);
            JSONArray interractions= new JSONArray();
            JSONObject backgroundInterraction=new JSONObject();
            backgroundInterraction.put("name","idle");
            backgroundInterraction.put("type","background");
            backgroundInterraction.put("canPassThrough",true);
            backgroundInterraction.put("messages",new JSONArray());
            backgroundInterraction.put("animations",animations);
            interractions.add(backgroundInterraction);
            defalutObject.put("interactions",interractions);
            defalutObject.put("ActivateMessages",new JSONArray());
            defalutObject.put("DeactivateMessages",new JSONArray());
            gameManager.createActorPrototype(defalutObject);
            addActor(new Actor(defalutObject), (boolean) defalutObject.get("isPlayer"));
        }
    }

    /**
     * Sets up a tab and loads the actors and sets the on click actions.
     * @param actorList The list of actors to create a list from.
     * @return A FlowPane with the images of the actors loaded.
     */
    private FlowPane setupTab(List<Actor> actorList){
        backgroundTilePane.setPrefViewportWidth(WINDOW_WIDTH);
        backgroundTilePane.setPrefViewportHeight(WINDOW_HEIGHT);
        backgroundTilePane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        ArrayList<BorderPane> tileImages = new ArrayList<>();
        for(Actor thisActor:actorList){
            BorderPane thisTileImage = new BorderPane(thisActor.getActorImage());
            thisTileImage.setOnMouseClicked(event -> {
                Actor currentActiveActor = ActiveItem.getActiveItem(programName);
                if(currentActiveActor == null){
                    //first tile clicked
                    ActiveItem.setActiveItem(programName, thisActor);
                    thisTileImage.setStyle("-fx-border-color: blue;");
                    selectedPane = thisTileImage;
                }
                else if(currentActiveActor.equals(thisActor)){
                    //deselect
                    selectedPane.setStyle(null);
                    selectedPane = null;
                    ActiveItem.removeActiveItem(programName);
                }
                else {
                    //replace old selection
                    selectedPane.setStyle(null);
                    ActiveItem.setActiveItem(programName, thisActor);
                    selectedPane = thisTileImage;
                    thisTileImage.setStyle("-fx-border-color: blue;");
                }
            });
            tileImages.add(thisTileImage);
        }
        FlowPane tileHolder = new FlowPane();
        tileHolder.getChildren().addAll(tileImages);
        tileHolder.setPrefWrapLength(200);
        return tileHolder;
    }

    /**
     * Sets up the content of the two different tabs in the menu.
     */
    public void setupTabs(){
        backgroundTilePane.setContent(setupTab(getBackgroundActors()));
        //System.out.println("Background actor size is " + getBackgroundActors().size());
        actorTilePane.setContent(setupTab(getPlayableActors()));
    }

    public TabPane getActorMenu(){
        setupTabs();
        TabPane allTabs = new TabPane();
        Tab backgroundTab = new Tab();
        backgroundTab.setContent(backgroundTilePane);
        backgroundTab.setText("Backgrounds");
        Tab actorTab = new Tab();
        actorTab.setContent(actorTilePane);
        actorTab.setText("Actors");
        allTabs.getTabs().addAll(backgroundTab, actorTab);
        return allTabs;
    }
}
