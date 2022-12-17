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
 * Clase para el Insert de datos en la BBDD
 */
public class InsertItem {

    // Insert de Clientes en la BBDD
    public void insertarCliente(Cliente cliente) throws SQLException {
        Connection con = DataSource.getConnection("classicmodels", "root", "123456");
        Statement stmt = con.createStatement();
        String query = "Insert into customers(customerEmail,idCard,customerName,phone,creditLimit,birthDate) "
                + "values (" + cliente.getEmail() + "," + cliente.getDni() + "," + cliente.getNombre() + ","
                + cliente.getTelefono() + "," + cliente.getCreditoLimite() + "," + cliente.getFechaNacimiento() + ");";
        ResultSet rs = stmt.executeQuery(query);
    }
}
