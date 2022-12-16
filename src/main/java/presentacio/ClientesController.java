/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author Albert
 */
public class ClientesController implements Initializable {


    @FXML
    private Button btn_salir;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_borrar;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDni;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtCredito;
    @FXML
    private TextField txtFecha;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void onClick_salir(ActionEvent event) {
    }

    @FXML
    private void onClick_añadir(ActionEvent event) {
    }

    @FXML
    private void onClick_modificar(ActionEvent event) {
    }

    @FXML
    private void onClick_borrar(ActionEvent event) {
    }

}
