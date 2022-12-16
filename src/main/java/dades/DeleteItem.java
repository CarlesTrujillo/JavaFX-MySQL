package dades;

import aplicacio.model.Cliente;
import aplicacio.model.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Albert
 * @version 1.0
 * 
 * Clase para borrar datos en la BBDD
 */
public class DeleteItem {
   
    // Delete de clientes en la BBDD
    public void deleteCliente(Cliente cliente) throws SQLException {
        Connection con = DataSource.getConnection();
        Statement stmt = con.createStatement();
        String query = "delete from customers where customerEmail = " + cliente.getEmail();
        ResultSet rs = stmt.executeQuery(query);
    }
    
    // Delete de productos en la BBDD
    public void deleteProducto(Producto producto) throws SQLException {
        Connection con = DataSource.getConnection();
        Statement stmt = con.createStatement();
        String query = "delete from products where productCode = " + producto.getCode();
        ResultSet rs = stmt.executeQuery(query);
    }

}
