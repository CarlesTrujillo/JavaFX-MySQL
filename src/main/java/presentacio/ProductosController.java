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
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.Producto;

/**
 * @author D
 * @version 1.0
 *
 * Controlador Pantalla Productos
 */
public class ProductosController implements Initializable {

    // Varios
    private int idProductoSeleccionado;

    ObservableList<Producto> llistaObservableProductos = FXCollections.observableArrayList();

    @FXML
    private TableView<Producto> tablaProductos;

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

    // Columnas
    @FXML
    private TableColumn idCol, nombreCol, descCol, stockCol, precioCol;

    /**
     * Funciones de los clics de los botones que se encuentran en la pantalla
     * Productos
     *
     * @param event
     * @throws SQLException
     */
    @FXML
    private void onClickEdit(ActionEvent event) throws SQLException {
        modificarProducto();
    }

    @FXML
    private void onClickAdd(ActionEvent event) {
        insertarProducto();
    }

    @FXML
    private void onClickDelete(ActionEvent event) throws SQLException {
        eliminarProducto();
    }

    @FXML
    void onKey_Insert(KeyEvent event) throws SQLException {

        /**
         * Hotkey para insertar un usuario.
         */
        if (event.getCode() == KeyCode.ENTER) {
            insertarProducto();
        }

        /**
         * Hotkey para cerrar la ventana.
         */
        if (event.getCode() == KeyCode.ESCAPE) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Hotkey para el boton de borrado.
     */
    @FXML
    void onKey_Delete(KeyEvent event) throws SQLException {
        if (event.getCode() == KeyCode.DELETE) {
            eliminarProducto();
        }
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

            llistaObservableProductos.setAll(productos);

            idCol.setCellValueFactory(new PropertyValueFactory<>("code"));
            nombreCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            descCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            stockCol.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));
            precioCol.setCellValueFactory(new PropertyValueFactory<>("precio"));
            tablaProductos.setItems(llistaObservableProductos);
            dades.DataSource.getConnection("m03uf6_22_23", "root", "123456");
            /**
             * Ejecutamos el metodo de limpiar campos desde el inicio ya que así
             * tendremos la cantidad predeterminada de stock que tenemos
             * almacenada en nuestra BBDD cargada automaticamente al abrir
             * nuestra pantalla
             */
            limpiarCampos();

        } catch (SQLException ex) {

        }
    }

    /**
     * Esta función recoge el valor seleccionado por el usuario en la lista para
     * luego poder trabajar con ello
     *
     * @param event
     */
    @FXML
    void onItem_Selected(MouseEvent event) {
        Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();

        if (producto != null) {
            txtNombre.setText(producto.getNombre());
            txtDesc.setText(producto.getDescripcion());
            txtStock.setText(producto.getCantidadStock() + "");
            txtPrecio.setText(producto.getPrecio() + "");
        }
    }

    /**
     * Función para insertar un producto a la base de datos
     */
    public void insertarProducto() {
        Producto producto = new Producto();
        producto.setNombre(txtNombre.getText());
        producto.setDescripcion(txtDesc.getText());
        int cantidadStock = Integer.parseInt(txtStock.getText());
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
            mostrarAlertaError("Error carregant dades: " + ex.toString());
        } catch (RuntimeException ex) {
            alertaCampoVacio("Los campos no deben estar vacíos" + ex.toString());
        }
    }

    /**
     * Función para modificar un producto de la BBDD
     *
     * @throws SQLException
     */
    public void modificarProducto() throws SQLException {
        Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
        if ((producto != null) && (noEstaVacio())) {
            int posicion = tablaProductos.getSelectionModel().getSelectedIndex();
            Producto nuevoProducto = new Producto();
            nuevoProducto.setNombre(txtNombre.getText());
            nuevoProducto.setDescripcion(txtDesc.getText());
            int cantidadStock = Integer.parseInt(txtStock.getText());
            nuevoProducto.setCantidadStock(cantidadStock);
            nuevoProducto.setPrecio(Double.parseDouble(txtPrecio.getText()));
            aplicacio.LogicaProducto.updateProducto(nuevoProducto);
            llistaObservableProductos.set(posicion, nuevoProducto);
            tablaProductos.refresh();
            limpiarCampos();
        }
    }

    /**
     * Función para eliminar un producto de la BBDD
     *
     * @throws SQLException
     */
    public void eliminarProducto() throws SQLException {
        Producto producto = (Producto) tablaProductos.getSelectionModel().getSelectedItem();
        LogicaProducto.deleteProducto(producto);
        llistaObservableProductos.remove(producto);
        tablaProductos.refresh();
        limpiarCampos();
    }

    /**
     * Función para dejar en blanco los TextField
     *
     * @throws SQLException
     */
    public void limpiarCampos() throws SQLException {
        txtNombre.clear();
        txtDesc.clear();
        txtPrecio.clear();
        txtStock.setText(LogicaProducto.getDefaultStock() + "");
    }

    /**
     * Función para comprobar que los campos no estén vacíos
     *
     * @return
     */
    public boolean noEstaVacio() {
        return (txtNombre.getText() != null) && (txtDesc.getText() != null)
                && (txtStock.getText() != null) && (txtPrecio.getText() != null);
    }

    /**
     * Alerta por fallo con la conexión a la base de datos
     *
     * @param txt
     */
    private void mostrarAlertaError(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("ERROR");
        alert.setContentText(txt);

        alert.showAndWait();
    }

    /**
     * Alerta por error al no escribir nada en los campos
     *
     * @param txt
     */
    private void alertaCampoVacio(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setTitle("ERROR");
        alert.setContentText(txt);

        alert.showAndWait();
    }
}
