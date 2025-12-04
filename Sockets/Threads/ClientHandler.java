package Threads;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author lucia
 */
//Extender de Thread, esto permite qu ela clase pueda correr en paralelo!!!!!!!!!
public class ClientHandler extends Thread {

    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");

    //Para guardar la conexion especidifca con este cleinte
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    // 2. CONSTRUCTOR: Recibimos el "teléfono descolgado" (socket) y los tubos de comunicación
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    //3 metodo RUN, aqui va la logica que antes estaba en el main
    @Override
    public void run() {
        String received;
        String toreturn;

        while (true) {
            try {
                //Menu de opciones para el cliente

                dos.writeUTF("¿Fecha o Hora? [Date | Time]..\n"
                        + "Escribir Exit para terminar la conexión");
                //Espera la respuesta (esto solo bloeua a este hilo, no al server)

                received = dis.readUTF();
                if (received.equals("Exit")) {
                    System.out.println("Cliente" + this.s + " encia exit... ");
                    this.s.close(); //se cuelga la llamda
                    break;
                }
                Date date = new Date();

                // Lógica de respuesta simple
                switch (received) {
                    case "Date":
                        toreturn = fordate.format(date);
                        dos.writeUTF(toreturn);
                        break;
                    case "Time":
                        toreturn = fortime.format(date);
                        dos.writeUTF(toreturn);
                        break;
                    default:
                        dos.writeUTF("Entrada inválida");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Limpieza de recursos al terminar
        try {
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
