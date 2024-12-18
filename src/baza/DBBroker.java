/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import com.mysql.cj.protocol.Resultset;
import java.util.ArrayList;
import java.util.List;
import model.Lokacija;
import java.sql.ResultSet;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Alat;
import model.Masina;

/**
 *
 * @author Jelena Malesevic
 */
public class DBBroker {
     List<Lokacija> lista = new ArrayList<>();
     List<Alat> listaAlata = new ArrayList<>();
     
    public List<Lokacija> vratiSveLokacije() {
        try {
            String upit = "SELECT * FROM LOKACIJA ORDER BY NAZIV ASC";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                Lokacija l = new Lokacija(id, naziv);
                lista.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Alat> vratiSveAlate() {
         try {
            String upit = "SELECT * FROM ALAT";
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("id");
                String naziv = rs.getString("naziv");
                int stanje = rs.getInt("stanje");
                Alat a = new Alat(id, naziv, stanje);
                listaAlata.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAlata;
    }

    public boolean postojiMasina(int idLok) {
        String upit = "SELECT * FROM MASINA WHERE LOKACIJA = "+idLok;
        
         try {
             Statement st = Konekcija.getInstance().getConnection().createStatement();
             ResultSet rs = st.executeQuery(upit);
             if(rs.next()){
                 return true;
             }else{
                 return false;
             }
         } catch (SQLException ex) {
             Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;
    }

    public boolean ubaciMasinu(Masina m) throws SQLException {
         try {
             String upit1 = "INSERT INTO masina (naziv,godProizvodnje,datumPocetka,radniVek, proizvojac, tip, lokacija, inzenjer) VALUES (?,?,?,?,?,?,?,?)";
             PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit1,Statement.RETURN_GENERATED_KEYS);//jer mi treba rezultat id
             
             ps.setString(1, m.getNaziv());
             ps.setInt(2, m.getGodProizvodnje());
             ps.setDate(3, new java.sql.Date(m.getDatumPocetka().getTime()));
             ps.setInt(4, m.getOcekivaniRadniVek());
             ps.setString(5, m.getProizvodjac());
             ps.setString(6, m.getTip().toString());
             ps.setInt(7, m.getLokacija().getId());
             ps.setString(8, m.getInzenjer());
             
             int affected = ps.executeUpdate();
             if(affected==0){
                 throw new SQLException("GRESKAAAAAA U KREIRANJU MASINCICE SLATKE");
             }
             ResultSet generatedKeys = ps.getGeneratedKeys();
             System.out.println(generatedKeys);
             if(generatedKeys.next()){
                 long masinaId = generatedKeys.getLong(1);
                 String upit2 = "INSERT INTO upotreba (masina,alat) VALUES (?,?)";
                 String upit3 = "UPDATE alat SET stanje=? WHERE id = ?";
                 ps = Konekcija.getInstance().getConnection().prepareStatement(upit2);
                 PreparedStatement ps3 = Konekcija.getInstance().getConnection().prepareStatement(upit3);
                 for(Alat a : m.getAlati()){
                     ps.setLong(1, masinaId);
                     ps.setLong(2, a.getId());
                     int novoStanje = a.getStanje()-1;
                     ps3.setLong(1, novoStanje);
                     ps3.setLong(2, a.getId());
                     ps.addBatch();
                     ps3.addBatch();
                 }
                 ps.executeBatch();
                 ps3.executeBatch();
                 Konekcija.getInstance().getConnection().commit();
                         
             }else{
                 Konekcija.getInstance().getConnection().rollback();
             }
             
         } catch (SQLException ex) {
             Konekcija.getInstance().getConnection().rollback();
             Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;
    }

    
    
}
