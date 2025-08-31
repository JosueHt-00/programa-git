/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author itsan
 */
public class Profesion {
    Conexion cn;
    private int id_profesion;
    private String profesion;

    public Profesion() {
    }

    public Profesion(int id_profesion, String profesion) {
        this.id_profesion = id_profesion;
        this.profesion = profesion;
    }

    public int getId_profesion() {
        return id_profesion;
    }

    public void setId_profesion(int id_profesion) {
        this.id_profesion = id_profesion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    
    public DefaultComboBoxModel read() {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        try {
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT * FROM actividad333.profesion;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            combo.addElement("0) Elija profesion");
            while (consulta.next()) {
                combo.addElement(consulta.getString("id_profesion") + ") " + consulta.getString("profesion"));
            }
            cn.cerrar_conexion();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return combo;
    }
}
