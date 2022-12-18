package dades;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Producto;

/**
 *
 * @author D
 * @version 1.0
 *
 * Funciones para trabajar con la tabla Productos de la BBDD
 */
public class ProductosDAO {

    // Función de borrado de productos en la BBDD
    public void deleteProducto(Producto producto) throws SQLException {
        Connection con = DataSource.getConnection("classicmodels", "root", "123456");
        Statement stmt = con.createStatement();
        String query = "delete from products where productCode = " + producto.getCode();
        ResultSet rs = stmt.executeQuery(query);
    }

    // Función para insertar productos en la BBDD
    public static void insertarProducto(Producto producto) throws SQLException {
        Connection con = DataSource.getConnection("classicmodels", "root", "123456");
        Statement stmt = con.createStatement();
        String query = "Insert into products(productCode, productName, productDescription, quantityInStock, buyPrice) "
                + "values (" + producto.getCode() + "," + producto.getNombre() + "," + producto.getDescripcion() + ","
                + producto.getCantidadStock() + "," + producto.getPrecio() + ");";
        ResultSet rs = stmt.executeQuery(query);
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
}
