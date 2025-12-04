RMI o Remote Methoon es una tecnologia de JAVA  **permite que un programa JAVA llame a ==metodos de un objeto que esta en otra maquina ==,proceso o en la misma, como si fuera un objeto loca.**

```css
El servidor mantiene el estado, el cliente solo invoca.!!!!!!!!
```

```cs
En resumen, RMI te permite dividir tu lógica en carpetas (`servidor`, `cliente`, `común`) y hacer que interactúen de forma transparente, encargándose él de toda la complejidad de sockets, puertos y conversión de datos.
```

Es como hacer un `obj.method()` pero ese `obj` no esta en tu PC, si no que esta en otro servidor remoto.

RMI se lo usa para aplicaciones distribuidas en Java donde hay:
	Un servidor que expone objetos remotos
	Hay uno o varios **Clientes** que llaman metodos de esos objetos.

RMI ayuda con la distribucion de la logica :
```css
 Parte del código corre en el servidor (lógica pesada, acceso a BD, etc.).
 Parte corre en el cliente (interfaz gráfica, validaciones pequeñas).
```

**Y RMI se encarga de:
- Abrir conexión
- Serializar parámetros
- Enviar por red
- Ejecutar en el servidor
- Recibir resultado
- Devolvértelo**

# Parte clave

## 1. Interfaz Remota
Es una interface que usa `java.rmi.Remote`, sus metodos lanzan `RemoteException`
**Define lo que el servidor ofrece a los clientes**
 extends `Remote` esto es por si existen errores de conexion con la red. El server infroma al cleinte que puede habver fallos de red.
En resumen:

```php
Es donde se define las reglas del juego, por ejemplo: se puede definir los metodos a usar: insertar(), actualizar(), seleccionar()
```

## 2. Objeto Remoto  
Es donde se desglosa la logica que tendra cada metodo implementado en la interface, se llama al metodo `ainsertar()` este objeto implementa la logica de ese metodo y se contacta con la base de datos.
Hereda el metodo ==`UnicastRemoteObject`==  el cual ayuda a que este objeto se vuelva **Transportable**, JAVA abre un puerto  automaticamente  y se queda escuchando peticiones para ese objeto.

## 3. RMI Registry
Es el servidor, se encarga de relacionar un **nombre con el objeto remoto**
1. `LocateRegistry.createRegistry(1099)`: Levante el servidor en el puerto 1099.
2. `Naming.bind("OperacionesBd", operacionesBd)`: **Inscribe su nombre.** Dice: "Quien busque 'OperacionesBd**=='(Objeto Remoto)==**, rediríjanlo a este objeto".\

## 4. Cliente RMI (Stub)
**Busca el objeto e Invoca sus metodos** 
Cuando el cliente hace eso `Naming.lookup(".../OperacionesBd")`



## 1. Crear una interfaz remota
Extiende `java.rmi.Remote`
Todo los metodos lanzan `RemoteException`
```java
public interface Operaciones extends Remote {
    int sumar(int a, int b) throws RemoteException;
}
```
## 2. Implementar la interfaz en una clase remota
Extiene `UnicastRemoteObject`
Implementa todos los metodos remotos
```java
public class OperacionesImpl extends UnicastRemoteObject implements Operaciones {
    public OperacionesImpl() throws RemoteException { super(); }
}
```

## 3 Crear Servidor RMI
Iniciar el `Registry`
Registrar el Objeto Remoto
```java
public class OperacionesImpl extends UnicastRemoteObject implements Operaciones {
    public OperacionesImpl() throws RemoteException { super(); }
}
```

## 4. Crear Cliente 
Busca el objeto remoto usando `lookup`
Invoca los metodos
```java
Operaciones op = (Operaciones) Naming.lookup("rmi://localhost:1099/Operaciones");
op.sumar(2,3);
```

## 5. Reglas importantes de los metodos remotos
- Deben estar declarados en la interfaz remota.
- Siempre deben lanzar `RemoteException`.
- Argumentos y retornos deben ser:
    - tipos primitivos
    - objetos serializables (`implements Serializable`)
    - o interfaces remotas

## 6. Serializacion en RMI
Para poder enviar objetos complejos como (Persona,Respuesta,) `Serializable`
```java
public class Persona implements Serializable { ... }
```


```css
Cliente → lookup() → obtiene stub remoto
Cliente → stub → llama método remoto
Stub → envía solicitud por la red
Servidor → ejecuta método real
Servidor → devuelve resultado
Stub → cliente recibe respuesta como si fuera local
```
