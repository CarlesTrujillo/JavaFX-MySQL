/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author andre
 */
public class ComandaDetails {
    int numeroComanda;
    int codigoProducto;
    int cantidadPedida;
    double precioProducto;
    int numeroLineaComanda;

    public ComandaDetails(int numeroComanda, int codigoProducto, int cantidadPedida, double precioProducto, int numeroLineaComanda) {
        this.numeroComanda = numeroComanda;
        this.codigoProducto = codigoProducto;
        this.cantidadPedida = cantidadPedida;
        this.precioProducto = precioProducto;
        this.numeroLineaComanda = numeroLineaComanda;
    }

    public int getNumeroComanda() {
        return numeroComanda;
    }

    public void setNumeroComanda(int numeroComanda) {
        this.numeroComanda = numeroComanda;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getCantidadPedida() {
        return cantidadPedida;
    }

    public void setCantidadPedida(int cantidadPedida) {
        this.cantidadPedida = cantidadPedida;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public int getNumeroLineaComanda() {
        return numeroLineaComanda;
    }

}
