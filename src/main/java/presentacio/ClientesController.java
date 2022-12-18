/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package presentacio;

import model.Cliente;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private void onClick_añadir(ActionEvent event){
        Cliente cliente = new Cliente();
        cliente.setEmail(txtEmail.getText());
        cliente.setDni(txtDni.getText());
        cliente.setNombre(txtNombre.getText());
        cliente.setTelefono(txtTelefono.getText());
        double credito = Double.parseDouble(txtCredito.getText());
        cliente.setCreditoLimite(credito);
        cliente.setFechaNacimiento(txtFecha.getText());       
        llistaObservableClientes.add(cliente);
        tablaClientes.refresh();
        try {
            dades.DataSource.getConnection("m03uf6_22_23","root","123456");
            aplicacio.LogicaCliente.setCliente(cliente);
            
        } catch (SQLException ex) {
            System.out.println("Fallo en clientesController");
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @FXML
    private void onClick_modificar(ActionEvent event) {
    }

    @FXML
    private void onClick_borrar(ActionEvent event) {
    }

}
