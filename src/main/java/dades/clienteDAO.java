package dades;

import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Producto;

/**
 *
 * @author D
 * @version 1.0
 *
 * Clase para el Insert de datos en la BBDD
 */
public class clienteDAO {
    
    public static ArrayList<Cliente> muestraClientes() throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        ArrayList<Cliente> clientes = new ArrayList<>();
        Statement stmt = con.createStatement();
        String query = "select * from customers";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setEmail(rs.getString("customerEmail"));
            cliente.setDni(rs.getString("idCard"));
            cliente.setNombre(rs.getString("customerName"));
            cliente.setTelefono(rs.getString("phone"));
            double doble = Double.parseDouble(rs.getString("creditLimit"));
            cliente.setCreditoLimite(doble);
            cliente.setFechaNacimiento(rs.getString("birthDate"));
            clientes.add(cliente);
        }
        return clientes;
    }

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
    
    public static String consultarEdad() throws SQLException{
        
        Connection conn = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        Statement sentencia;
        
        sentencia = conn.createStatement();        
        String query = "select * from appConfig";
        ResultSet rs = sentencia.executeQuery(query);
            
        String edad = rs.getString("minCustomerAge");            
        return edad;
    
    }
    
    // Delete de clientes en la BBDD
    public static void deleteCliente(Cliente cliente) throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        Statement sentencia;
        sentencia = con.createStatement();
        String sqlStr = "delete from customers where customerEmail = '" + cliente.getEmail() + "';";
        //PreparedStatement preparedStatement = con.prepareStatement(sqlStr);
        //preparedStatement.setString(0,cliente.getEmail());
        sentencia.executeUpdate(sqlStr);
        
        
    }
    
}
