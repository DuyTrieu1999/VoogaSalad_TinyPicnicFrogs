package engine.frontend.game_engine_UI.BattleWorld;

import engine.backend.AnimationObject;
import engine.backend.Commands.Command;
import engine.controller.Controller;
import engine.frontend.game_engine_UI.MenuView.MenuView;
import engine.frontend.game_engine_UI.WorldView;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.List;


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
    private MenuView menuView;
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
<<<<<<< HEAD
        AnimationObject testPlayer = new AnimationObject("player battle", pathWay+"player_right.png");
        AnimationObject testEnemy = new AnimationObject("enemy battle", pathWay+"player_left.png");
        playerSide = new PlayerSide(testPlayer);
        opponentSide = new OpponentSide(testEnemy);
=======
        playerSide = new PlayerSide(myPlayer);
        opponentSide = new OpponentSide(myEnemy);
>>>>>>> 69d9ec06d71cb7f7bf90a11c9d709f307bde5cfb
        this.playerSide.setHealth(myController.getalliesHealth().get(0));
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
            System.out.println("something");
            List<Command> commandList = myController.getAllCommand();
            menuView = new MenuView(commandList, displayPane);
            menuView.setPrefHeight(SCREEN_HEIGHT-battle_background.getFitHeight());
<<<<<<< HEAD
            menuView.getPane().setMaxHeight(SCREEN_HEIGHT-battle_background.getFitHeight());
            displayPane.setCenter(menuView.getPane());
            displayPane.setAlignment(menuView.getPane(), Pos.BOTTOM_CENTER);
            menuView.setSelectedCommand();
=======
            menuView.setMaxHeight(SCREEN_HEIGHT-battle_background.getFitHeight());
            displayPane.setCenter(menuView);
            displayPane.setAlignment(menuView, Pos.BOTTOM_CENTER);
            menuView.setSellectedCommand();
>>>>>>> 69d9ec06d71cb7f7bf90a11c9d709f307bde5cfb
            System.out.println(menuView);
        }));
    }
    public MenuView getMenuView () {
        return menuView;
    }

}
