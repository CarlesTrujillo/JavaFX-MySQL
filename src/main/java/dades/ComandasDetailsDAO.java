/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.ComandaDetails;

/**
 *
 * @author andre
 */
public class ComandasDetailsDAO {
    
    public static ArrayList<ComandaDetails> cargarComndasDetails(Connection con, String idComada) throws SQLException
    {
        ArrayList<ComandaDetails> ret = new ArrayList<>();
        
        Statement sentencia;
        
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM orderdetails WHERE orderNumber = " + idComada);
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            ret.add(new ComandaDetails(rs.getInt("orderNumber"), rs.getInt("productCode"), rs.getInt("quantityOrdered"), rs.getDouble("priceEach"), rs.getInt("orderLineNumber")));
        }
        
        return ret;
    }
    
     public static void borrarTodasComandaDetails(Connection conn , int idComanda) throws SQLException
    {
         Statement sentencia;
         String nomBD = conn.getCatalog();
         sentencia = conn.createStatement();
         sentencia.executeUpdate("DELETE FROM "+nomBD+".orderdetails WHERE orderNumber = " + idComanda);
    
    }
     public static void borrarUnaComandaDetails(Connection conn , int idComanda, int idProducto) throws SQLException
    {
         Statement sentencia;
         String nomBD = conn.getCatalog();
         sentencia = conn.createStatement();
         sentencia.executeUpdate("DELETE FROM "+nomBD+".orderdetails WHERE orderNumber = " + idComanda + " AND productCode = " + idProducto);
    } 
    
    public static void insertarUnComandaDetails(Connection conn, ComandaDetails comandaDetails) throws SQLException{
        
          String sqlQuery;
        String nomBD = conn.getCatalog();
        
        sqlQuery = "INSERT INTO "+nomBD+".orderdetails (orderNumber, productCode, quantityOrdered, priceEach, orderLineNumber) VALUES (?,?,?,?,?);";
        
        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setString(1, Integer.toString(comandaDetails.getNumeroComanda()));
        preparedStatement.setString(2, Integer.toString(comandaDetails.getCodigoProducto()));
        preparedStatement.setString(3, Integer.toString(comandaDetails.getCantidadPedida()));
        preparedStatement.setString(4,  Double.toString(comandaDetails.getPrecioProducto()));
        preparedStatement.setString(5,  "0");
        
        preparedStatement.executeUpdate();
        
    
    }
    
     public static double maxOrderAmount(Connection con) throws SQLException{
        double ret = 0;
        Statement sentencia;
        
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM appConfig");
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            ret = rs.getLong("maxOrderAmount");
        }
        
        return ret;
    }
     
    public static double defaultProductBenefit(Connection con) throws SQLException{
    
         double ret = 0;
        Statement sentencia;
        
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM appConfig");
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            ret = rs.getDouble("defaultProductBenefit");
        }
        
        return ret;
    }

    public static int defaultQuantityOrdered(Connection con) throws SQLException {
        int ret = 0;
        Statement sentencia;
        
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM appConfig");
        ResultSet rs = sentencia.getResultSet();
        while (rs.next()) {
            ret = rs.getInt("defaultQuantityOrdered");
        }
        
        return ret;
    }

    
}
