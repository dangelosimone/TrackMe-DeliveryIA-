package projectJava.mainJava.view.controller;

import javafx.fxml.FXML;
import projectJava.mainJava.MainApplication;

public class ChoiseCourierController extends Controller {
    @FXML
    public void update() {
        MainApplication.getMainController().showView("/projectJava/mainJava/courierView.fxml");
    }

    @FXML
    public void history() {
        MainApplication.getMainController().showView("/projectJava/mainJava/userView.fxml");
    }
    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/loginView.fxml");
    }
    @Override
    public void onResume() {}

}
