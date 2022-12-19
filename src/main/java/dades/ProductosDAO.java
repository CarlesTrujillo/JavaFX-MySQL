package dades;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public static void deleteProducto(Producto producto) throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        Statement stmt = con.createStatement();
        String query = "delete from products where productCode = '" + producto.getCode() + "';";
        ResultSet rs = stmt.executeQuery(query);
    }

    // Función para insertar productos en la BBDD
    public static void insertarProducto(Producto producto) throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        
        String query = "INSERT INTO m03uf6_22_23 . products(productName,productDescription,quantityInStock,buyPrice) VALUES (?,?,?,?);";
        
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setString(1, producto.getNombre());
        preparedStatement.setString(2, producto.getDescripcion());
        preparedStatement.setInt(3, producto.getCantidadStock());
        preparedStatement.setDouble(4, producto.getPrecio());
        preparedStatement.executeUpdate();
    }

    // Función para mostrar los productos de la BBDD
    public static ArrayList<Producto> muestraProductos() throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        ArrayList<Producto> productos = new ArrayList<>();
        Statement stmt = con.createStatement();
        String query = "SELECT * FROM products;";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            Producto producto = new Producto();
            producto.setCode(rs.getInt("productCode"));
            producto.setNombre(rs.getString("productName"));
            producto.setDescripcion(rs.getString("productDescription"));
            producto.setCantidadStock(rs.getInt("quantityInStock"));
            double doble = Double.parseDouble(rs.getString("buyPrice"));
            producto.setPrecio(doble);
            productos.add(producto);
        }
        return productos;
    }
}
