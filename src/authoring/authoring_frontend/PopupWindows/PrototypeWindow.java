package authoring.authoring_frontend.PopupWindows;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.PrototypeForm;
import javafx.scene.control.ScrollPane;

/**
 * Prototype Window
 *
 * Creates a popup window that allows the user to create a
 * new Actor Prototype
 *
 * @author brookekeene
 */
public class PrototypeWindow extends PopupWindow {
    //    public static final String DEFAULT_STYLESHEET = "light.css";
    private int size;
    private PrototypeForm myContent;

    /**
     * Constructor
     */
    public PrototypeWindow(GameManager manager, int n) {
        super(manager, n);
        size = n;

        this.addContent();
        this.display(myResources.getString("NewPrototype"));
    }

    /**
     * creates the elements needed to get the data for a prototype
     */
    public void addContent() {
        ScrollPane mySP = new ScrollPane();
        myContent = new PrototypeForm(myManager);
        mySP.setContent(myContent);
        mySP.setPrefSize(size, size);
        myRoot.getChildren().add(mySP);
    }
}

