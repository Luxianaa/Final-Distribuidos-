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
public class ServerTCP {
    public static void main(String[] args) {
        int port = 5002; // SE CREA EL PUERTO DE CONEXION
        ServerSocket server; 
        
        try {
            //Se inicia el server de escucha
            server = new ServerSocket(port);
            System.out.println("Se inicio el server en el puerto: " + port);
            
            while(true){
                //Aceptar la conexion
                //el server se detiene AQUI. Cuando alguien lo llama crea un objeto (client)!!!
                //ese objeto cleinte sera exclusivo para hablar con esa persona!!!!!
                Socket client = server.accept();
                System.out.println("El cliente se conecto ");
                
                // Se estable canales de comunicacion (STREAMS)
                //Para leer lo que viene del cliente
                
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                //para escribile al cliente
                PrintStream toClient = new PrintStream(client.getOutputStream());
                
                //leer
                String recibido = fromClient.readLine();
                System.out.println("MENSAJE DESDE EL CLEINTE : " + recibido);
                
                //Responder
                toClient.println("Hola como es desde el SERVER");
                
                // Nota: Al terminar el bloque, la conexión con este cliente específico podría cerrarse
                // o mantenerse dependiendo de la lógica. Aquí se reinicia el loop para otro cliente.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
