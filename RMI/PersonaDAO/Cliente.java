/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PersonaDAO;

import java.rmi.Naming;

import java.util.Scanner;

/**
 *
 * @author lucia
 */
public class Cliente {

    public static void main(String[] args) {
        try {
            ICRUD operacion;

            operacion = (ICRUD) Naming.lookup("rmi://localhost/OperacionesBD");
            System.out.println("correindo");

            Scanner sc = new Scanner(System.in);
            int opcion = 0;

            while (opcion != 5) {
                System.out.println("\n========= MENU CRUD PERSONAS =========");
                System.out.println("1. Insertar persona");
                System.out.println("2. Listar personas");
                System.out.println("3. Seleccionar persona por ID");
                System.out.println("4. Editar persona");
//                System.out.println("5. Eliminar persona");
                System.out.println("5. Salir");
                System.out.print("Elige una opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {

                    case 1: // INSERTAR
                        System.out.println("\n--- Insertar Persona ---");

                        System.out.print("Nombres: ");
                        String nombres = sc.nextLine();

                        System.out.print("Apellidos: ");
                        String ap = sc.nextLine();

                        System.out.print("Sexo (M/F): ");
                        String sx = sc.nextLine().toUpperCase();
                        Sexo sexo = Sexo.valueOf(sx);

                        System.out.print("Número de documento: ");
                        int numdoc = sc.nextInt();

                        Persona nueva = new Persona(nombres, ap, sexo, numdoc);
                        Respuesta r1 = operacion.insertar(nueva);

                        System.out.println(r1.getMensajes()[0]);
                        break;

                    case 2: // LISTAR
                        System.out.println("\n--- Listar Personas ---");
                        Respuesta r2 = operacion.listar();

                        if (r2.getDatos() != null) {
                            for (Persona p : r2.getDatos()) {
                                System.out.println(
                                        p.getId() + " | "
                                        + p.getNombres() + " "
                                        + p.getApellidos() + " | "
                                        + p.getSexo() + " | "
                                        + p.getNumero_documento()
                                );
                            }
                        } else {
                            System.out.println("No hay datos.");
                        }
                        break;

                    case 3: // SELECCIONAR POR ID
                        System.out.println("\n--- Seleccionar Persona ---");

                        System.out.print("ID: ");
                        int id = sc.nextInt();

                        Respuesta r3 = operacion.seleccionar(id);

                        if (r3.getDatos() != null) {
                            Persona p = r3.getDatos()[0];
                            System.out.println(
                                    p.getId() + " | "
                                    + p.getNombres() + " "
                                    + p.getApellidos() + " | "
                                    + p.getSexo() + " | "
                                    + p.getNumero_documento()
                            );
                        } else {
                            System.out.println(r3.getMensajes()[0]);
                        }
                        break;

                    case 4: // EDITAR PERSONA
                        System.out.println("\n--- Editar Persona ---");

                        System.out.print("ID a editar: ");
                        int idedit = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nuevo nombre: ");
                        String nn = sc.nextLine();

                        System.out.print("Nuevo apellido: ");
                        String na = sc.nextLine();

                        System.out.print("Nuevo sexo (M/F): ");
                        String nss = sc.nextLine().toUpperCase();
                        Sexo nsexo = Sexo.valueOf(nss);

                        System.out.print("Nuevo número de documento: ");
                        int nd = sc.nextInt();

                        Persona editada = new Persona(idedit, nn, na, nsexo, nd);

                        Respuesta r4 = operacion.editar(editada);
                        System.out.println(r4.getMensajes()[0]);
                        break;

//                    case 5: // ELIMINAR
//                        System.out.println("\n--- Eliminar Persona ---");
//
//                        System.out.print("ID a eliminar: ");
//                        int idel = sc.nextInt();
//
//                        Respuesta r5 = operacion.eliminar(idel);
//                        System.out.println(r5.getMensajes()[0]);
//                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
