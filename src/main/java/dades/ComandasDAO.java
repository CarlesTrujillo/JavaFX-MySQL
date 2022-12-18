/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Comanda;

/**
 *
 * @author andre
 */
public class ComandasDAO {
    
     public static Connection connectarBD(String bd, String usuari, String password) throws SQLException
    {
        Connection ret;
      
        ret =  DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?useUnicode=true&"
                            + "useJDBCCompliantTimezoneShift=true&"
                            + "useLegacyDatetimeCode=false&serverTimezone=UTC", usuari, password);   
       
        
        return ret;
    }
     
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
         sentencia.executeUpdate("DELETE FROM "+nomBD+".orderdetails WHERE orderNumber = " + idComanda);
         sentencia.executeUpdate("DELETE FROM "+nomBD+".orders WHERE orderNumber = " + idComanda);
          
       
        
    }
}
