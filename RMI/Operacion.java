/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lucia
 */
//import java.rmi.Remote; 
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;// ESTO HARA QUE ESTO SE CONVIERTA EN UN OBJETO REMOTO O EXPORTABLE


public class Operacion extends UnicastRemoteObject implements IOperacion{
    
    public Operacion()throws RemoteException{
        super();
    }

    @Override
    public int suma(int a, int b) throws RemoteException {
        return(a+b);
    }

    @Override
    public int resta(int a, int b) throws RemoteException {
        return(a-b);
    }

    @Override
    public int multiplicacion(int a, int b) throws RemoteException {
        return (a * b);
    }

    @Override
    public int division(int a, int b) throws RemoteException {
        return (a / b);
    }
    
}
