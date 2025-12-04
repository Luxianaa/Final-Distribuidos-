/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonaDAO;

import java.io.Serializable;

/**
 *
 * @author lucia
 */
public class Persona implements Serializable{
    private int id;
    private String nombres;
    private String apellidos;
    private Sexo sexo;
    private int numero_documento;

     // Constructor sin id para registrar una persona
    public Persona(int id, String nombres, String apellidos, Sexo sexo, int numero_carnet) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.numero_documento = numero_carnet;
    }
    
    // Constructor sin id para leer un regidtro existente
    
    public Persona(String nombres, String apellidos, Sexo sexo, int numero_carnet) {
//        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.numero_documento = numero_carnet;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(int numero_carnet) {
        this.numero_documento = numero_carnet;
    }
    
    
}
