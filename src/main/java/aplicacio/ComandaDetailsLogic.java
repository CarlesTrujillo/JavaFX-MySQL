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
    
  ObservableList<ComandaDetails> llistaObservableComanda;
  private Connection conn;
    
     public ComandaDetailsLogic() throws SQLException {

        conn = DataSource.getConnection("m03uf6_22_23", "root", "1234");

        llistaObservableComanda = FXCollections.<ComandaDetails>observableArrayList();
    }
    
     public void carregarComandaDetails(String idComanda) throws SQLException {

        this.llistaObservableComanda.setAll(ComandasDetailsDAO.carregarComndasDetails(conn, idComanda));

    }
     
    public ObservableList<ComandaDetails> getLlistaObservableComandaDetails() {
        return llistaObservableComanda;
    }
}
