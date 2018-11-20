package authoring.authoring_frontend.Forms;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;



/**
 * FormBox
 *
 * @author brookekeene
 */
public abstract class FormBox extends HBox {
    protected Label myLabel;

    public FormBox(String label) {
        myLabel = new Label(label);
        this.getChildren().add(myLabel);
        this.setContent();
    }

    public abstract void setContent();

    public abstract void getContent();

    public abstract boolean invalidEntry();


}
