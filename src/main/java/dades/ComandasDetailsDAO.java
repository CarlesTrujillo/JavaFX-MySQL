/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
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
}
