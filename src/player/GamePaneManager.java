package player;


import authoring.authoring_backend.GameData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static player.SceneManager.DEFAULT_RESOURCE;

public class GamePaneManager {
    private ScrollPane gamesPane;
    private List<GameData> gameDataList;
    private ResourceBundle myResources;
    private final String FILE_PATH="./resources/games.xml";
    private final int LOGO_SIDE_LENGTH=100;
    public GamePaneManager(){
        gameDataList= new ArrayList<>();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        setUpGamesList();
        gamesPane= setUpGamesPane();
    }
    public ScrollPane getGamesPane(){return gamesPane;}
    private void setUpGamesList(){
        XStream xStream= new XStream(new DomDriver());
        Map<String,GameData> localGames=(Map<String,GameData>)xStream.fromXML(new File(FILE_PATH));
        gameDataList.addAll(localGames.values());
    }
    private ScrollPane setUpGamesPane(){
        ScrollPane scrollPane= new ScrollPane();
        scrollPane.setContent(getGamesBox());
        return scrollPane;
    }
    private VBox getGamesBox(){
        VBox vBox= new VBox();
        vBox.setAlignment(Pos.CENTER);
        for(GameData g:gameDataList){
            vBox.getChildren().add(setUpGameBox(g));
        }
        return vBox;
    }
    private HBox setUpGameBox(GameData g){
        HBox hBox= new HBox();
        hBox.setSpacing(8);
        Button playButton = new Button(myResources.getString("playBtn"));
        Button editButton= new Button(myResources.getString("editBtn"));
        ImageView gameLogo= new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("GameLogo.png")));
        gameLogo.setFitWidth(LOGO_SIDE_LENGTH);
        gameLogo.setFitHeight(LOGO_SIDE_LENGTH);
        hBox.getChildren().addAll(gameLogo,new Text(g.getTitle()),new Text(g.getDescription()),playButton,editButton);
        return  hBox;
    }
}
