package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.PopupWindows.*;

public class PopupFactory {
    public static int PROTOTYPE_SIZE = 500;
    public static int MESSAGE_SIZE = 300;
    public static int SAVE_SIZE = 200;

    public static PopupWindow getPopup(String type, GameManager manager) {
        if("prototype".equalsIgnoreCase(type)) {
            return new PrototypeWindow(manager, PROTOTYPE_SIZE);
        }
        else if("message".equalsIgnoreCase(type)) {
            return new MessageWindow(manager, MESSAGE_SIZE);
        }
        else if("save".equalsIgnoreCase(type)) {
            return new SaveWindow(manager, SAVE_SIZE);
        }
        return null;
    }
}
