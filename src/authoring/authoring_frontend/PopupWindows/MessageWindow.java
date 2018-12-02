package authoring.authoring_frontend.PopupWindows;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.MessageForm;

/**
 * Message Window
 *
 * @author janiceliu
 * @author brookekeene
 */
public class MessageWindow extends PopupWindow{
    private int size;
    private MessageForm myMessageForm;

    /**
     * Constructor
     */
    public MessageWindow(GameManager manager, int n) {
        super(manager, n);
        size = n;

        this.display(myResources.getString("CreateMessage"));
        this.addContent();
    }


    /**
     * creates UI elements needed to get and save new
     * message information
     */
    public void addContent() {
        myMessageForm = new MessageForm(myManager);
        myRoot.getChildren().add(myMessageForm);
    }
}
