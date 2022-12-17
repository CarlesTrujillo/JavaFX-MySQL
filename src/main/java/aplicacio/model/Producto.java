package aplicacio.model;

import dades.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author D
 * @version 1.0
 *
 * Clase Producto
 */
public class Producto {

    int code;
    String nombre;
    String descripcion;
    int cantidadStock;
    double precio;

    public Producto(int code, String nombre, String descripcion, int cantidadStock, double precio) {
        this.code = code;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.precio = precio;
    }

    // Constructor con datos inicializados
    public Producto() {
        this.code = 0;
        this.nombre = "";
        this.descripcion = "";
        this.cantidadStock = 0;
        this.precio = 0;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Función para mostrar los productos de la BBDD
    public static ArrayList<Producto> muestraProductos() throws SQLException {
        Connection con = DataSource.getConnection("classicmodels", "root", "123456");
        ArrayList<Producto> productos = new ArrayList<>();
        Statement stmt = con.createStatement();
        String query = "select * from products";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
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

    // Función para insertar productos en la BBDD
    public void insertarProducto(Producto producto) throws SQLException {
        Connection con = DataSource.getConnection("classicmodels", "root", "123456");
        Statement stmt = con.createStatement();
        String query = "Insert into products(productCode, productName, productDescription, quantityInStock, buyPrice) "
                + "values (" + producto.getCode() + "," + producto.getNombre() + "," + producto.getDescripcion() + ","
                + producto.getCantidadStock() + "," + producto.getPrecio() + ");";
        ResultSet rs = stmt.executeQuery(query);
    }

    // Función de borrado de productos en la BBDD
    public void deleteProducto(Producto producto) throws SQLException {
        Connection con = DataSource.getConnection("classicmodels", "root", "123456");
        Statement stmt = con.createStatement();
        String query = "delete from products where productCode = " + producto.getCode();
        ResultSet rs = stmt.executeQuery(query);
    }
}
