/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

//import java.net.DatagramSocket;
import java.io.*;
import java.net.*;

/**
 *
 * @author lucia
 */
public class ServerUDP {

    public static void main(String[] args) {
        int port = 6789; // Puerto donde escucharemos
        try {
            DatagramSocket socketUDP = new DatagramSocket(port);
            byte[] buffer = new byte[1000]; //Espacio que se reserva para el mensjae que entrara

            System.out.println("Server UDP escuchando por el puerto" + port);

            while (true) {
                //Se prepara el envase o paqeute (DatagramPacket) vacio
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                //esta en espera hasta que llegue un paquete
                socketUDP.receive(peticion);
                //Leer remitente, leer lo que  nos envia 
                //Necesitamos extraer la IP y puerto del paquete para saber quien envio
                System.out.println("Datagrama recibiendo del host: " + peticion.getAddress());
                System.out.println("Desde el puerto REMOTO:" + peticion.getPort());

                //procesar los datos recibidos
                String cadena = new String(peticion.getData()); //convertir bytes a String (la info que llego)
                String response = "Hola" + cadena;// mandar o crear la respuesta
                byte[] mensaje = response.getBytes(); //convertir la respuesta a bytes

                // Enviar respuesta 
                //Se crea un paquete dirigido explicitamente al del emisor o remitente original
                DatagramPacket respuesta = new DatagramPacket(mensaje, response.length(),
                        peticion.getAddress(), peticion.getPort());

                socketUDP.send(respuesta);

            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

}
