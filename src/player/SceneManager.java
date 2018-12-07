package player;

import engine.frontend.game_engine_UI.BattleWorld.OpponentSide;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


public class SceneManager {
    private Stage myStage;
    public static final int BOX_SPACING=8;
    private final int INCEST_TP=15;
    private final int INCEST_RL=12;
    private UserProfileManager userManager;
    private UserPaneManager userPaneManager;
    private final int REGISTER_BOX_WIDTH=500;
    private final int REGISTER_BOX_HEIGHT=100;
    private BorderPane mainPane;
    private GamePaneManager gamePaneManager;
    public static final String DEFAULT_RESOURCE = "English";
    private ResourceBundle myResources;
    public SceneManager(UserProfileManager manager, Stage stage){
        userManager=manager;
        myStage=stage;
        userPaneManager= new UserPaneManager(userManager);
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myStage.setScene(getLoginScene());
        gamePaneManager= new GamePaneManager();
    }

    private Scene getLoginScene(){
        BorderPane borderPane= new BorderPane();
        borderPane.setPrefSize(500,100);
        Scene loginScene = new Scene(borderPane);
        getLoginScreen(borderPane);
        return loginScene;
    }

    private void getLoginScreen(BorderPane pane){
        VBox vBox=new VBox();
        pane.setCenter(vBox);
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(8);
        setUpLoginBox(vBox);
    }

