package aplicacio;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Producto;

/**
 *
 * @author D
 * @version 1.0
 *
 * Logica Productos
 */
public class LogicaProducto {

    public LogicaProducto() {
        super();
    }

    public static ArrayList<Producto> getProductos() throws SQLException {

        ArrayList<Producto> productoLogica = dades.ProductosDAO.muestraProductos();

        return productoLogica;
    }

    public static void setProducto(Producto producto) throws SQLException {
        dades.ProductosDAO.insertarProducto(producto);
    }

    public static void updateProducto(Producto producto) throws SQLException {
        dades.ProductosDAO.updateProducto(producto);
    }

    public static void deleteProducto(Producto producto) throws SQLException {
        dades.ProductosDAO.deleteProducto(producto);
    }

    public static int getDefaultStock() throws SQLException {
        int cantidadStockDefault = dades.ProductosDAO.getStockDefault();

        return cantidadStockDefault;
    }
}
