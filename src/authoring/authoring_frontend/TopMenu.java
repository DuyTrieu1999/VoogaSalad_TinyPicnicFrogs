package authoring.authoring_frontend;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;

import java.util.ResourceBundle;

/**
 * TopMenu
 *
 * Creates a MenuBar that allows user to select from various menus
 * of settings and basic functions
 *
 * @author brookekeene
 */
public class TopMenu extends HBox {
    public static final String DEFAULT_RESOURCE = "English";

    private MenuBar myMenu;
    private ResourceBundle myResources;

    /**
     * Constructor
     */
    public TopMenu() {
        myMenu = new MenuBar();
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);

        this.getChildren().add(myMenu);
        this.addAllMenus();
    }

    /**
     * calls methods which create dropdown menus within myMenu
     */
    private void addAllMenus() {
        addFileTab();
        addEditTab();
        addViewTab();
    }

    //TODO: refactor so that each add...Tab() calls an instance of a MenuChoice

    /**
     * creates new File menu with choices: New (Game, Prototype), Open
     */
    private void addFileTab() {
        Menu fileMenu = new Menu(myResources.getString("File"));

        Menu newSubmenu = new Menu(myResources.getString("New"));
        MenuItem newGame = new MenuItem(myResources.getString("Game"));
        MenuItem newActor = new MenuItem(myResources.getString("Prototype"));

        newGame.setOnAction(e -> {
            System.out.println("Open New AuthoringView"); //TODO: replace this with code
        });

        newActor.setOnAction(e -> {
            PrototypeWindow makeNewActor = new PrototypeWindow();
            System.out.println("Open Popup Window"); //TODO: replace this with code
        });

        newSubmenu.getItems().add(newGame);
        newSubmenu.getItems().add(newActor);

        MenuItem openItem = new MenuItem(myResources.getString("Open"));

        openItem.setOnAction(e -> {
            System.out.println("Open FileChooser"); //TODO: replace this with code
        });

        fileMenu.getItems().add(newSubmenu);
        fileMenu.getItems().add(openItem);

        myMenu.getMenus().add(fileMenu);
    }

    /**
     * creates new Edit menu with choices: Save
     */
    private void addEditTab() {
        Menu editMenu = new Menu(myResources.getString("Edit"));

        MenuItem saveItem = new MenuItem(myResources.getString("Save"));

        saveItem.setOnAction(e -> {
            System.out.println("Save current game"); //TODO: replace this with code
        });

        editMenu.getItems().add(saveItem);

        myMenu.getMenus().add(editMenu);
    }

    /**
     * creates new View menu with choices: Theme (Light, Dark)
     */
    private void addViewTab() {
        Menu viewMenu = new Menu(myResources.getString("View"));

        Menu themeSubmenu = new Menu(myResources.getString("Theme"));
        MenuItem lightTheme = new MenuItem(myResources.getString("Light"));
        MenuItem darkTheme = new MenuItem(myResources.getString("Dark"));

        lightTheme.setOnAction(e -> {
            System.out.println("Change stylesheet to light version"); //TODO: replace this with code
        });

        darkTheme.setOnAction(e -> {
            System.out.println("Change stylesheet to dark version"); //TODO: replace this with code
        });

        themeSubmenu.getItems().add(lightTheme);
        themeSubmenu.getItems().add(darkTheme);
        viewMenu.getItems().add(themeSubmenu);

        myMenu.getMenus().add(viewMenu);
    }
}
