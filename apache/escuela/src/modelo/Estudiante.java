/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author itsan
 */

public class Estudiante extends PERSONA{
    Conexion cn = new Conexion();
    private int carnet;
    private int iddd;
    

    public Estudiante() {
    }

    public Estudiante(int iddd, int carnet, String nit, String nombres, String apellidos, String direccion, String fecha_nacimiento, int id, int telefono, boolean genero) {
        super(nit, nombres, apellidos, direccion, fecha_nacimiento, id, telefono, genero);
        this.carnet = carnet;
        this.iddd=iddd;
    }

    public int getIddd() {
        return iddd;
    }

    public void setIddd(int iddd) {
        this.iddd = iddd;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }
    
    /**
     *
     * @return
     */
    public DefaultTableModel read(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn.abrir_conexion();
            String[] columnas = {"id","NIT","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","FECHA_NACIMIENTO","GENERO","CARNET"};
            String query = "SELECT id_estudiante as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento,CASE genero"
                    + " WHEN 1 THEN 'Masculino' WHEN 0 THEN 'Femenino' END AS genero,carnet FROM actividad333.estudiante;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);        
            tabla.setColumnIdentifiers(columnas);
            
            String datos[] = new String[9];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("genero");
                datos[8] = consulta.getString("carnet");
                tabla.addRow(datos);}
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error : " + ex.getMessage());
            }
        return tabla;
        }
 
    public void create(){
            try{
            PreparedStatement parametro;
            cn.abrir_conexion();
            String query = "INSERT INTO `actividad333`.`estudiante` (`nit`, `nombres`, `apellidos`, `direccion`, `telefono`, `fecha_nacimiento`,"
                    + " `genero`, `carnet`) VALUES (?,?,?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, (String.valueOf(getTelefono())));
            parametro.setString(6, getFecha_nacimiento());
            parametro.setBoolean(7, isGenero());        
            parametro.setInt(8, getCarnet());

            int ejecutar=parametro.executeUpdate();
            System.out.println("Se ingresó" + ejecutar + "registro");
            JOptionPane.showMessageDialog(null, "se añadieron" + ejecutar + "filas", "Atencion compa",JOptionPane.INFORMATION_MESSAGE);
            cn.cerrar_conexion();

        }
        catch(SQLException ex){
            System.out.println("error" + ex.getMessage());
            JOptionPane.showMessageDialog(null,"error: " + ex, "error compa", JOptionPane.ERROR_MESSAGE);
        } 
        
    }
     
    public void update(){
        try {
            PreparedStatement parametro;
            cn.abrir_conexion();
            String query = "UPDATE `actividad333`.`estudiante` SET `nit` = ?, `nombres` = ?, `apellidos` = ?, `direccion` = ?,"
                    + " `telefono` = ?, `fecha_nacimiento` = ?, `genero` = ?, `carnet` = ? WHERE (`id_estudiante` = ?);";
            
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, (String.valueOf(getTelefono())));
            parametro.setString(6, getFecha_nacimiento());
            parametro.setBoolean(7, isGenero());       
            parametro.setInt(8, getCarnet());
            parametro.setInt(9, getIddd());

            int ejecutar = parametro.executeUpdate();


            cn.cerrar_conexion();
            System.out.println("Se Actualizó " + ejecutar + " registro");

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
    public void delete(){
        try {
            PreparedStatement parametro;
            cn.abrir_conexion();
            String query = "DELETE FROM `actividad333`.`estudiante` WHERE (`id_estudiante` = ?)";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIddd());

            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            System.out.println("Se Eliminó " + ejecutar + " registro");

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }
    }
    
    

