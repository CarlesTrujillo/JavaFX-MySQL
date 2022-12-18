package model;

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

    
    
}
