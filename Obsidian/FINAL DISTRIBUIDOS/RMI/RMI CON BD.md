# Flujo de  desarrollo personas_db

## 1. La Base de Datos 
Antes de todo, se crea una base de datos para poder consumir los datos.
## 2. Objetos Comunes
`Sexo.java` no es obligatorio, es para usar un enum y el cleinte escoja M o F.
`Persona.java` aqui es donde se instancias los atributos que recupera la BD , su construvtor y sus metodos accesrores.
`Respuesta.java`: Usa a Persona.java, es lo que devolvera el servidor.

## 3. El contrato (Planos)

`ICRUD o IOperacionesBd.java`: Aqui usaremos Persona y Respuesta, como sabemos zqui solo se definen los metodos a usar :
```java
public interface ICRUD extends Remote{
    public Respuesta insertar(Persona persona) throws RemoteException;
    public Respuesta editar(Persona persona) throws RemoteException;
    public Respuesta listar() throws RemoteException;
```

## 4. Logical Local 
En esta parte ed donde se conecta la BD.
`PersonaDAO.java` Es el desarrollo logico , escribe la conexion usando `SELECT` `INSERT`.
```JAVA
public boolean insertar(Persona persona) {
        String sql = "INSERT INTO personas(nombres,apellidos,numero_documento,sexo) VALUES (?,?,?,?) ";
```

## 5. Implementacion RMI
Aqui se une la logica. se usa `ICRUD o IOperacionesBd` y se conecta con el DAO `PersonaDAO`
==`OperacionesBd.java`==:
- Extiende de `UnicastRemoteObject`.
- Implementa `IOperacionesBd`.
- Llama a los métodos de `PersonaDAO`.
## 6. Server
`server.java` crea un registro en el puerto `1099` el puerto default y hace un `bind` (union) de `OperacionBd` detron de un `try Catch`
```java
int port = 1099;
            
            OperacionesBD operacionesBD = new OperacionesBD();
            LocateRegistry.createRegistry(port);
            Naming.bind("OperacionesBD", operacionesBD);
            
```

## 7. Cliente
Se crea el server
`Cliente.java` hace el `lookup`(busqueda) y llama a lso metodos `invoke` Este archivo solo compila si ya tienes lista la interfaz `ICRUD o IOperacionesBd` y los modelos (`Persona`, `Respuesta`).
```java
ICRUD operacion;

            operacion = (ICRUD) Naming.lookup("rmi://localhost/OperacionesBD");
            System.out.println("correindo");
```
Aqui se aplicara la logica de como se cosumira los datos , digamos un sqwitch con opciones de listar, insertar,etc, 


