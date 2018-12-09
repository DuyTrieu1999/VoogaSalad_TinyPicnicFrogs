package authoring.authoring_frontend.PopupWindows;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.Forms.MessageForm;

/**
 * Message Window
 *
 * @author janice
 * @author brookekeene
 */
public class MessageWindow extends PopupWindow{

    /**
     * Constructor
     */
    public MessageWindow(GameManager manager, int n) {
        super(manager, n);

        this.display(myResources.getString("CreateMessage"));
        this.addContent();
    }


    /**
     * creates UI elements needed to get and save new
     * message information
     */
    public void addContent() {
        myContent = new MessageForm(myManager);
        myRoot.getChildren().add(myContent);
    }
}
