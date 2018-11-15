module voogasalad_tinypicnicfrogs {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires java.xml;
    requires json.simple;

    exports engine.backend;
    exports engine.frontend;
    exports engine.controller;
    exports engine.frontend.game_engine_UI;
}