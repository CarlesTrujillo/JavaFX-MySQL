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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.stage.Stage;
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
       
    }    
    
    @FXML
    private void onClick_clientes(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("clientes.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onClick_productos(ActionEvent event) throws IOException {
    App.setRoot("productos");
    }

    @FXML
    private void onClick_ordenes(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("comandas.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        //stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void onClick_salir(ActionEvent event) {
    }

}
