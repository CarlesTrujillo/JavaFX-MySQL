package presentacio;

import aplicacio.LogicaProducto;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Producto;

/**
 * @author D
 * @version 1.0
 * 
 * Controlador Pantalla Productos
 */

public class ProductosController implements Initializable {

    private LogicaProducto cl;
    
    private int idProductoSeleccionado;

    // Botones
    @FXML
    private Button btn_atras;
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_delete;
    
    @FXML
    private TableView<Producto> tablaProductos;
    
    // Columnas
    @FXML 
    private TableColumn idCol, nameCol, descCol, stockCol, precioCol;
    
    ObservableList<Producto> llistaObservableProductos = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void onClickAtras(ActionEvent event) {
    }

    @FXML
    private void onClickEdit(ActionEvent event) {
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
    }

    @FXML
    private void onClickDelete(ActionEvent event) {
        /*try {
              c1.(idProductoSeleccionado);
              cl.borrarComandaEnTableView(idProductoSeleccionado);
        }catch(SQLException ex){
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        }*/
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<Producto> productos = aplicacio.LogicaProducto.getProductos();
                        
            for (Producto s : productos) {
                llistaObservableProductos.add(new Producto(s.getCode(), s.getNombre(),s.getDescripcion(),s.getCantidadStock(),s.getPrecio()));
            }
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            descCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            stockCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
            tablaProductos.setItems(llistaObservableProductos);
            dades.DataSource.getConnection("m03uf6_22_23","root","123456");
        } catch(SQLException ex) {
            
        }
    }    
    
}
