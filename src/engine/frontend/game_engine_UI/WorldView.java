package engine.frontend.game_engine_UI;

import engine.backend.gameevent.GameKeyEvent;
import engine.controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Super class that manages the view in the game. This class is extended by OverWorldView
 * and BattleWorldView. Main functionality includes adding all AnimationObjects to the
 * Pane, updating the game loop, and switch between two world views.
 *
 * @author Duy Trieu (dvt5)
 */
public abstract class WorldView {
    protected Timeline animation;
    protected Scene myScene;
    protected BorderPane displayPane;
    protected Controller myController;

    protected Runnable nextSceneHandler;

    public void setNextSceneHandler (Runnable handler) {
        nextSceneHandler = handler;
    }
    /**
     * @param controller Controller that will send information from the back end to be updated in the front end
     */

    public WorldView (Controller controller) {
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        this.myController = controller;
        displayPane = new BorderPane();
        myScene = new Scene(displayPane, 750 , 600, Color.BLACK);
        myScene.setOnKeyPressed(e -> myController.getGameWorld().handleInput(new GameKeyEvent(e)));
    }

    /**
     * Clear the view
     */
    public void clearView () {
        displayPane.getChildren().clear();
    }

    /**
     * return myScene
     */
    public Scene getMyScene () {
        return myScene;
    }
    /**
     * change the boolean. Currently in testing to change between views using Runnable
     */

}
