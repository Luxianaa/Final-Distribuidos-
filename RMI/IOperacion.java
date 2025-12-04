/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author lucia
 */

import java.rmi.Remote; 
import java.rmi.RemoteException;
//SE UTILIZAN ESTAS DOS LIBRERIAWS PARA PODER NOTIFCAR SI EXISTE UN EROR DE CONEXION EN RED.

//Se crean los metodos que se implemetnaran en el objeto remoto.
public interface IOperacion extends Remote{
    public int suma (int a, int b) throws RemoteException;
    public int resta (int a, int b) throws RemoteException;
    public int multiplicacion (int a, int b) throws RemoteException;
    public int division (int a, int b) throws RemoteException;
}
