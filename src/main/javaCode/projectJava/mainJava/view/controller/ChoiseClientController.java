package projectJava.mainJava.view.controller;

import javafx.fxml.FXML;
import projectJava.mainJava.MainApplication;


public class ChoiseClientController extends Controller {
    /**
     * Passa alla visualizzazione contenuta nel "showView()" quando si fa clic sul pulsante a cui la "send()" é associata nel .fxml
     */
    @FXML
    public void send() {
        MainApplication.getMainController().showView("/projectJava/mainJava/sendView.fxml");
    }

    /**
     * Passa alla visualizzazione contenuta nel "showView()" quando si fa clic sul pulsante a cui la "track()" é associata nel .fxml
     */
    @FXML
    public void track() {
        MainApplication.getMainController().showView("/projectJava/mainJava/trackView.fxml");
    }

    /**
     * Passa alla visualizzazione contenuta nel "showView()" quando si fa clic sul pulsante a cui la "loadBack()" é associata nel .fxml
     * Principalemente per tornare al file.fxml precedente
     */
    @FXML
    public void loadBack(){
        MainApplication.getMainController().showView("/projectJava/mainJava/choiseView.fxml");
    }

    @Override
    public void onResume() {}
}
