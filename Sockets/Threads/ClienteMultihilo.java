/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Threads;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteMultihilo {

    public static void main(String[] args) throws IOException {
        try {
            Scanner scn = new Scanner(System.in);
            InetAddress ip = InetAddress.getByName("localhost");

            // Conectamos al puerto 5056
            Socket s = new Socket(ip, 5056);

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            while (true) {
                // Leer menú del servidor
                System.out.println(dis.readUTF());

                String tosend = scn.nextLine();
                dos.writeUTF(tosend); // Enviar opción

                if (tosend.equals("Exit")) {
                    System.out.println("Cerrando conexión : " + s);
                    s.close();
                    break;
                }

                // Leer respuesta (Fecha u Hora)
                String received = dis.readUTF();
                System.out.println(received);
            }

            // Cerrar recursos
            scn.close();
            dis.close();
            dos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
