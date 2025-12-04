
import java.util.Scanner;
import java.rmi.Naming;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author lucia
 */
public class Cliente {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            IOperacion operacion;
            operacion = (IOperacion) Naming.lookup("rmi://localhost/Operaciones");//instanciar el objeto remoto sel servidfor

            int op = 0;
            int a = 0;
            int b = 0;

            while (op != 5) {
                System.out.println("1.Sumar");
                System.out.println("2.Restar");
                System.out.println("3.Multiplicar");
                System.out.println("4.Dividir");
                System.out.println("5. Salir");
                System.out.print("Elegir una opcion");
                op = sc.nextInt();

                System.out.println("Introduir el primer valor:");
                a = sc.nextInt();
                System.out.println("Introducir segundo valor:");
                b = sc.nextInt();
          
            switch (op) {
                case 1:
                    System.out.println("La suma es :" + operacion.suma(a, b));
                    break;
                case 2:
                    System.out.println("La resta es :" + operacion.resta(a, b));
                    break;
                case 3:
                    System.out.println("La multi es :" + operacion.multiplicacion(a, b));
                    break;
                case 4:
                    System.out.println("La division es :" + operacion.division(a, b));
                    break;
            }
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
