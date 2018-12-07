package authoring.authoring_frontend.PopupWindows;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.SaveForm;

/**
 * SaveWindow
 *
 * @author brookekeene
 */
public class SaveWindow extends PopupWindow{
    public static int SIZE = 300;
    private SaveForm myContent;

    /**
     * Constructor
     */
    public SaveWindow(GameManager manager, int n) {
        super(manager, n);

        this.display(myResources.getString("GameInfo"));
        this.addContent();
    }

    /**
     * creates the UI elements needed to save a game
     */
    public void addContent() {
        myContent = new SaveForm(myManager);
        myRoot.getChildren().add(myContent);
    }
}
