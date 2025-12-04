/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lucia
 */

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            int port = 1099;
            //Instancia del Objeto 
            Operacion operacion = new Operacion();
            LocateRegistry.createRegistry(port);
            Naming.bind("Operaciones", operacion);
            
            System.out.println("Servidor reacy, el Objeto fue registrado como 'Operaciones'.");
            System.out.println("conectado al puerto: "+ port);
            
        } catch (RemoteException ex) {
                 System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
             } catch (AlreadyBoundException ex) {
                 System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
             } catch (MalformedURLException ex) {
                 System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
             }
    }
}
