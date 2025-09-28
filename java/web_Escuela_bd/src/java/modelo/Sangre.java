/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author josue
 */

public class Sangre {
    private int id_tipo_sangre;
    private String sangre;
    private Conexion cn; 
    
    public Sangre(){}

    public int getId_tipo_sangre() {
        return id_tipo_sangre;
    }

    public void setId_tipo_sangre(int id_tipo_sangre) {
        this.id_tipo_sangre = id_tipo_sangre;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

   public HashMap<String,String> drop_sangre() {
    HashMap<String,String> drop = new HashMap<>();
    try {
        cn = new Conexion();
        cn.abrir_conexion();

        if (cn.conexionBD == null) {
            System.out.println("❌ ERROR: No se estableció la conexión con MySQL");
            return drop;
        }

        String query = "SELECT id_tipo_sangre, sangre FROM tipos_sangre;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

        while (consulta.next()) {
            String id = consulta.getString("id_tipo_sangre");
            String tipo = consulta.getString("sangre");
            System.out.println("✔ Cargando: " + id + " - " + tipo);
            drop.put(id, tipo);
        }

        cn.cerrar_conexion();
    } catch (Exception ex) {
        System.out.println("❌ Error en drop_sangre(): " + ex.getMessage());
    }
    return drop;
}
}
