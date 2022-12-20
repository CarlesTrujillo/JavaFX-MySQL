/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio;

import dades.ComandasDetailsDAO;
import dades.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ComandaDetails;
import model.Producto;

/**
 *
 * @author andre
 */
public class ComandaDetailsLogic {
    
  ObservableList<ComandaDetails> listaObservableComandaDetails;
  private Connection conn;
    
     public ComandaDetailsLogic() throws SQLException {

        conn = DataSource.getConnection("m03uf6_22_23", "root", "1234");

        listaObservableComandaDetails = FXCollections.<ComandaDetails>observableArrayList();
    }
    
     public void cargarComandaDetails(String idComanda) throws SQLException {

        this.listaObservableComandaDetails.setAll(ComandasDetailsDAO.cargarComndasDetails(conn, idComanda));

    }
    
    public void borrarTodasComandaDetails(int idComanda) throws SQLException {
        
        ComandasDetailsDAO.borrarTodasComandaDetails(conn, idComanda);
    }
    
     public void borrarUnaComandaDetails(int idComanda, int idProducto) throws SQLException {
        
        ComandasDetailsDAO.borrarUnaComandaDetails(conn, idComanda, idProducto);
    }
     
     public void insertarVariasComandasDetails(Boolean permitir) throws SQLException{
        if(!permitir){ 
            for (ComandaDetails comandaDetails : listaObservableComandaDetails) {
                
                ComandasDetailsDAO.insertarUnComandaDetails(conn, comandaDetails);
            }
        }
     }
     
     public void insertarUnComandaDetailsenListaObservable(int idComanda, Producto producto, int cantidadPedida){
         
         ComandaDetails comandaDetails = new ComandaDetails(idComanda, producto.getCode(), cantidadPedida, producto.getPrecio(), 0);
         this.listaObservableComandaDetails.add(comandaDetails);
     }
     
    public void insertarUnComandaDetails(int idComanda, Producto producto, int cantidadPedida) throws SQLException{
        
        ComandaDetails comandaDetails = new ComandaDetails(idComanda, producto.getCode(), cantidadPedida, producto.getPrecio(), 0);
        ComandasDetailsDAO.insertarUnComandaDetails(conn, comandaDetails);
        this.listaObservableComandaDetails.add(comandaDetails);
    }
    
    public void borrarUnaComandaDetailsdeTableview(int idComanda, int idProducto){
        
        for (ComandaDetails comandaDetails : listaObservableComandaDetails) {
            if (comandaDetails.getNumeroComanda() == idComanda && comandaDetails.getCodigoProducto() == idProducto) {
                listaObservableComandaDetails.remove(comandaDetails);
                break;
            }
        }
    }
    
    public Double restarImporteDeComandaDetailsEliminada(Double importeTotal, int idComanda, int idProducto){
        double ret = 0;
          for (ComandaDetails comandaDetails : listaObservableComandaDetails) {
               if (comandaDetails.getNumeroComanda() == idComanda && comandaDetails.getCodigoProducto() == idProducto) {
                    double precio = (double) comandaDetails.getPrecioProducto() * comandaDetails.getCantidadPedida();
                   importeTotal -= precio;
                   ret = importeTotal;
               }
          }
          
          return ret;
    }
    
    public Double sumarImporteDeComandaDetailsCreada(Double importeTotal, int idComanda, int idProducto){
        
        double ret = 0;
          for (ComandaDetails comandaDetails : listaObservableComandaDetails) {
               if (comandaDetails.getNumeroComanda() == idComanda && comandaDetails.getCodigoProducto() == idProducto) {
                    double precio = (double) comandaDetails.getPrecioProducto() * comandaDetails.getCantidadPedida();
                   importeTotal += precio;
                   ret = importeTotal;
               }
          }
          
          return ret;
    }
    
     public Boolean listaVacia() {
         
        return listaObservableComandaDetails.isEmpty();
    }
     
    public double importe(){
        double ret = 0;
       
        for (ComandaDetails comandaDetails : listaObservableComandaDetails) {
                double precio = (double) comandaDetails.getPrecioProducto() * comandaDetails.getCantidadPedida();
                ret += precio;
            }
        return ret;
        }
    
     
    public ObservableList<ComandaDetails> getLlistaObservableComandaDetails() {
        return listaObservableComandaDetails;
    }
}
