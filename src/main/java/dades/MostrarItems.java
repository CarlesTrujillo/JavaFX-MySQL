/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import aplicacio.model.Cliente;
import aplicacio.model.Producto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Albert
 */
public class MostrarItems {
        public static ArrayList<Cliente> muestraClientes() throws SQLException{
        Connection con = DataSource.getConnection("classicmodels", "root", "123456");
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
            Statement stmt = con.createStatement();
            String query = "select * from customers";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
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
       
        public static ArrayList<Producto> muestraProductos() throws SQLException {
            Connection con = DataSource.getConnection("classicmodels", "root", "123456");
            ArrayList<Producto> productos = new ArrayList<>();
            Statement stmt = con.createStatement();
            String query = "select * from products";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()) {
                Producto producto = new Producto();
                producto.setCode(rs.getInt("productCode"));
                producto.setNombre(rs.getString("productName"));
                producto.setDescripcion(rs.getString("productDescription"));
                producto.setCantidadStock(rs.getInt("quantityInStock"));
                double doble = Double.parseDouble(rs.getString("price"));
                producto.setPrecio(doble);
                productos.add(producto);
            }
            return productos;
        }
}
