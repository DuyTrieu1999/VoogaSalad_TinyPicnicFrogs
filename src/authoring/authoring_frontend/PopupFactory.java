package authoring.authoring_frontend;

import authoring.authoring_backend.GameManager;
import authoring.authoring_frontend.PopupWindows.*;

class PopupFactory {
    private final static int PROTOTYPE_SIZE = 500;
    private final static int MESSAGE_SIZE = 300;
    private final static int SAVE_SIZE = 200;

    static PopupWindow getPopup(String type, GameManager manager, ActorManager actorManager) {
        if("prototype".equalsIgnoreCase(type)) {
            return new PrototypeWindow(manager, PROTOTYPE_SIZE, actorManager);
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
