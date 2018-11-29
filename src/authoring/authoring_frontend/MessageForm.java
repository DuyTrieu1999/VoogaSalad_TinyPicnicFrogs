package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.Forms.FormBox;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MessageForm extends VBox {
    public static final String DEFAULT_RESOURCE = "English";
    public static int SIZE = 500;
    public static int PADDING = 10;
    private ResourceBundle myResources;
    private TextField prototypeName;

    private GameManager myManager;

    /**
     * Constructor
     */
    public MessageForm(GameManager manager) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE);
        myManager = manager;

        this.setMaxSize(SIZE, SIZE);
        this.setPadding(new Insets(PADDING));
        this.addAllFields();
    }

    public void addAllFields(){

    }
}
