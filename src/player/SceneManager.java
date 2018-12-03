package player;

import engine.backend.Commands.Command;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;





public class SceneManager {
    private Stage myStage;
    private UserProfileManager userManager;
    public SceneManager(UserProfileManager manager, Stage stage){
        userManager=manager;
        myStage=stage;
        myStage.setScene(getLoginScene());
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
        BorderPane pane = new BorderPane();
        pane.setPrefSize(500,500);
        Scene mainScene= new Scene(pane);
        pane.setRight(setUpAccountBox());
        HBox hBox= new HBox();
        hBox.getChildren().addAll(setUpMenuBar());
        pane.setTop(hBox);
        return mainScene;
    }
    private VBox setUpAccountBox(){
        VBox accountBox=new VBox();
        accountBox.setSpacing(8);
        accountBox.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        ImageView profileView = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("profile_icon.png")));
        profileView.setFitWidth(100);
        profileView.setFitHeight(100);
        accountBox.getChildren().add(profileView);
        for(String key:userManager.getUserAttributes().keySet()){
           Text text = new Text(key+": "+userManager.getUserAttributes().get(key));
           text.setFont(Font.font("Veranda",20));
           accountBox.getChildren().add(text);
       }
        return accountBox;
    }

    private MenuBar setUpMenuBar(){
        MenuBar menuBar= new MenuBar();
        menuBar.setPrefWidth(myStage.getWidth());
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
        menu.getItems().addAll(logOutItem,editAccountItem);
    }

}
