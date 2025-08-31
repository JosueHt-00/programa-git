/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author itsan
 */
abstract class PERSONA{
        
    private String nit,nombres,apellidos,direccion,fecha_nacimiento;
    private int id, telefono;
    private boolean genero;

    public PERSONA() {
    }

    public PERSONA(String nit, String nombres, String apellidos, String direccion, String fecha_nacimiento, int id, int telefono, boolean genero) {
        this.nit = nit;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.id = id;
        this.telefono = telefono;
        this.genero = genero;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean isGenero() {
        return genero;
    }

    public void setGenero(boolean genero) {
        this.genero = genero;
    }
    
    
    
    
    
    private void create(){}
    private DefaultTableModel read(){return null;}
    private void update(){}
    private void delete(){}    
    
    
    
    
}
