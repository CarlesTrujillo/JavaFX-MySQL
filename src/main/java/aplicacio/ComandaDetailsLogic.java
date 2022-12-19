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

/**
 *
 * @author andre
 */
public class ComandaDetailsLogic {
    
  ObservableList<ComandaDetails> llistaObservableComandaDetails;
  private Connection conn;
    
     public ComandaDetailsLogic() throws SQLException {

        conn = DataSource.getConnection("m03uf6_22_23", "root", "1234");

        llistaObservableComandaDetails = FXCollections.<ComandaDetails>observableArrayList();
    }
    
     public void cargarComandaDetails(String idComanda) throws SQLException {

        this.llistaObservableComandaDetails.setAll(ComandasDetailsDAO.cargarComndasDetails(conn, idComanda));

    }
    
    public void borrarTodasComandaDetails(int idComanda) throws SQLException {
        
        ComandasDetailsDAO.borrarTodasComandaDetails(conn, idComanda);
    }
    
     public Boolean listaVacia() {
         
        return llistaObservableComandaDetails.isEmpty();
    }
     
    public double importe(){
        double ret = 0;
       
        for (ComandaDetails comandaDetails : llistaObservableComandaDetails) {
                double precio = (double) comandaDetails.getPrecioProducto() * comandaDetails.getCantidadPedida();
                ret += precio;
            }
        return ret;
        }
    
     
    public ObservableList<ComandaDetails> getLlistaObservableComandaDetails() {
        return llistaObservableComandaDetails;
    }
}
