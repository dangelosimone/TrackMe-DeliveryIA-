module unipa.prog {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    exports projectJava.mainJava.view.controller;
    opens projectJava.mainJava.view.controller to javafx.fxml;
    exports projectJava.mainJava;
    opens projectJava.mainJava to javafx.fxml;
}

//<ChoiceBox fx:id="senderChooser" layoutX="10.0" layoutY="10.0" prefWidth="150.0" AnchorPane.leftAnchor="293.0" AnchorPane.rightAnchor="40.0" AnchorPane.topAnchor="41.0" />