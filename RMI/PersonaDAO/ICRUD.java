/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package PersonaDAO;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author lucia
 */
public interface ICRUD extends Remote{
    public Respuesta insertar(Persona persona) throws RemoteException;
    public Respuesta editar(Persona persona) throws RemoteException;
    public Respuesta listar() throws RemoteException;
    public Respuesta seleccionar(int id) throws RemoteException;
    public Respuesta elimnar(int id) throws RemoteException;

//    public Respuesta eliminar(int idel);
}
