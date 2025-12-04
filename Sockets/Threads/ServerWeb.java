/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWeb {

    public static void main(String[] args) {
        int port = 80; // Puerto estándar de la Web

        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor Web iniciado en puerto 80");

            while (true) {
                Socket client = server.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintStream toClient = new PrintStream(client.getOutputStream());

                System.out.println("Navegador conectado");

                // LEER CABECERAS HTTP
                // El navegador envía muchas líneas de texto. Leemos hasta encontrar una vacía.
                String a = fromClient.readLine();
                while (a != null && a.length() > 0) {
                    System.out.println(a); // Imprimimos lo que pide el navegador (User-Agent, etc.)
                    a = fromClient.readLine();
                }

                // RESPONDER (SIMULACIÓN)
                // Aquí estamos "engañando" al navegador enviándole texto plano.
                // Una respuesta real debería ser: "HTTP/1.1 200 OK\r\n\r\n<html>...</html>"
                toClient.println("HTTP-Version 1.1");
                toClient.println("Codigo");

                // IMPORTANTE: Cerrar el socket para que el navegador sepa que terminamos.
                client.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
