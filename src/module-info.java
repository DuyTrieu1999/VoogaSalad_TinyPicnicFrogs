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
    //requires groovy.all;

    opens authoring.authoring_backend to xstream;
    opens engine.backend to xstream;
    opens engine.backend.Commands to xstream;
    opens engine.frontend.game_engine_UI to xstream;
    exports engine.backend;
    exports engine.controller;
    exports engine.frontend.game_engine_UI;
    exports authoring.authoring_backend;
    exports authoring.authoring_frontend;
    exports authoring.authoring_frontend.Forms;
}