package authoring.authoring_frontend.PopupWindows;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.Forms.SaveForm;
import javafx.scene.control.ScrollPane;

/**
 * SaveWindow
 *
 * @author brookekeene
 */
public class SaveWindow extends PopupWindow{
    private int size;

    /**
     * Constructor
     */
    public SaveWindow(GameManager manager, int n) {
        super(manager, n);
        size = n;

        this.display(myResources.getString("GameInfo"));
        this.addContent();
    }

    /**
     * creates the UI elements needed to save a game
     */
    public void addContent() {
        ScrollPane mySP = new ScrollPane();
        myContent = new SaveForm(myManager);
        mySP.setContent(myContent);
        mySP.setPrefSize(size, size);
        myRoot.getChildren().add(mySP);
    }
}
