package player;

import engine.frontend.game_engine_UI.Main;

/**
 * There is a main method that can run the game engine
 */
public class PlayerMain {
    public static void main(String[] args) throws Exception {
       // Main.main(new String[0]);
        ServerManager serverManager = new ServerManager();
        serverManager.testConnection();
        serverManager.login("robertDuval@duke.edu","voogaFML");
        serverManager.register("michaelGlushakov@duke.edu","voogaAAAA!!!","Prattstar","Michael Glushakov");
        serverManager.login("michaelGlushakov@duke.edu","voogaAAAA!!!");
        serverManager.updateUser("michaelGlushakov@duke.edu","voogaAAAA!!!","Future Pratt dropout","Michael Glushakov");
    }
}
