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

    /**
     * Función para el borrado de datos en la BBDD
     * 
     * @param producto
     * @throws SQLException 
     */
    public static void deleteProducto(Producto producto) throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        Statement sentencia;
        sentencia = con.createStatement();
        String query = "DELETE FROM products WHERE productCode = " + producto.getCode() + ";";
        sentencia.executeUpdate(query);
    }

    /**
     * Función para insertar productos en la BBDD
     * 
     * @param producto
     * @throws SQLException 
     */
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

    /**
     * Función para la inserción de Productos en la BBDD
     * 
     * @param producto
     * @throws SQLException 
     */
    public static void updateProducto(Producto producto) throws SQLException {
        Connection conn = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        String sqlQuery = "UPDATE products SET productName = ?, productDescription = ?, quantityInStock = ?, buyPrice = ? WHERE productCode = ?;";

        PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
        preparedStatement.setString(1, producto.getNombre());
        preparedStatement.setString(2, producto.getDescripcion());
        preparedStatement.setInt(3, producto.getCantidadStock());
        preparedStatement.setDouble(4, producto.getPrecio());
        preparedStatement.setInt(5, producto.getCode());
        preparedStatement.executeUpdate();

    }

    /**
     * Función para mostrar los productos de la BBDD
     * 
     * @return
     * @throws SQLException 
     */
    public static ArrayList<Producto> muestraProductos() throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        ArrayList<Producto> productos = new ArrayList<>();
        Statement sentencia;
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM products;");
        ResultSet rs = sentencia.getResultSet();

        while (rs.next()) {
            Producto producto = new Producto();
            int codigo = Integer.parseInt(rs.getString("productCode"));
            producto.setCode(codigo);
            producto.setNombre(rs.getString("productName"));
            producto.setDescripcion(rs.getString("productDescription"));
            int cantidad = Integer.parseInt(rs.getString("quantityInStock"));
            producto.setCantidadStock(cantidad);
            double doble = Double.parseDouble(rs.getString("buyPrice"));
            producto.setPrecio(doble);
            productos.add(producto);
        }
        return productos;
    }

    /**
     * Función para recoger el valor de defaultQuantityInStock de la 
     * tabla appConfig para poder trabajar con ello
     * 
     * @return
     * @throws SQLException 
     */
    public static int getStockDefault() throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        Statement sentencia;
        sentencia = con.createStatement();
        sentencia.executeQuery("SELECT * FROM appConfig;");
        Byte cantidadStockDefault;
        int ret = 0;
        ResultSet rs = sentencia.getResultSet();
        
        while (rs.next()) {
            cantidadStockDefault=rs.getByte("defaultQuantityInStock");
            ret = cantidadStockDefault.intValue();
        }
        return ret;
    }

    /**
     * Función para recoger productos en base al nombre que especifique
     * el usuario
     * 
     * @param nombre
     * @return
     * @throws SQLException 
     */
    public static Producto getNombre(String nombre) throws SQLException {
        Connection con = DataSource.getConnection("m03uf6_22_23", "root", "123456");
        Statement sentencia;
        Producto producto = new Producto();
        
        sentencia = con.createStatement();
        String sqlQuery = "SELECT * FROM products WHERE productName = '" + nombre + "';";
        sentencia.executeQuery(sqlQuery);
        ResultSet rs = sentencia.getResultSet();
        
        while(rs.next()) {
            int codigo = Integer.parseInt(rs.getString("productCode"));
            producto.setCode(codigo);
            producto.setNombre(rs.getString("productName"));
            producto.setDescripcion(rs.getString("productDescription"));
            int cantidad = Integer.parseInt(rs.getString("quantityInStock"));
            producto.setCantidadStock(cantidad);
            double doble = Double.parseDouble(rs.getString("buyPrice"));
            producto.setPrecio(doble);
        }
        
        return producto;
    }
}
