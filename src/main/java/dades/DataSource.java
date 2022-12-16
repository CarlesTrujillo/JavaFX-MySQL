/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Albert
 */
public class DataSource {
    
    public static Connection getConnection() throws SQLException{
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m03uf6_22_23", "root", "123456");  
        
        return con;
    }
    
}
