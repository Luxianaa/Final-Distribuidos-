/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;
import java.io.*;
import java.net.*;

public class ServerMultihilo {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056); // Puerto de escucha
        System.out.println("Servidor iniciado en puerto 5056");

        // Bucle infinito de "recepción"
        while (true) {
            Socket s = null;

            try {
                // 1. ACEPTAR: El servidor se bloquea aquí esperando que alguien llame
                s = ss.accept();

                System.out.println("Un nuevo cliente se ha conectado : " + s);

                // 2. PREPARAR TUBERÍAS
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Asignando un nuevo hilo para este cliente");

                // 3. DELEGAR: Creamos el trabajador y le pasamos el cliente
                Thread t = new ClientHandler(s, dis, dos);

                // 4. EJECUTAR: .start() llama mágicamente al método run() en paralelo
                t.start();

                // El bucle while termina e INMEDIATAMENTE vuelve arriba a esperar otro cliente.
                // ¡No se queda esperando a que el cliente termine!

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}