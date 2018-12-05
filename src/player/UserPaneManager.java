package player;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class UserPaneManager {
    private UserProfileManager myManager;
    public UserPaneManager(UserProfileManager manager){
        myManager=manager;
    }

    public VBox setUpAccountBox(){
        VBox accountBox=new VBox();
        accountBox.setSpacing(8);
        accountBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        ImageView profileView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("profile_icon.png")));
        profileView.setFitWidth(100);
        profileView.setFitHeight(100);
        accountBox.getChildren().add(profileView);
        for(String key:myManager.getUserAttributes().keySet()){
            Text text = new Text(key+": "+myManager.getUserAttributes().get(key));
            text.setFont(Font.font("Veranda",20));
            accountBox.getChildren().add(text);
        }
        accountBox.getChildren().addAll(setUpFollowerBox(myManager.getFollowers(),myManager.getFollowing()));
        return accountBox;
    }

    public HBox setUpFollowerBox(List<String>followersList,List<String>followingList){
        HBox hbox = new HBox();
        Button followers= new Button("Followers: "+followersList.size());
        Button following = new Button("Following: "+followingList.size());
        followers.setOnAction(event->setUpListAlert("Followers","Followers",followersList));
        following.setOnAction(event->setUpListAlert("Following","Following",followingList));
        hbox.getChildren().addAll(followers,following);
        hbox.setSpacing(8);
        return hbox;
    }
    public void setUpListAlert(String title,String header, List<String> list){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        String text="";
        for(String s:list){text+=s+"\n";}
        alert.setContentText(text);
        alert.showAndWait();
    }



}
