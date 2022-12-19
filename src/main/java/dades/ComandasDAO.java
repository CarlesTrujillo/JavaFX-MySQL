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
    
     
    public static ArrayList<Comanda> carregarComndas(Connection con) throws SQLException
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
    
     public static void borrarComanda(Connection conn , int idComanda) throws SQLException
    {
         Statement sentencia;
         String nomBD = conn.getCatalog();
         sentencia = conn.createStatement();
         sentencia.executeUpdate("DELETE FROM "+nomBD+".orders WHERE orderNumber = " + idComanda);
          
       
        
    }
     
    public static ArrayList<Comanda> carregarComndasFiltradasPorFecha(Connection con, Date fechaInicial, Date fechaFinal) throws SQLException
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
}
