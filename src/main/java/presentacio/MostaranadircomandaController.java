
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import aplicacio.ComandaDetailsLogic;
import aplicacio.ComandasLogic;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import model.Comanda;
import model.ComandaDetails;



/**
 * FXML Controller class
 *
 * @author andre
 */
public class MostaranadircomandaController implements Initializable {
    private int idComandaSeleccionada;
    
    private int idProductoSeleccionado;
    
    private String idComanda;
    
    private ComandaDetailsLogic cdl;
    
    private ComandasLogic cl;
    
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_salir;
    
    @FXML
    private TableView tablaOrderDetails;
    
    @FXML 
    private TableColumn idProducto, cantidadPedida, precioProductoUnico;
     
    @FXML
    private Button btn_modificar;

    @FXML
    private Button btn_borrar;
    
    @FXML
    private Label precioTotal;

    @FXML
    private TextField fechaEnvio;

    @FXML
    private TextField emailCliente;

    @FXML
    private TextField fechaOrden;

    @FXML
    private TextField fechaEntrega;

    @FXML
    private TextField txtCredito;

    @FXML
    private TextField txtFecha;

    @FXML
    void onClick_salir(ActionEvent event) {

    }

    @FXML
    void onClick_anadir(ActionEvent event) {
        
        Boolean listaVacia = cdl.listaVacia();
        if (listaVacia) {
              mostrarAlertaError("No se puede añadir una comanda sin lineas de comanda");
        }
        
        try {
             int ret = cl.crearComanda(listaVacia, fechaOrden.getText(), fechaEntrega.getText(), fechaEnvio.getText(), emailCliente.getText());
             
             if (ret == 1) {
                 mostrarAlertaError("No se puede crear la comanda porque la diferencia de horas entre la fecha de la creación de la comanda y la fecha prevista de entrega es menor a minShippingHours");
            }
        } catch (SQLException ex) {
           mostrarAlertaError("Error al crear la comanda: " + ex.toString());
        }
    }

    @FXML
    void onClick_guardar(ActionEvent event) {

    }

    @FXML
    void onClick_borrar(ActionEvent event) {
         Double precioTot = Double.parseDouble(precioTotal.getText().split(" ")[0]);
        try {
            Double ret = cdl.restarImporteDeComandaDetailsEliminada(precioTot, idComandaSeleccionada, idProductoSeleccionado);
            cdl.borrarUnaComandaDetails(idComandaSeleccionada, idProductoSeleccionado);
            cdl.borrarUnaComandaDetailsdeTableview(idComandaSeleccionada, idProductoSeleccionado);
            precioTotal.setText(Double.toString(ret) + " €");
        } catch (SQLException ex) {
           mostrarAlertaError("Error al borrar el ComandaDetails: " + ex.toString());
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
            tablaOrderDetails.setItems(cdl.getLlistaObservableComandaDetails());
            
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }catch (Exception ex){
            mostrarAlertaError("Error inicialitzant capa lógica: " + ex.toString());
        }
        
        idProducto.setCellValueFactory(new PropertyValueFactory<>("CodigoProducto"));
        cantidadPedida.setCellValueFactory(new PropertyValueFactory<>("CantidadPedida"));
        precioProductoUnico.setCellValueFactory(new PropertyValueFactory<>("PrecioProducto"));
        
         tablaOrderDetails.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                ComandaDetails comandaDetailsSeleccionada = (ComandaDetails) tablaOrderDetails.getSelectionModel().getSelectedItem();
                idComandaSeleccionada = comandaDetailsSeleccionada.getNumeroComanda();
                idProductoSeleccionado = comandaDetailsSeleccionada.getCodigoProducto();
    }
        });
    }    

    public void setidComanda(String idComanda) {
        this.idComanda = idComanda;
        carrgarComandaDetails();
    }
    
    public void carrgarComandaDetails(){
        
         try {
             Comanda comanda = cl.cargarUnaComanda(idComanda);
             cargarDatosComanda(comanda);
             cdl.cargarComandaDetails(idComanda);
              double importe = cdl.importe();
             precioTotal.setText(Double.toString(importe) + " €");
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }
    }
    
    public void cargarDatosComanda(Comanda comanda) {
        
        fechaOrden.setText(quitarHoraDEFechas(comanda.getFechaOrden()));
        fechaEntrega.setText(quitarHoraDEFechas(comanda.getFechaRequerida()));
        fechaEnvio.setText(quitarHoraDEFechas(comanda.getFechaEnvio()));
        emailCliente.setText(comanda.getEmailCliente());
        emailCliente.setDisable(true);
    }
    
    public String quitarHoraDEFechas(String fecha){
        return fecha.split(" ")[0];
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
