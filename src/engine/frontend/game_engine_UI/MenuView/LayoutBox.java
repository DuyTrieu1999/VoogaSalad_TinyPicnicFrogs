package engine.frontend.game_engine_UI.MenuView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LayoutBox extends HBox {
    private ToggleButton button;
    private BooleanProperty buttonSelected;

    public LayoutBox (String command) {
        buttonSelected = new SimpleBooleanProperty(button.isSelected());
        button = new ToggleButton(command);
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().setAll(button);
        this.getChildren().add(layout);
    }
    public BooleanProperty activeProperty () {
        return buttonSelected;
    }
}
