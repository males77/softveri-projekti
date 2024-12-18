/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Alat;
import model.Inzenjer;
import model.Lokacija;
import model.Masina;

/**
 *
 * @author Jelena Malesevic
 */
public class Controller {
    private static Controller instance;
    private List<Inzenjer> inzenjeri = new ArrayList<>();
    private Inzenjer ulogovaniInzenjer = null ;
    private DBBroker dbb;
    
    private Controller(){
        Inzenjer inzenjer1 = new Inzenjer("Vanja", "Vizi", "vanjav", "vanjav", 1);
        Inzenjer inzenjer2 = new Inzenjer("Marija", "Petrovic", "marijap", "lozinka123", 2);
        Inzenjer inzenjer3 = new Inzenjer("Ivan", "Jovanovic", "ivanj", "mojalozinka", 3);
        
        inzenjeri.add(inzenjer1);
        inzenjeri.add(inzenjer2);
        inzenjeri.add(inzenjer3);
        
        dbb = new DBBroker();
    }
    public static Controller getInstance(){
       if(instance==null){
           instance = new Controller();
       } 
       return instance;
    }

    public List<Inzenjer> getInzenjeri() {
        return inzenjeri;
    }

    public void setInzenjeri(List<Inzenjer> inzenjeri) {
        this.inzenjeri = inzenjeri;
    }

    public Inzenjer getUlogovaniInzenjer() {
        return ulogovaniInzenjer;
    }

    public void setUlogovaniInzenjer(Inzenjer ulogovaniInzenjer) {
        this.ulogovaniInzenjer = ulogovaniInzenjer;
    }
    
        public Inzenjer login(String username, String password) {
            for(Inzenjer i:inzenjeri){
                if(i.getKorisnickoIme().equals(username)&&i.getLozinka().equals(password)){
                    ulogovaniInzenjer = i;
                    return i;
                }
            }
            return null;
        }

    public List<Lokacija> vratiSveLokacijeIzBaze() {
        return dbb.vratiSveLokacije();
    }

    public List<Alat> vratiSveAlate() {
        return dbb.vratiSveAlate();
    }

    public boolean postojiMasina(int idLok) {
        return dbb.postojiMasina(idLok);
    }

    public boolean ubaciMasinu(Masina m) throws SQLException {
        return dbb.ubaciMasinu(m);
    }

   
}
