package authoring.authoring_frontend.Forms;

import javafx.scene.control.TextField;

public class TextBox extends FormBox {
    private TextField myText;

    public TextBox(String label) {
        super(label);
    }

    @Override
    public void setContent() {
        myText = new TextField();
        this.getChildren().add(myText);
    }

    @Override
    public void getContent() {
        myText.getText();
    }

    @Override
    public boolean invalidEntry() {
        return myText.getText().isEmpty();
    }
}
