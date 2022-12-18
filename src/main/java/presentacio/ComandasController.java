/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import aplicacio.ComandasLogic;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class ComandasController implements Initializable {
    
    private ComandasLogic cl;
    
     @FXML
    private TableView tablaComandas;
    
    @FXML 
    private TableColumn idComanda, fechaComanda, fechaEntrega, fechaEnvio, emailCliente;
    
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_salir;

    @FXML
    private Button btn_modificar;

    @FXML
    private Button btn_borrar;

    @FXML
    void onClick_salir(ActionEvent event) {

    }

    @FXML
    void onClick_añadir(ActionEvent event) {

    }

    @FXML
    void onClick_modificar(ActionEvent event) {

    }

    @FXML
    void onClick_borrar(ActionEvent event) {

    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cl = new ComandasLogic();

            tablaComandas.setItems(cl.getLlistaObservableComanda());
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }catch (Exception ex){
            mostrarAlertaError("Error inicialitzant capa lógica: " + ex.toString());
        }
        
        idComanda.setCellValueFactory(new PropertyValueFactory<>("NumeroOrden"));
        fechaComanda.setCellValueFactory(new PropertyValueFactory<>("FechaOrden"));
        fechaEntrega.setCellValueFactory(new PropertyValueFactory<>("FechaRequerida"));
        fechaEnvio.setCellValueFactory(new PropertyValueFactory<>("FechaEnvio"));
        emailCliente.setCellValueFactory(new PropertyValueFactory<>("EmailCliente"));
        
         try {
             cl.carregarComandas();
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }
    }
    
     private void mostrarAlertaError(String txt)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("ERROR");
        alert.setContentText(txt);

        alert.showAndWait();
    }
    
}
