package projectJava.mainJava.view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import projectJava.mainJava.MainApplication;

import java.awt.*;
import java.io.File;
import java.util.Stack;

public class MainController extends Stack<Controller> {
    @FXML
    private ScrollPane contentPane;


    public MainController() {
        MainApplication.setMainController(this);
    }

    @FXML
    public void initialize() {
        showView("/projectJava/mainJava/choiseView.fxml");
    }

    public void showView(String path) {
        contentPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentPane.setContent(MainApplication.loadView(path));}

    public void navigateBack() {
        pop();
        Controller controller = get(size()-1);
        contentPane.setContent(controller.getNode());
        controller.onResume();
    }


}
