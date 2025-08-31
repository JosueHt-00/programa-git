/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;
import MODELO.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author itsan
 */
public class Docente extends PERSONA{
    Conexion cn = new Conexion();
    private int idd;
    private String codigo;
    private Float Salario;
    private int id_profesion;

    public Docente() {
    }

    public Docente(int idd, String codigo, Float Salario, int id_profesion, String nit, String nombres, String apellidos, String direccion, String fecha_nacimiento, int id, int telefono, boolean genero) {
        super(nit, nombres, apellidos, direccion, fecha_nacimiento, id, telefono, genero);
        this.idd=idd;
        this.codigo = codigo;
        this.Salario = Salario;
        this.id_profesion = id_profesion;
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Float getSalario() {
        return Salario;
    }

    public void setSalario(Float Salario) {
        this.Salario = Salario;
    }

    public int getId_profesion() {
        return id_profesion;
    }

    public void setId_profesion(int id_profesion) {
        this.id_profesion = id_profesion;
    }
    
    
    public DefaultTableModel read(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            Conexion cn = new Conexion();
            cn.abrir_conexion();
            String[] columnas = {"id","cui","nombres","Apellidos","Direccion","Telefono","fecha_nacimiento","genero","codigo","salario","profesion"};
            tabla.setColumnIdentifiers(columnas);
            String query = "SELECT d.id_docente as id,d.nit,d.nombres,d.apellidos,d.direccion,d.telefono,d.fecha_nacimiento,"
                    + "CASE d.genero WHEN 0 THEN 'femenino' WHEN 1 THEN 'masculino' END AS genero,"
                    + "d.codigo,d.salario,concat(p.id_profesion,') ',p.profesion) as profesion FROM actividad333.docente AS d"
                    + " INNER JOIN actividad333.profesion AS p ON d.id_profesion = p.id_profesion;";        //concat(p.id_puesto,') ',p.puesto) as puesto
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String datos[] = new String[11];
            while (consulta.next()) {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("genero");
                datos[8] = consulta.getString("codigo");
                datos[9] = consulta.getString("salario");
                datos[10] = consulta.getString("profesion");
                tabla.addRow(datos);
            }
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
            String query = "INSERT INTO `actividad333`.`docente` (`nit`, `nombres`, `apellidos`, `direccion`, `telefono`, `fecha_nacimiento`,"
                    + " `genero`, `codigo`, `salario`, `id_profesion`) VALUES (?,?,?,?,?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, (String.valueOf(getTelefono())));
            parametro.setString(6, getFecha_nacimiento());
            parametro.setBoolean(7, isGenero());       
            parametro.setString(8, getCodigo());
                if (getSalario() == null) {
                    parametro.setNull(9, java.sql.Types.FLOAT);
                } else {
                    parametro.setFloat(9, getSalario());
                }
            parametro.setInt(10, getId_profesion());
            

            
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
            String query = "UPDATE `actividad333`.`docente` SET `nit` = ?, `nombres` = ?, `apellidos` = ?, `direccion` = ?,"
                    + " `telefono` = ?, `fecha_nacimiento` = ?, `genero` = ?, `codigo` = ?, `salario` = ?, `id_profesion` = ?"
                    + " WHERE (`id_docente` = ?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, (String.valueOf(getTelefono())));
            parametro.setString(6, getFecha_nacimiento());
            parametro.setBoolean(7, isGenero());       
            parametro.setString(8, getCodigo());
                if (getSalario() == null) {
                    parametro.setNull(9, java.sql.Types.FLOAT);
                } else {
                    parametro.setFloat(9, getSalario());
                }
            parametro.setInt(10, getId_profesion());
            parametro.setInt(11, getIdd());

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
            String query = "DELETE FROM `actividad333`.`docente` WHERE (`id_docente` = ?)";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getIdd());

            int ejecutar = parametro.executeUpdate();
            cn.cerrar_conexion();
            System.out.println("Se Eliminó " + ejecutar + " registro");

        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }
}