    private void setUpLoginBox(VBox vbox) {
        TextField emailField = new TextField(myResources.getString("email"));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(myResources.getString("password"));
        Button loginButton = new Button(myResources.getString("loginBTN"));
        Button registerButton = new Button(myResources.getString("registerBTN"));
        registerButton.setOnAction(event->{myStage.setScene(setUpRegisterScene());});
        vbox.getChildren().addAll(emailField, passwordField, loginButton,registerButton);
        loginButton.setOnAction(event -> {
            userManager.login(emailField.getText(),passwordField.getText());
            if(userManager.isPlayerLoggedIn()){
               myStage.setScene(setUpMainScene());}
        });
    }
    private Scene setUpRegisterScene(){
        BorderPane borderPane= new BorderPane();
        Scene registerScene = new Scene(borderPane);
        VBox vbox = new VBox();
        setUpRegisterBox(vbox);
        borderPane.setPrefSize(REGISTER_BOX_WIDTH,REGISTER_BOX_HEIGHT);
        borderPane.setCenter(vbox);
        return registerScene;
    }
    private void setUpRegisterBox(VBox vBox){
        vBox.setSpacing(BOX_SPACING);
        vBox.setPadding(new Insets(INCEST_TP, INCEST_RL
                , INCEST_TP, INCEST_RL));
        TextField emailField = new TextField(myResources.getString("email"));
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(myResources.getString("password"));
        TextField bioField = new TextField(myResources.getString("bio"));
        TextField nameField= new TextField(myResources.getString("name"));
        Button registerButton = new Button(myResources.getString("registerBTN"));
        vBox.getChildren().addAll(emailField,passwordField,bioField,nameField,registerButton);
        registerButton.setOnAction(event->{
            userManager.register(emailField.getText(),passwordField.getText(),bioField.getText(),nameField.getText());
            if(userManager.isPlayerLoggedIn()){myStage.setScene(setUpMainScene());}
        });
    }
    private Scene setUpMainScene(){
        mainPane= new BorderPane();
        mainPane.setPrefSize(PlayerMain.SCREEN_SIZE,PlayerMain.SCREEN_SIZE);
        Scene mainScene= new Scene(mainPane);
        mainPane.setRight(userPaneManager.setUpAccountBox());
        HBox hBox= new HBox();
        hBox.getChildren().addAll(setUpMenuBar());
        mainPane.setTop(hBox);
        mainPane.setCenter(gamePaneManager.getGamesPane());
        return mainScene;
    }
    private MenuBar setUpMenuBar(){
        MenuBar menuBar= new MenuBar();
        menuBar.prefWidthProperty().bind(myStage.widthProperty());
        Menu fileMenu = new Menu(myResources.getString("File"));
        setFileMenu(fileMenu);
        Menu accountMenu = new Menu(myResources.getString("accountMenu"));
        setAccountMenu(accountMenu);
        menuBar.getMenus().addAll(fileMenu,accountMenu);
        return menuBar;
    }
    private void setFileMenu(Menu menu){
        MenuItem exitItem = new MenuItem(myResources.getString("exitMI"));
        exitItem.setOnAction(event->{System.exit(0);});
        menu.getItems().add(exitItem);
    }
    private void setAccountMenu(Menu menu){
        MenuItem logOutItem = new MenuItem(myResources.getString("logOutMI"));
        logOutItem.setOnAction(event->{
            userManager.clear();
            myStage.setScene(getLoginScene());
        });
        MenuItem editAccountItem = new MenuItem(myResources.getString("editAccountMI"));
        MenuItem socialItem=new MenuItem(myResources.getString("searchMI"));
        socialItem.setOnAction(event -> {setUserLookupDialog();});
        editAccountItem.setOnAction(event->{setEditAccountDialog();});
        menu.getItems().addAll(logOutItem,editAccountItem,socialItem);
    }
    private void setEditAccountDialog(){

        TextField nameField=new TextField(userManager.getUserAttributes().get(myResources.getString("name")));
        TextField bioField= new TextField(userManager.getUserAttributes().get(myResources.getString("bio")));
        Button saveButton = new Button(myResources.getString("Save"));
        saveButton.setOnAction(event->{
            try {
                userManager.updateInfo(nameField.getText(), bioField.getText());
                mainPane.setRight(userPaneManager.setUpAccountBox());
            }catch(ServerException e){
                launchErrorDialog(e.getException());
            }
        });
        VBox vBox= new VBox();
        vBox.getChildren().addAll(new Text(myResources.getString("name")),nameField,new Text(myResources.getString("bio")),bioField,saveButton);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(myResources.getString("profile"));
        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();

    }
    private void setUserLookupDialog(){
        TextInputDialog dialog= new TextInputDialog(myResources.getString("name"));
        dialog.setHeaderText(myResources.getString("socialPortal"));
        dialog.setHeaderText(myResources.getString("findUsers"));
        dialog.setContentText(myResources.getString("namePrompt"));
        Optional <String>result=dialog.showAndWait();
        result.ifPresent(name->{
                if(!name.equals(myResources.getString("name"))){
                    try{
                    setUpUserListDialog(myResources.getString("socialPortal"),myResources.getString("users"),userManager.lookUpUsers(name));}
                    catch(ServerException e){
                        launchErrorDialog(e.getException());
                    }
                }
        });
    }
    private void setUpUserListDialog(String title, String header, List<JSONObject>entries){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(header);
        VBox vBox= new VBox();
        for(JSONObject user:entries){
            Button button= new Button((String)user.get("name"));
            button.setOnAction(event->{setUpUserDialog(user);});
            vBox.getChildren().add(button);
        }

        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();
    }
    private void setUpUserDialog(JSONObject user){
        VBox vBox= new VBox();
        vBox.setSpacing(BOX_SPACING);
        vBox.getChildren().addAll(new Text(myResources.getString("name")+": "+(String)user.get("name")),new Text(myResources.getString("email")+": "+(String)user.get(myResources.getString("email"))),new Text(myResources.getString("bio")+": "+(String)user.get("bio")));
        List<String>followers= new ArrayList<>();
        List<String>following= new ArrayList<>();
        userManager.parseArray((JSONArray)user.get("followers"),followers);
        userManager.parseArray((JSONArray)user.get("following"),following);
        Button followButton= new Button(myResources.getString("follow"));
        if(userManager.getFollowers().contains((String)user.get("email"))){followButton.setText(myResources.getString("following"));}
        else{
            followButton.setOnAction(event->{
                try {
                    userManager.followUser((String) user.get("email"));
                    mainPane.setRight(userPaneManager.setUpAccountBox());
                }catch(ServerException e){
                    launchErrorDialog(e.getException());
                }
            });

        }
        vBox.getChildren().addAll(userPaneManager.setUpFollowerBox(followers,following),followButton);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(myResources.getString("socialPortal"));
        alert.setHeaderText(myResources.getString("users"));
        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();
    }
    private void launchErrorDialog(Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(myResources.getString("error"));
        alert.getDialogPane().setContent(new VBox(new Text(e.getMessage())));
        alert.showAndWait();
    }
}
