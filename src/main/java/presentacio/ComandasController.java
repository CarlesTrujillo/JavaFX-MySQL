/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import aplicacio.ComandaDetailsLogic;
import aplicacio.ComandasLogic;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Comanda;

/**
 * FXML Controller class
 *
 * @author andre
 */
public class ComandasController implements Initializable {
    
    private ComandasLogic cl;
    
    private ComandaDetailsLogic cdl;
    
    private int idComandaSeleccionada;
    
    @FXML
    private TextField fechaInicial;
    
    @FXML
    private TextField fechaFinal;
     
    @FXML
    private TableView tablaComandas;
    
    @FXML 
    private TableColumn idComanda, fechaComanda, fechaEntrega, fechaEnvio, emailCliente;
    
    @FXML
    private Button resetear;
    
    @FXML
    private Button btn_add;
    
      @FXML
    private Button filtrar;

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
    void onClick_anadir(ActionEvent event) {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mostaranadircomanda.fxml"));
        Parent root = null;
        
        try {
            root = fxmlLoader.load();
            
        } catch (IOException | IllegalStateException ex) {
             mostrarAlertaError("Error al intentar inizializar la pantalla de mostrar detalles de la comanda: " + ex.toString());
        }
        
        MostaranadircomandaController  mostaranadircomandaController = fxmlLoader.getController();
          mostaranadircomandaController.setesModificar(false);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    void onClick_modificar(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mostaranadircomanda.fxml")); 
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException | IllegalStateException ex) {           
            mostrarAlertaError("Error al intentar inizializar la pantalla de mostrar detalles de la comanda: " + ex.toString());
        }
        MostaranadircomandaController  mostaranadircomandaController = fxmlLoader.getController();
        mostaranadircomandaController.setidComanda(Integer.toString(idComandaSeleccionada));
        mostaranadircomandaController.setesModificar(true);
            
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        
      
    }

    @FXML
    void onClick_borrar(ActionEvent event) {
         try {
              cdl.borrarTodasComandaDetails(idComandaSeleccionada);
              cl.borrarComanda(idComandaSeleccionada);
              cl.borrarComandaEnTableView(idComandaSeleccionada);
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }
    }
    
    @FXML
    void onClick_filtrar(ActionEvent event) {
        
        Date fechaIni = convertirADate(fechaInicial.getText());
        Date fechaFi = convertirADate(fechaFinal.getText());
        try {
            cl.caregarComandasFiltradasPorFecha(fechaIni, fechaFi);
        } catch (SQLException ex) {
             mostrarAlertaError("Error carregant dades: " + ex.toString());
        } catch (Exception ex) {
              mostrarAlertaError("Error en el formato de las fechas, el formato de las fechas debe ser (YYYY-MM-DD) : " + ex.toString());
        }
    }
    
    @FXML
    void onClick_resetear(ActionEvent event) {
          try {
             cl.caregarComandas();
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }
    }

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cl = new ComandasLogic();
            cdl = new ComandaDetailsLogic();
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
             cl.caregarComandas();
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }
         
        tablaComandas.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                Comanda comandaSeleccionada = (Comanda) tablaComandas.getSelectionModel().getSelectedItem();
                idComandaSeleccionada = comandaSeleccionada.getNumeroOrden();
    }
        });
    }
    
    private Date convertirADate(String fecha){
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(fecha);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
        return fechaDate;
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
