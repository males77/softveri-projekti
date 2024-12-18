/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
/**
 *
 * @author Jelena Malesevic
 */
public class Konekcija {
    private static Konekcija instance;
    private Connection connection;
    private Konekcija(){
        try{
            String url="jdbc:mysql://localhost:3306/ps_2018_klk_1g";
            connection = DriverManager.getConnection(url,"root","");
            connection.setAutoCommit(false);
        } catch(SQLException e){
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    public static Konekcija getInstance(){
        if(instance==null){
            instance = new Konekcija();
            
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
}
