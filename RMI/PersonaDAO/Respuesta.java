package PersonaDAO;

import java.io.Serializable;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lucia
 */
//En esta es fundametnal es el DTO es como el mensajero entre el cliente y el servidor, 
//transporta los obejtos entre estos dos , hace que los datos viajen en un mismo objeto {respuesta}
//no olvidarse de Serializable !!! convierte los datos en 0 y 1 para qeu viaje por la red llegan al cleitne y se rearman.
public class Respuesta implements Serializable {
    Boolean transaccion;
    String[] mensajes;
    Persona[] datos;
    

    public Respuesta(Boolean transaccion, String[] mensajes, Persona[] datos) {
        this.transaccion = transaccion;
        this.mensajes = mensajes;
        this.datos = datos;
    }
    
    public Boolean getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Boolean transaccion) {
        this.transaccion = transaccion;
    }

    public String[] getMensajes() {
        return mensajes;
    }

    public void setMensajes(String[] mensajes) {
        this.mensajes = mensajes;
    }

    public Persona[] getDatos() {
        return datos;
    }

    public void setDatos(Persona[] datos) {
        this.datos = datos;
    }
    
}
