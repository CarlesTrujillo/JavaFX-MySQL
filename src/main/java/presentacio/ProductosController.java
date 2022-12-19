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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Producto;

/**
 * @author D
 * @version 1.0
 *
 * Controlador Pantalla Productos
 */
public class ProductosController implements Initializable {

    private int idProductoSeleccionado;

    // Botones
    @FXML
    private Button btn_edit;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_delete;

    // TextFields
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDesc;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtPrecio;

    @FXML
    private TableView<Producto> tablaProductos;

    // Columnas
    @FXML
    private TableColumn idCol, nombreCol, descCol, stockCol, precioCol;

    ObservableList<Producto> llistaObservableProductos = FXCollections.observableArrayList();

    @FXML
    private void onClickEdit(ActionEvent event) {
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
        insertarProducto();
    }

    @FXML
    private void onClickDelete(ActionEvent event) throws SQLException {
        eliminarProducto();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ArrayList<Producto> productos = aplicacio.LogicaProducto.getProductos();

            for (Producto s : productos) {
                llistaObservableProductos.add(new Producto(s.getCode(), s.getNombre(), s.getDescripcion(), s.getCantidadStock(), s.getPrecio()));
            }
            idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
            nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            descCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            stockCol.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
            tablaProductos.setItems(llistaObservableProductos);
            dades.DataSource.getConnection("m03uf6_22_23", "root", "123456");
        } catch (SQLException ex) {

        }
    }

    // Función de selección de elemento en la tabla
    @FXML
    void onItem_Selected(MouseEvent event) {
        Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
        
        if(producto != null) {
            txtNombre.setText(producto.getNombre());
            txtDesc.setText(producto.getDescripcion());
            txtStock.setText(producto.getCantidadStock() + "");
            txtPrecio.setText(producto.getPrecio() + "");
        }
    }
    
    // Función para insertar un producto
    public void insertarProducto() {
        Producto producto = new Producto();
        producto.setNombre(txtNombre.getText());
        producto.setDescripcion(txtDesc.getText());
        byte cantidadStock = Byte.parseByte(txtStock.getText());
        producto.setCantidadStock(cantidadStock);
        double precio = Double.parseDouble(txtPrecio.getText());
        producto.setPrecio(precio);

        try {
            dades.DataSource.getConnection("m03uf6_22_23", "root", "123456");
            aplicacio.LogicaProducto.setProducto(producto);
            llistaObservableProductos.add(producto);
            tablaProductos.refresh();
            limpiarCampos();
            
        } catch (SQLException ex) {

        }

    }
    
    // Función para eliminar un producto de la BBDD
    public void eliminarProducto() throws SQLException {
        Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
        LogicaProducto.deleteProducto(producto);
        llistaObservableProductos.remove(producto);
        tablaProductos.refresh();
        limpiarCampos();
    }
    
    // Función para dejar en blanco los TextField
    public void limpiarCampos() {
        txtNombre.clear();
        txtDesc.clear();
        txtPrecio.clear();
    }

}
