package dades;

import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
        Statement sentencia;
        sentencia = con.createStatement();
        sentencia.executeQuery("select * from customers");
        ResultSet rs = sentencia.getResultSet();
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
    
    public static int consultarEdad() throws SQLException{
        Connection conn = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        Statement sentencia;
        sentencia = conn.createStatement();        
        String query = "select * from appConfig;";
        sentencia.executeQuery(query);
        Byte edad;
        int ret = 0;
        ResultSet rs = sentencia.getResultSet();
        while(rs.next()){
            edad=rs.getByte("minCustomerAge");
            ret = edad.intValue();
        }
        return ret;
    
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
    
        // Insert de Clientes en la BBDD
    public static void updateCliente(Cliente cliente) throws SQLException {
        Connection conn = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        String sqlQuery = "update customers set idCard = ?,customerName = ?,phone = ?,creditLimit = ?,birthDate = ? WHERE customerEmail = ?;";
        
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setString(1, cliente.getDni());
        preparedStatement.setString(2, cliente.getNombre());
        preparedStatement.setString(3, cliente.getTelefono());
        preparedStatement.setDouble(4, cliente.getCreditoLimite());
        preparedStatement.setString(5, cliente.getFechaNacimiento());        
        preparedStatement.setString(6, cliente.getEmail());
        preparedStatement.executeUpdate();   
        
    }
    
    public static double getCreditLimit() throws SQLException{
        Connection conn = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        Statement sentencia;
        sentencia = conn.createStatement();        
        String query = "select * from appConfig;";
        sentencia.executeQuery(query);
        String resultado ="";
        double ret = 0;
        ResultSet rs = sentencia.getResultSet();
        while(rs.next()){
            resultado=rs.getString("defaultCreditLimit");

        }
        ret = Double.parseDouble(resultado);
        return ret;
    
    }
    
    
}
