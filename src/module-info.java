module voogasalad_tinypicnicfrogs {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires java.xml;
    requires json.simple;
    requires xstream;
    requires java.desktop;
    opens authoring.authoring_backend to xstream;
    opens authoring.authoring_frontend to xstream;
    opens engine.backend to xstream;
    opens engine.backend.Commands to xstream;
    opens engine.frontend.game_engine_UI to xstream;
    exports engine.backend;
    exports engine.controller;
    exports engine.frontend.game_engine_UI;
    opens engine.frontend.game_engine_UI.AnimationProcesser to xstream;
    opens engine.frontend.game_engine_UI.BattleWorld to xstream;
    opens engine.frontend.game_engine_UI.MenuView to xstream;
    opens engine.frontend.game_engine_UI.OverWorld to xstream;
    opens engine.frontend.game_engine_UI.SplashScreen to xstream;
    exports authoring.authoring_backend;
    exports authoring.authoring_frontend;
<<<<<<< HEAD
<<<<<<< HEAD
    exports authoring.authoring_frontend.Forms;
}
=======
    exports authoring.authoring_frontend.FormBoxes;
}
>>>>>>> 6db361009336f829fd6443e4bf9c42230b28c7a1
=======
    exports authoring.authoring_frontend.FormBoxes;
}

>>>>>>> d5d391061bc1ff132b7389f4eb4f85b5bb3f186c
