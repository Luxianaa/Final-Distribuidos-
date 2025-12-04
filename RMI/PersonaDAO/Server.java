/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonaDAO;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author lucia
 */
public class Server {
    public static void main(String[] args) {
        try {
            int port = 1099;
            
            OperacionesBD operacionesBD = new OperacionesBD();
            LocateRegistry.createRegistry(port);
            Naming.bind("OperacionesBD", operacionesBD);
            
            System.out.println("Se pudo conectar en puerto " + port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
