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
        
        ArrayList<Cliente> clienteLogica = dades.MostrarItems.muestraClientes();
        
    return clienteLogica;
    }
    
    public static void setCliente(Cliente cliente) throws SQLException{
       dades.InsertItem.insertarCliente(cliente);
    }
    
    
}
