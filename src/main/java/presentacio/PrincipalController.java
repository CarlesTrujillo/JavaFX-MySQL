/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
/**
 * FXML Controller class
 *
 * @author Albert
 */
public class PrincipalController implements Initializable {


    @FXML
    private Button btn_Clientes;
    @FXML
    private Button btn_productos;
    @FXML
    private Button btn_ordenes;
    @FXML
    private Button btn_salir;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onClick_clientes(ActionEvent event) throws IOException {
    App.setRoot("clientes");
    }

    @FXML
    private void onClick_productos(ActionEvent event) throws IOException {
    App.setRoot("productos");
    }

    @FXML
    private void onClick_ordenes(ActionEvent event) throws IOException {
    App.setRoot("comandas");
    }

    @FXML
    private void onClick_salir(ActionEvent event) {
    }

}
