package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.MenuView.BattleMenu;
import engine.frontend.game_engine_UI.WorldView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * View class extended from WordlView, is responsible for updating the battle view
 * in the game
 *
 * @author Duy Trieu (dvt5)
 */
public class BattleView extends WorldView implements BattleViewAPI {
    private PlayerSide playerSide;
    private OpponentSide opponentSide;
    private AnimationObject myPlayer;
    private AnimationObject myEnemy;
    private BattleMenu menuView;
    private String pathWay = "";
    private ImageView battle_background;

    private static final double SCREEN_WIDTH = 750;
    private static final double SCREEN_HEIGHT = 600;

    public BattleView(Controller controller) {
        super(controller);
        setUpDisplay();
        addButtonPane();
    }

    private void setUpDisplay() {
        clearView();
        this.myEnemy = myController.getBattleEnemyAnimation().get(0);
        this.myPlayer = myController.getBattlePlayerAnimation().get(0);
        playerSide = new PlayerSide(myPlayer);
        opponentSide = new OpponentSide(myEnemy);
        this.playerSide.setHealth(myController.getAlliesHealth().get(0));
        this.opponentSide.setHealth(myController.getEnemiesHealth().get(0));
        battle_background = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("battle_background.png")));
        battle_background.setFitWidth(SCREEN_WIDTH);
        battle_background.setFitHeight(SCREEN_WIDTH/2);
        displayPane.getChildren().add(battle_background);
        displayPane.setLeft(playerSide);
        displayPane.setRight(opponentSide);
    }
    private void addButtonPane () {
        HBox buttonBox = new HBox();
        Button combatButton = new Button("Battle!");
        buttonBox.getChildren().add(combatButton);
        displayPane.getChildren().add(buttonBox);
        buttonBox.setTranslateY(battle_background.getFitHeight());
        combatButton.setOnMouseClicked((event -> {
            menuView = new BattleMenu(myController);
            menuView.setPrefHeight(SCREEN_HEIGHT-battle_background.getFitHeight());
            menuView.setMaxHeight(SCREEN_HEIGHT-battle_background.getFitHeight());
            displayPane.setCenter(menuView);
            displayPane.setAlignment(menuView, Pos.BOTTOM_CENTER);
            menuView.setSelectedCommand();
            this.playerSide.setHealth(myController.getAlliesHealth().get(0));
            this.opponentSide.setHealth(myController.getEnemiesHealth().get(0));
            System.out.println("health: " + myController.getAlliesHealth().get(0));
        }));
    }
    public BattleMenu getMenuView () {
        return menuView;
    }
}
