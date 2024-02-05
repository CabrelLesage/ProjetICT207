/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package System;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author Le_SAGE
 */
public class Connecte {
    Connection con;
    public Connecte(){
        try{
            Class.forName("com.mysql.jdbc.Driver");//Chargement du pilote
            con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/ict217", "root", "root");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Echec de connection");
        }
    }
}
