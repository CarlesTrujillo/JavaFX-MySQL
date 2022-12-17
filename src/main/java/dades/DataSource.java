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
 * @author D
 */
public class DataSource {
    
    public static Connection getConnection(String bd, String usuari, String password) throws SQLException{
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?useUnicode=true&"
                            + "useJDBCCompliantTimezoneShift=true&"
                            + "useLegacyDatetimeCode=false&serverTimezone=UTC", usuari, password);  
        
        return con;
    }
    
}
