package dades;

import model.Cliente;
import aplicacio.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Albert
 * @version 1.0
 *
 * Clase para el Insert de datos en la BBDD
 */
public class InsertItem {

    // Insert de Clientes en la BBDD
    public static void insertarCliente(Cliente cliente) throws SQLException {
        Connection conn = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        String sqlQuery = "INSERT INTO  m03uf6_22_23 . customers  (customerEmail,idCard,customerName,phone,creditLimit,birthDate) VALUES (?,?,?,?,?,?);";
        
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setString(1, cliente.getEmail());
        preparedStatement.setString(2, cliente.getDni());
        preparedStatement.setString(3, cliente.getNombre());
        preparedStatement.setString(4, cliente.getTelefono());
        preparedStatement.setDouble(5, cliente.getCreditoLimite());
        preparedStatement.setString(6, cliente.getFechaNacimiento());        
        preparedStatement.executeUpdate();   
        
    }
}
