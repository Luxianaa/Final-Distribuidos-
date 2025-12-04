/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.net.*;
import java.util.Scanner;
import java.io.*;

/**
 *
 * @author lucia
 */
public class ClienteUDP {

    public static void main(String[] args) {
        int port = 6789; //Debe ser la misma que con la del servidor
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Introduzca su nombre: ");
            String dato = sc.next(); //devuelve el valor de la cadena
            String ip = "localhost";

            //Crear el SOcket (sin puerto fijo)
            //El cliente no necesita un puerto fijo, el sistema op le asigna uno libre.
            DatagramSocket socketUDP = new DatagramSocket();
            byte[] mensaje = dato.getBytes();
            InetAddress hostServer = InetAddress.getByName(ip);
            // Empaquetar el mensaje que se enviara

            DatagramPacket peticion = new DatagramPacket(mensaje, dato.length(), hostServer, port);

            socketUDP.send(peticion); //enviar el mensaje

            // Preparase para la respuesta 
            byte[] buffer = new byte[1000];
            DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
            //recibir
            socketUDP.receive(respuesta);
            System.out.println("Respuesta: " + new String(respuesta.getData()));
            //cerrar 
            socketUDP.close();

            
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }

    }

}
