/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio;

import dades.ComandasDAO;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Comanda;

/**
 *
 * @author andre
 */
public class ComandasLogic {

    ObservableList<Comanda> llistaObservableComanda;
    private Connection conn;

    public ComandasLogic() throws SQLException {

        conn = ComandasDAO.connectarBD("m03uf6_22_23", "root", "1234");

        llistaObservableComanda = FXCollections.<Comanda>observableArrayList();
    }
    
    public void carregarComandas() throws SQLException {

        this.llistaObservableComanda.setAll(ComandasDAO.carregarComndas(conn));

    }

    public ObservableList<Comanda> getLlistaObservableComanda() {
        return llistaObservableComanda;
    }

}
