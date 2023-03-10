
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import aplicacio.ComandaDetailsLogic;
import aplicacio.ComandasLogic;
import aplicacio.LogicaProducto;
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
import model.Producto;



/**
 * FXML Controller class
 *
 * @author andre
 */
public class MostaranadircomandaController implements Initializable {
    private int idComandaSeleccionada;
    
    private int idProductoSeleccionado;
    
    private String idComanda;
    
    private Boolean esModificar;
    
    private ComandaDetailsLogic cdl;
    
    private ComandasLogic cl;
    
    private LogicaProducto pl;
    
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
    private TextField nuevoProducto;

    @FXML
    private TextField cantidadProducto;

    @FXML
    void onClick_salir(ActionEvent event) {

    }

    @FXML
    void onClick_anadir(ActionEvent event) {
        
        Boolean listaVacia = cdl.listaVacia();
        Boolean maxOrderAmount = cdl.maxOrderAmount(Double.parseDouble(precioTotal.getText().split(" ")[0]));
        if (listaVacia) {
              mostrarAlertaError("No se puede a??adir una comanda sin lineas de comanda");
        } else if(maxOrderAmount){
             mostrarAlertaError("No se puede a??adir una comanda que tenga un importe mayor a maxOrderAmount");
        }
        
        try {
             int ret = cl.crearComanda(listaVacia, maxOrderAmount, fechaOrden.getText(), fechaEntrega.getText(), fechaEnvio.getText(), emailCliente.getText());
              if (ret == 1) {
                mostrarAlertaError("No se puede crear la comanda porque la diferencia de horas entre la fecha de la creaci??n de la comanda y la fecha prevista de entrega es menor a minShippingHours");            
            }else{
               cdl.insertarVariasComandasDetails(listaVacia, maxOrderAmount);
              }
        } catch (SQLException ex) {
           mostrarAlertaError("Error al crear la comanda: " + ex.toString());
        }
    }

    @FXML
    void onClick_guardar(ActionEvent event) {
       
        try {
            cl.modificarUnaComanda(Integer.parseInt(idComanda), fechaOrden.getText(), fechaEntrega.getText(), fechaEnvio.getText(), emailCliente.getText());
        } catch (SQLException ex) {
              mostrarAlertaError("Error al modificar la comanda: " + ex.toString());
        }
    }

    @FXML
    void onClick_borrar(ActionEvent event) {
         Double precioTot = Double.parseDouble(precioTotal.getText().split(" ")[0]);
         if(esModificar){
            try {
                Double ret = cdl.restarImporteDeComandaDetailsEliminada(precioTot, idComandaSeleccionada, idProductoSeleccionado);
                cdl.borrarUnaComandaDetails(idComandaSeleccionada, idProductoSeleccionado);
                cdl.borrarUnaComandaDetailsdeTableview(idComandaSeleccionada, idProductoSeleccionado);
                precioTotal.setText(Double.toString(ret) + " ???");
             } catch (SQLException ex) {
                mostrarAlertaError("Error al borrar el ComandaDetails: " + ex.toString());
            }
         } else{
              Double ret = cdl.restarImporteDeComandaDetailsEliminada(precioTot, idComandaSeleccionada, idProductoSeleccionado);
              cdl.borrarUnaComandaDetailsdeTableview(idComandaSeleccionada, idProductoSeleccionado);
              precioTotal.setText(Double.toString(ret) + " ???");
         }
    }
    
    @FXML
    void onClick_anadirProducto(ActionEvent event) {
        
        Producto producto = null;
        Double precioTot = Double.parseDouble(precioTotal.getText().split(" ")[0]);
        
        try {
            producto = LogicaProducto.getProductoByNombre(nuevoProducto.getText());
        } catch (SQLException ex) {
           mostrarAlertaError("Error al intentar encontrar el nuevo producto: " + ex.toString());
        }
        
        if (esModificar) {
            try {
                  if (producto.getCode() == 0) {
                     mostrarAlertaError("Error al intentar encontrar el producto indicado");
                   }
                  cdl.insertarUnComandaDetails(Integer.parseInt(idComanda), producto, Integer.parseInt(cantidadProducto.getText()));
                
                Double nuevoPrecio = cdl.sumarImporteDeComandaDetailsCreada(precioTot,Integer.parseInt(idComanda),  producto.getCode());
                if (nuevoPrecio != 0) {
                precioTotal.setText(Double.toString(nuevoPrecio) + " ???"); 
                }
              
                
            } catch (SQLException ex) {
                 mostrarAlertaError("Error al intentar insertar el nuevo ComandaDetails: " + ex.toString());
            }
            
        
        }else{
             if (producto.getCode() == 0) {
               mostrarAlertaError("Error al intentar encontrar el producto indicado");
              }
           cdl.insertarUnComandaDetailsenListaObservable(0, producto, Integer.parseInt(cantidadProducto.getText()));
            
            Double nuevoPrecio = cdl.sumarImporteDeComandaDetailsCreada(precioTot, 0,  producto.getCode());
            if (nuevoPrecio != 0) {
                precioTotal.setText(Double.toString(nuevoPrecio) + " ???"); 
            }
           
        }
        
        nuevoProducto.setText("");
         int defaultCantidad = 0;
        try {
            defaultCantidad = cdl.defaultQuantityOrdered();
        } catch (SQLException ex) {
            mostrarAlertaError("Error al intentar insertar el nuevo ComandaDetails: " + ex.toString());
        }
          cantidadProducto.setText(Integer.toString(defaultCantidad));
       
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       try {
            cl = new ComandasLogic();
            cdl = new ComandaDetailsLogic();
            int defaultCantidad = cdl.defaultQuantityOrdered();
            cantidadProducto.setText(Integer.toString(defaultCantidad));
            tablaOrderDetails.setItems(cdl.getLlistaObservableComandaDetails());
            double importe = cdl.importe();
            precioTotal.setText(Double.toString(importe) + " ???");
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }catch (Exception ex){
            mostrarAlertaError("Error inicialitzant capa l??gica: " + ex.toString());
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
    
     public void setesModificar(Boolean esModificar) {
        this.esModificar = esModificar;
         System.out.println(esModificar);
    }
    
    public void carrgarComandaDetails(){
        
         try {
             Comanda comanda = cl.cargarUnaComanda(idComanda);
             cargarDatosComanda(comanda);
             cdl.cargarComandaDetails(idComanda);
              double importe = cdl.importe();
             precioTotal.setText(Double.toString(importe) + " ???");
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
