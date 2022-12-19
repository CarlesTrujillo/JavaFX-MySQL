/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Cliente;

/**
 *
 * @author Albert
 */
public class LogicaCliente {
    public LogicaCliente(){
    super();
    }
    
    public static ArrayList<Cliente> getClientes() throws SQLException{
        
        ArrayList<Cliente> clienteLogica = dades.clienteDAO.muestraClientes();
        
    return clienteLogica;
    }
    
    public static void setCliente(Cliente cliente) throws SQLException{
       dades.clienteDAO.insertarCliente(cliente);
    }
    public static void deleteCliente(Cliente cliente) throws SQLException{
        dades.clienteDAO.deleteCliente(cliente);
    }
    
    public static String consultarEdadMinima() throws SQLException{
        String edad = dades.clienteDAO.consultarEdad();
        return edad;
    }
    
    public static void updateCliente(Cliente cliente) throws SQLException{
    dades.clienteDAO.updateCliente(cliente);
    }
    
    
}
