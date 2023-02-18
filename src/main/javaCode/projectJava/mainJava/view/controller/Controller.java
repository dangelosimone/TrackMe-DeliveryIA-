package projectJava.mainJava.view.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import projectJava.mainJava.MainApplication;

/**
 * Classe astratta che contiene tutti i metodi di uso generale dei vari controller
 */
public abstract class Controller {
    @FXML
    public Node node;

    public Controller() {
        MainApplication.getMainController().push(this);
    }

    /**
     * ottenere il nodo della vista corrente.
     * @return il nodo della vista corrente.
     */
    public Node getNode() {
        return node;
    }

    /**
     * Metodo che viene invocato nel momento in cui la view viene visualizzata
     */
    public abstract void onResume();
}
