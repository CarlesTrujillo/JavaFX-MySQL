/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

/**
 *
 * @author D
 */
public class Comanda {
    
    int numeroOrden;
    String fechaOrden;
    String fechaRequerida;
    String fechaEnvio;
    String emailCliente;

    public Comanda(int numeroOrden, String fechaOrden, String fechaRequerida, String fechaEnvio, String emailCliente) {
        this.numeroOrden = numeroOrden;
        this.fechaOrden = fechaOrden;
        this.fechaRequerida = fechaRequerida;
        this.fechaEnvio = fechaEnvio;
        this.emailCliente = emailCliente;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getFechaRequerida() {
        return fechaRequerida;
    }

    public void setFechaRequerida(String fechaRequerida) {
        this.fechaRequerida = fechaRequerida;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
    
    
}
