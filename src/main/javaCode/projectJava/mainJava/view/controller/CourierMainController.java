package projectJava.mainJava.view.controller;
import javafx.fxml.FXML;
import projectJava.mainJava.MainApplication;

public class CourierMainController extends Controller{
    @Override
    public void onResume() {}

    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/choiseView.fxml");
    }

    @FXML
    public void loadLogin(){
        MainApplication.getMainController().showView("/projectJava/mainJava/loginView.fxml");
    }

    @FXML
    public void loadRegister(){
        MainApplication.getMainController().showView("/projectJava/mainJava/signupView.fxml");
    }
}
