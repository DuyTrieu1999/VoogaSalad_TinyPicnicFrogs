module voogasalad_tinypicnicfrogs {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.web;
    requires java.xml;
    requires json.simple;
    requires java.desktop;
    exports engine.backend;
    exports engine.frontend;
    exports engine.controller;
    exports authoring.authoring_backend;
    exports authoring.authoring_frontend;
    exports authoring.authoring_frontend.Forms;
}