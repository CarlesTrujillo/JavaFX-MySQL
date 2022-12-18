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
}
