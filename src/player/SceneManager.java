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


public class SceneManager {
    private Stage myStage;
    private UserProfileManager userManager;
    private UserPaneManager userPaneManager;
    private BorderPane mainPane;
    private GamePaneManager gamePaneManager;
    public SceneManager(UserProfileManager manager, Stage stage){
        userManager=manager;
        myStage=stage;
        userPaneManager= new UserPaneManager(userManager);
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
        TextField emailField = new TextField("email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Create Account");
        registerButton.setOnAction(event->{myStage.setScene(setUpRegisterScene());});
        vbox.getChildren().addAll(emailField, passwordField, loginButton,registerButton);
        loginButton.setOnAction(event -> {
            userManager.login(emailField.getText(),passwordField.getText());
            if(userManager.isPlayerLoggedIn()){
                System.out.println("Logged In");
               myStage.setScene(setUpMainScene());}
        });
    }
    private Scene setUpRegisterScene(){
        BorderPane borderPane= new BorderPane();
        Scene registerScene = new Scene(borderPane);
        VBox vbox = new VBox();
        setUpRegisterBox(vbox);
        borderPane.setPrefSize(500,100);
        borderPane.setCenter(vbox);
        return registerScene;
    }
    private void setUpRegisterBox(VBox vBox){
        vBox.setSpacing(8);
        vBox.setPadding(new Insets(15, 12, 15, 12));
        TextField emailField = new TextField("email");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        TextField bioField = new TextField("bio");
        TextField nameField= new TextField("name");
        Button registerButton = new Button("Create Account");
        vBox.getChildren().addAll(emailField,passwordField,bioField,nameField,registerButton);
        registerButton.setOnAction(event->{
            userManager.register(emailField.getText(),passwordField.getText(),bioField.getText(),nameField.getText());
            if(userManager.isPlayerLoggedIn()){myStage.setScene(setUpMainScene());}
        });
    }
    private Scene setUpMainScene(){
        mainPane= new BorderPane();
        mainPane.setPrefSize(500,500);
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
        Menu fileMenu = new Menu("File");
        setFileMenu(fileMenu);
        Menu accountMenu = new Menu("Account");
        setAccountMenu(accountMenu);
        menuBar.getMenus().addAll(fileMenu,accountMenu);
        return menuBar;
    }
    private void setFileMenu(Menu menu){
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event->{System.exit(0);});
        menu.getItems().add(exitItem);
    }
    private void setAccountMenu(Menu menu){
        MenuItem logOutItem = new MenuItem("Log Out");
        logOutItem.setOnAction(event->{
            userManager.clear();
            myStage.setScene(getLoginScene());
        });
        MenuItem editAccountItem = new MenuItem("Edit Account Details");
        MenuItem socialItem=new MenuItem("Search People");
        socialItem.setOnAction(event -> {setUserLookupDialog();});
        editAccountItem.setOnAction(event->{setEditAccountDialog();});
        menu.getItems().addAll(logOutItem,editAccountItem,socialItem);
    }
    private void setEditAccountDialog(){

        TextField nameField=new TextField(userManager.getUserAttributes().get("name"));
        TextField bioField= new TextField(userManager.getUserAttributes().get("bio"));
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event->{
            try {
                userManager.updateInfo(nameField.getText(), bioField.getText());
                mainPane.setRight(userPaneManager.setUpAccountBox());
            }catch(ServerException e){
                launchErrorDialog(e.getException());
            }
        });
        VBox vBox= new VBox();
        vBox.getChildren().addAll(new Text("name"),nameField,new Text("bio"),bioField,saveButton);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profile");
        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();

    }
    private void setUserLookupDialog(){
        TextInputDialog dialog= new TextInputDialog("name");
        dialog.setHeaderText("Social Portal");
        dialog.setHeaderText("Find Other Users");
        dialog.setContentText("Enter user's name");
        Optional <String>result=dialog.showAndWait();
        result.ifPresent(name->{
                if(!name.equals("name")){
                    try{
                    setUpUserListDialog("Social Portal","Users",userManager.lookUpUsers(name));}
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
        vBox.setSpacing(8);
        vBox.getChildren().addAll(new Text("name: "+(String)user.get("name")),new Text("email: "+(String)user.get("email")),new Text("bio: "+(String)user.get("bio")));
        List<String>followers= new ArrayList<>();
        List<String>following= new ArrayList<>();
        userManager.parseArray((JSONArray)user.get("followers"),followers);
        userManager.parseArray((JSONArray)user.get("following"),following);
        Button followButton= new Button("Follow");
        if(userManager.getFollowers().contains((String)user.get("name"))){followButton.setText("Following");}
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
        alert.setTitle("Social Portal");
        alert.setHeaderText("User");
        alert.getDialogPane().setContent(vBox);
        alert.showAndWait();
    }
    private void launchErrorDialog(Exception e){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error");
        alert.getDialogPane().setContent(new VBox(new Text(e.getMessage())));
        alert.showAndWait();
    }
}
