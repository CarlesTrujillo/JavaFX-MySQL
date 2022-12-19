/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import model.Comanda;

/**
 *
 * @author andre
 */
public class ComandasDAO {
    
    public static void anadirUnaComanda(Connection con, Comanda comanda) throws SQLException {
    
        String sqlQuery;
        String nomBD = con.getCatalog();
        
        sqlQuery = "INSERT INTO "+nomBD+".orders (orderDate, requiredDate, shippedDate, customers_customerEmail) VALUES (?,?,?,?);";
        
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
        preparedStatement.setString(1, comanda.getFechaOrden());
        preparedStatement.setString(2, comanda.getFechaRequerida());
        preparedStatement.setString(3, comanda.getFechaEnvio());
        preparedStatement.setString(4, comanda.getEmailCliente());
        
        preparedStatement.executeUpdate();
    }  
    public static ArrayList<Comanda> cargarComndas(Connection con) throws SQLException
    {
        ArrayList<Comanda> ret = new ArrayList<>();
        
        Statement sentencia;
        
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM orders");
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            ret.add(new Comanda(rs.getInt("orderNumber"), rs.getString("orderDate"), rs.getString("requiredDate"), rs.getString("shippedDate"), rs.getString("customers_customerEmail")));
        }
        
        return ret;
    }
    
    
    
    public static long minShippingHours(Connection con) throws SQLException{
        long ret = 0;
        Statement sentencia;
        
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM appConfig");
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            ret = rs.getLong("minShippingHours");
        }
        
        return ret;
    }
    public static int obtenerUltimaComanda(Connection con)  throws SQLException{
        int ret = 1;
        Statement sentencia;       
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM orders ORDER BY orderNumber DESC LIMIT 1");
        ResultSet rs = sentencia.getResultSet();
        Comanda comanda = null;
        while (rs.next()) {
              comanda = new Comanda(rs.getInt("orderNumber"), rs.getString("orderDate"), rs.getString("requiredDate"), rs.getString("shippedDate"),rs.getString("customers_customerEmail"));
        }
        if(comanda != null){
            ret = comanda.getNumeroOrden();
        }
        return ret;
    }
    
    
     public static void borrarComanda(Connection conn , int idComanda) throws SQLException
    {
         Statement sentencia;
         String nomBD = conn.getCatalog();
         sentencia = conn.createStatement();
         sentencia.executeUpdate("DELETE FROM "+nomBD+".orders WHERE orderNumber = " + idComanda);             
    }
     
    public static ArrayList<Comanda> cargarComndasFiltradasPorFecha(Connection con, Date fechaInicial, Date fechaFinal) throws SQLException
    {
        ArrayList<Comanda> ret = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE orderDate BETWEEN ? AND ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDate(1, new java.sql.Date(fechaInicial.getTime()));
        statement.setDate(2, new java.sql.Date(fechaFinal.getTime()));
        ResultSet rs = statement.executeQuery();
        
        while (rs.next()) {
            ret.add(new Comanda(rs.getInt("orderNumber"), rs.getString("orderDate"), rs.getString("requiredDate"), rs.getString("shippedDate"), rs.getString("customers_customerEmail")));
        }
        
        return ret;
    }
    
    public static Comanda cargarUnaComanda(Connection con, String idCmanda) throws SQLException {
        Comanda ret = null;
        Statement sentencia;
        
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM orders WHERE orderNumber = " + idCmanda);
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
          ret = new Comanda(rs.getInt("orderNumber"), rs.getString("orderDate"), rs.getString("requiredDate"), rs.getString("shippedDate"), rs.getString("customers_customerEmail"));
        }
        return ret;
    }
    
    public static void insertarComanda(Connection con, Comanda comanda)  throws SQLException {
        
        String sqlQuery;
        String nomBD = con.getCatalog();
        
        sqlQuery = "INSERT INTO "+nomBD+".orders (orderDate, requiredDate, shippedDate, customers_customerEmail) VALUES (?,?,?,?);";
        
        PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
        preparedStatement.setString(1, comanda.getFechaOrden());
        preparedStatement.setString(2, comanda.getFechaRequerida());
        preparedStatement.setString(3, comanda.getFechaEnvio());
        preparedStatement.setString(4, comanda.getEmailCliente());
        
        preparedStatement.executeUpdate();
        
    } 
}
