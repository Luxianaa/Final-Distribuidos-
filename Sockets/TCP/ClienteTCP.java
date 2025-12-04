/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;
import java.net.*;
import java.io.*;

/**
 *
 * @author lucia
 */
public class ClienteTCP {
    public static void main(String[] args) {
        int port = 5002;
        try {
            //lamar al servidor para hacer el Handshake!!
            // Si el servidor no está en 'accept()', esto falla inmediatamente.
            Socket client = new Socket("localhost", port);
            
            // Obtener los tubos de comunicacion
            
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            // Hablar
            toServer.println("Hola desde el CLIENTE");
            
            //escuchar
            
            String result = fromServer.readLine();
            System.out.println("Cadena devuelta por el server: " + result);
            
            // Colgar o terminar
            client.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
