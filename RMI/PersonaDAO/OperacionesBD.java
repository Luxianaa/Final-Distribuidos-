package PersonaDAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class OperacionesBD extends UnicastRemoteObject implements ICRUD {

    private PersonaDao bd = new PersonaDao("jdbc:mysql://localhost:3306/personas_bd", "root", "");

    public OperacionesBD() throws RemoteException {
        super();
    }

    @Override
    public Respuesta editar(Persona persona) throws RemoteException {
        boolean exito = this.bd.editar(persona);
        String[] mensajes = new String[]{ exito ? "Persona actualizada correctamente" : "No se pudo actualizar" };
        Persona[] datos = exito ? new Persona[]{ persona } : null;
        return new Respuesta(exito, mensajes, datos);
    }

    @Override
    public Respuesta insertar(Persona persona) throws RemoteException {
        boolean exito = this.bd.insertar(persona);
        String[] mensajes = new String[]{ exito ? "Persona registrada correctamente" : "Error al registrar persona" };
        Persona[] datos = exito ? new Persona[]{ persona } : null;
        return new Respuesta(exito, mensajes, datos);
    }

    @Override
    public Respuesta listar() throws RemoteException {
        try {
            List<Persona> lista = this.bd.listar();
            Persona[] datos = lista.toArray(new Persona[0]);
            String[] mensajes = new String[]{ "Lista obtenida correctamente" };
            return new Respuesta(true, mensajes, datos);
        } catch (Exception e) {
            return new Respuesta(false, new String[]{ "Error al listar: " + e.getMessage() }, null);
        }
    }

    @Override
    public Respuesta seleccionar(int id) throws RemoteException {
        Persona p = this.bd.obtenerPersona(id);

        if (p == null) {
            return new Respuesta(false, new String[]{ "No existe persona con ID " + id }, null);
        }

        Persona[] datos = new Persona[]{ p };
        return new Respuesta(true, new String[]{ "Persona encontrada correctamente" }, datos);
    }

    @Override
    public Respuesta elimnar(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
//    public Respuesta eliminar(int idel) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
