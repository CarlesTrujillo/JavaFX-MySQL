/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio.model;

/**
 *
 * @author D
 */
public class Cliente {
    
    String email;
    String dni;
    String nombre;
    String telefono;
    double creditoLimite;
    String fechaNacimiento;

    public Cliente(String email, String dni, String nombre, String telefono, double creditoLimite, String fechaNacimiento) {
        this.email = email;
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
        this.creditoLimite = creditoLimite;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Cliente() {
        this.email = "";
        this.dni = "";
        this.nombre = "";
        this.telefono = "";
        this.creditoLimite = 0;
        this.fechaNacimiento = "";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getCreditoLimite() {
        return creditoLimite;
    }

    public void setCreditoLimite(double creditoLimite) {
        this.creditoLimite = creditoLimite;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
}
