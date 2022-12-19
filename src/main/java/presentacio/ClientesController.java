/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import aplicacio.LogicaCliente;
import model.Cliente;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, String> emailCol;
    @FXML
    private TableColumn<Cliente, String> dniCol;
    @FXML
    private TableColumn<Cliente, String> nameCol;
    @FXML
    private TableColumn<Cliente, String> phoneCol;
    @FXML
    private TableColumn<Cliente, Double> creditCol;
    @FXML
    private TableColumn<Cliente, String> birthCol;

    ObservableList<Cliente> llistaObservableClientes = FXCollections.observableArrayList();
                
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {    
            ArrayList<Cliente> clientes = aplicacio.LogicaCliente.getClientes();
                        
            for (Cliente s : clientes) {
                llistaObservableClientes.add(new Cliente(s.getEmail(), s.getDni(),s.getNombre(),s.getTelefono(),s.getCreditoLimite(),s.getFechaNacimiento()));
            }
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            dniCol.setCellValueFactory(new PropertyValueFactory<>("dni"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("telefono"));
            creditCol.setCellValueFactory(new PropertyValueFactory<>("creditoLimite"));
            birthCol.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
            tablaClientes.setItems(llistaObservableClientes);
            dades.DataSource.getConnection("m03uf6_22_23","root","123456");
            
        } catch (SQLException ex) {
            
//            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    }

    @FXML
    private void onClick_salir(ActionEvent event) {
    
    }
    
    @FXML
    void onKey_Insert(KeyEvent event) {
        
        /***
         * Hotkey para insertar un usuario.
         */
        
        if(event.getCode() == KeyCode.ENTER){
            insertarUsuario();
        }
        
        /***
         * Hotkey para cerrar la ventana.
         */
        if(event.getCode() == KeyCode.ESCAPE){
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }   
    }
    
    /***
     * Hotkey para el boton de borrado.
     */
    @FXML
    void onKey_Delete(KeyEvent event) throws SQLException {
        if(event.getCode() == KeyCode.DELETE){
            eliminarUsuario();
        }   
    }
    
    /***
     * Funcion para la seleccion de un usuario y el mostrado en los campos de texto.
     * 
     * @param event 
     */
    @FXML
    void onItem_Selected(MouseEvent event) {
        Cliente cliente = (Cliente) tablaClientes.getSelectionModel().getSelectedItem();
        if(cliente!=null){
            txtEmail.setText(cliente.getEmail());
            txtDni.setText(cliente.getDni());
            txtTelefono.setText(cliente.getTelefono());
            txtNombre.setText(cliente.getNombre());
            txtCredito.setText(cliente.getCreditoLimite() + "");
            txtFecha.setText(cliente.getFechaNacimiento());
        }else{
        limpiarCampos();
        }
    }

    /***
     * Funcion para el boton de añadir usuario.
     * @param event 
     */
    @FXML
    private void onClick_añadir(ActionEvent event){
        insertarUsuario();
    }
    
    /***
     * Accion para el boton de modificar usuario.
     * 
     * @param event 
     */
    @FXML
    private void onClick_modificar(ActionEvent event) throws SQLException {
        Cliente cliente = (Cliente) tablaClientes.getSelectionModel().getSelectedItem();
        if(cliente != null){
            int posicion = tablaClientes.getSelectionModel().getSelectedIndex();
            Cliente nuevoCliente = new Cliente();
            nuevoCliente.setNombre(txtNombre.getText());
            nuevoCliente.setDni(txtDni.getText());
            nuevoCliente.setEmail(txtEmail.getText());
            nuevoCliente.setFechaNacimiento(txtFecha.getText());
            nuevoCliente.setCreditoLimite(Double.parseDouble(txtCredito.getText()));
            nuevoCliente.setTelefono(txtTelefono.getText());
            aplicacio.LogicaCliente.updateCliente(nuevoCliente);
            llistaObservableClientes.set(posicion, nuevoCliente);
            tablaClientes.refresh();
        }
    }
    
    

    /***
     * Accion para el boton de borrar usuario.
     * 
     * @param event
     * @throws SQLException 
     */
    @FXML
    private void onClick_borrar(ActionEvent event) throws SQLException {
        eliminarUsuario();
    }
    
    @FXML
    void onClick_Pane(ActionEvent event) {
        limpiarCampos();
    }
    
    
    /***
     * Funcion para hacer la comprobacion de la edad.
     * 
     * @param fecha
     * @return
     * @throws SQLException 
     */
    public static boolean comprobarEdad(String fecha) throws SQLException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha1 = LocalDate.parse(fecha, formatter);
        LocalDate fecha2 = LocalDate.now();
        int edadMin = Integer.parseInt(LogicaCliente.consultarEdadMinima());
        
        Period period = Period.between(fecha1,fecha2);
        
        if(period.getYears()>edadMin){
            return true;
        }else{
            return false;
        }
    }
    
    /***
     * Funcion para limpiar los campos de texto despues de un Insert, Update, Delete.
     * 
     */
    public void limpiarCampos(){
        txtEmail.clear();
        txtDni.clear();
        txtTelefono.clear();
        txtNombre.clear();
        txtCredito.clear();
        txtFecha.clear();
    }
    /***
     * Funcion para insertar un usuario.
     */
    public void insertarUsuario(){
        Cliente cliente = new Cliente();
        cliente.setEmail(txtEmail.getText());
        cliente.setDni(txtDni.getText());
        cliente.setNombre(txtNombre.getText());
        cliente.setTelefono(txtTelefono.getText());
        double credito = Double.parseDouble(txtCredito.getText());
        cliente.setCreditoLimite(credito);
        cliente.setFechaNacimiento(txtFecha.getText());       
        try {
            dades.DataSource.getConnection("m03uf6_22_23","root","123456");
            aplicacio.LogicaCliente.setCliente(cliente);
            llistaObservableClientes.add(cliente);
            tablaClientes.refresh();
            limpiarCampos();
        } catch (SQLException ex) {
        }
    }
    
    /***
     * Funcion para eliminar un usuario.
     */
    public void eliminarUsuario() throws SQLException{
        Cliente cliente = (Cliente) tablaClientes.getSelectionModel().getSelectedItem();
        LogicaCliente.deleteCliente(cliente);
        llistaObservableClientes.remove(cliente);
        tablaClientes.refresh();
        limpiarCampos();
    }
    
}
