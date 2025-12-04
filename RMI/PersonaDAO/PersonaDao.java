/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonaDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucia
 */
public class PersonaDao {

    private Connection conexion;

    public PersonaDao(String Url, String usuario, String password) {
        try {
            this.conexion = DriverManager.getConnection(Url, usuario, password);
        } catch (SQLException e) {
            e.printStackTrace( );
//            System.out.println("No hay cpnexion a la base");

        }

    }

    public boolean insertar(Persona persona) {
        String sql = "INSERT INTO personas(nombres,apellidos,numero_documento,sexo) VALUES (?,?,?,?) ";
        boolean aux = false;
        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setString(1, persona.getNombres());
            instruccion.setString(2, persona.getApellidos());
            instruccion.setInt(3, persona.getNumero_documento());
            instruccion.setString(4, String.valueOf(persona.getSexo()));

            instruccion.execute();
            return true;

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return aux;
    }

    public List<Persona> listar() {
        List<Persona> auxiliar = new ArrayList<>();

        String sql = "SELECT * FROM personas";

        try {
            Statement instruccion = conexion.createStatement();
            ResultSet resultado = instruccion.executeQuery(sql);

            while (resultado.next()) {
                Persona p = new Persona(
                        resultado.getInt("id"),
                        resultado.getString("nombres"),
                        resultado.getString("apellidos"),
                        Sexo.valueOf(resultado.getString("sexo")),
                        resultado.getInt("numero_documento")
                );
                auxiliar.add(p);
            }

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return auxiliar;
    }

    public boolean editar(Persona persona) {
        String sql = "UPDATE personas SET nombres=?, apellidos=?, edad=?,sexo=?, numero_documento=? WHERE id=?";
        boolean aux = false;
        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setString(1, persona.getNombres());
            instruccion.setString(2, persona.getApellidos());
//            instruccion.setString(3, String.valueOf(persona.getSexo()));
            instruccion.setInt(4, persona.getNumero_documento());
            instruccion.setInt(5, persona.getId());
            instruccion.executeUpdate();
            return true;

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return aux;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM personas WHERE id=?";
        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setInt(1, id);
            instruccion.executeUpdate();
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }

    public Persona obtenerPersona(int id) {
        String sql = "SELECT id, nombres, apellidos, sexo, numero_documento FROM personas WHERE id=?";
        try {
            PreparedStatement instruccion = conexion.prepareStatement(sql);
            instruccion.setInt(1, id);
            ResultSet resultado = instruccion.executeQuery();
            Persona p = new Persona(resultado.getInt("id"), resultado.getString("nombres"),
                    resultado.getString("apellidos"), Sexo.valueOf(resultado.getString("sexo")), resultado.getInt("numero_documento"));

            return p;

        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
        return null;
    }
}
