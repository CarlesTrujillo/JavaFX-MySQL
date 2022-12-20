/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio;

import dades.ComandasDAO;
import dades.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Comanda;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author andre
 */
public class ComandasLogic {

    ObservableList<Comanda> llistaObservableComanda;
    private Connection conn;

    public ComandasLogic() throws SQLException {

        conn = DataSource.getConnection("m03uf6_22_23", "root", "1234");

        llistaObservableComanda = FXCollections.<Comanda>observableArrayList();
    }
    
    public int crearComanda(Boolean permitir, Boolean maxOrderAmount, String fechaOrden, String fechaEntrega, String fechaEnvio, String emailCliente) throws SQLException{
       int ret = 0;
        if (!permitir && maxOrderAmount) {
            long diferenciaHoras = restarFechas(fechaOrden, fechaEntrega);
            long minShippingHours = minShippingHours();
            if(diferenciaHoras > minShippingHours){
                int idComanda = obtenerUltimaComanda() + 1;
                Comanda comanda = new Comanda(idComanda, fechaOrden, fechaEntrega, fechaEnvio, emailCliente);
                ComandasDAO.anadirUnaComanda(conn, comanda);
            } else{
                ret = 1;
            }
        }
        
        return ret;
    }
    
    public void modificarUnaComanda(int idComanda, String fechaOrden, String fechaEntrega, String fechaEnvio, String emailCliente) throws SQLException{
        
         Comanda comanda = new Comanda(idComanda, fechaOrden, fechaEntrega, fechaEnvio, emailCliente);
         ComandasDAO.modificarUnaComanda(conn, comanda);
    }
    
    public long minShippingHours(){
         long ret = 0;
        try {
            ret = ComandasDAO.minShippingHours(conn);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return ret;
    }
    
    public void caregarComandas() throws SQLException {

        this.llistaObservableComanda.setAll(ComandasDAO.cargarComndas(conn));

    }
    
     public void borrarComanda(int idComanda) throws SQLException {

        ComandasDAO.borrarComanda(conn, idComanda);

    }

    public void borrarComandaEnTableView(int idComanda) {
        for (Comanda comanda : llistaObservableComanda) {
            if (comanda.getNumeroOrden() == idComanda) {
                llistaObservableComanda.remove(comanda);
                break;
            }
        }

    }
    
    public void caregarComandasFiltradasPorFecha(Date fechaInicial, Date fechaFinal) throws SQLException {

        this.llistaObservableComanda.setAll(ComandasDAO.cargarComndasFiltradasPorFecha(conn, fechaInicial, fechaFinal));

    }
    
    public Comanda cargarUnaComanda(String idComanda) throws SQLException{
        
        Comanda ret;
        ret = ComandasDAO.cargarUnaComanda(conn, idComanda);
        return ret;
    }
    
    public int obtenerUltimaComanda() throws SQLException{
        
        int ret;
        ret = ComandasDAO.obtenerUltimaComanda(conn);
        return ret;
    }

    public ObservableList<Comanda> getLlistaObservableComanda() {
        return llistaObservableComanda;
    }
    
    public long restarFechas(String fechasOrden, String fechaEntrega){
        
        fechasOrden += "T00:00:00Z";
        fechaEntrega += "T00:00:00Z";
        
        Instant fechaIni = Instant.parse(fechasOrden);
        Instant fechaFi = Instant.parse(fechaEntrega);
        
        Duration duration = Duration.between(fechaIni, fechaFi);
        long hours = duration.toHours(); 
        
        return hours;
        
    }

}
