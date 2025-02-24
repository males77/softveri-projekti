/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jelena Malesevic
 */
public class Masina {
    private int id;
    private String naziv;
    private int godProizvodnje;
    private int ocekivaniRadniVek;
    private String proizvodjac;
    private Date datumPocetka = new Date();
    private TipMasine tip;
    private Lokacija lokacija;
    private List<Alat> alati = new ArrayList<>();
    private String inzenjer;

    public int getOcekivaniRadniVek() {
        return ocekivaniRadniVek;
    }

    public void setOcekivaniRadniVek(int ocekivaniRadniVek) {
        this.ocekivaniRadniVek = ocekivaniRadniVek;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getInzenjer() {
        return inzenjer;
    }

    public void setInzenjer(String inzenjer) {
        this.inzenjer = inzenjer;
    }
    

    public Masina() {
    }

    public Masina(int id, String naziv, int godProizvodnje,Date datumPocetka, TipMasine tip, Lokacija lokacija, String inzenjer) {
        this.id = id;
        this.naziv = naziv;
        this.godProizvodnje = godProizvodnje;
        this.datumPocetka = datumPocetka;
        this.tip = tip;
        this.lokacija = lokacija;
        this.inzenjer = inzenjer;
    }

    public Masina(int id, String naziv, int godProizvodnje,Date datumPocetka, int ocekivaniRadniVek, String proizvodjac, TipMasine tip, Lokacija lokacija, String inzenjer) {
        this.id = id;
        this.naziv = naziv;
        this.godProizvodnje = godProizvodnje;
        this.datumPocetka = datumPocetka;
        this.ocekivaniRadniVek = ocekivaniRadniVek;
        this.proizvodjac = proizvodjac;
        this.tip = tip;
        this.lokacija = lokacija;
        this.inzenjer = inzenjer;
    }
    
    

    public List<Alat> getAlati() {
        return alati;
    }

    public void setAlati(List<Alat> alati) {
        this.alati = alati;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getGodProizvodnje() {
        return godProizvodnje;
    }

    public void setGodProizvodnje(int godProizvodnje) {
        this.godProizvodnje = godProizvodnje;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public TipMasine getTip() {
        return tip;
    }

    public void setTip(TipMasine tip) {
        this.tip = tip;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    @Override
    public String toString() {
        return "Masina{" + "id=" + id + ", naziv=" + naziv + ", godProizvodnje=" + godProizvodnje + ", datumPocetka=" + datumPocetka + ", tip=" + tip + ", lokacija=" + lokacija + '}';
    }
    
    
}
