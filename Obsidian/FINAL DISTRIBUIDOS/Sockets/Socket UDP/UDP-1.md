Se hara una conexion sencilla cliente-servidor

# Servidor

`ServerUDP` : Su trabajo es abrir un purto y quedarse esperando hasta que le llegue cualquier paquete.
Primero se crea un puerto donde se escuchara cleinte-servidor
```java
int port = 6789;
```
Para esto se crea un buffer en bytes reservado para enviar y recbir paquetes.
```java
byte[] buffer = new byte[1000];
```
Se crea un `DatagramPacket`  vacio donde esperara  el paquete 
```java
socketUDP.receive(peticion); //espera aqui hasta que llegue un paquete
```
Ya que no hay conexion, necesitamos saber el **puerto y la IP** del que nos enviar el mensaje para eso:
```java
System.out.print("Datagrama recibido del host: " + peticion.getAddress()); System.out.println(" desde el puerto remoto: " + peticion.getPort());
```
Despues se procesan los datos:
1. Convertir a Bytes.
2. Generar una Respuesta
3. COnvertir la respuesta a bytes
Se manda un paquete dirigido explicitamente al emisor. **agarrando su IP y su Puerto**
```java
DatagramPacket respuesta = new DatagramPacket(mensaje, response.length(),  peticion.getAddress(), peticion.getPort()); 

socketUDP.send(respuesta); // se envia la respuetsa
```

## Puntos Clave en el Server
**`new DatagramSocket(port)`:** ==**Es obligatorio asignar un puerto fijo (6789).**== Si no, el cliente no sabría a dónde enviar.
**`DatagramPacket` para recibir:** Crea con un arreglo vacío (`bufer`). Es como poner un cubo vacío esperando que caiga agua.
**Sin `accept()`:** Aquí no se aceptan conexiones. Simplemente se reciben paquetes sueltos.

# Cliente

Se crea el Emisor, el que enviara una peticion al servidior
Se asigna el mismo puerto
```java
int port = 6789;// el meme que del server
```

Se debe crear lo que se pedira, que dato o que, ademas, se debe  obtener la direccion del servidor

```java
System.out.print("Introduzca Su nombre: "); 
String dato = sc.next(); 
String ip = "localhost"; //direccion del servidor
```

Se crea un socket sin un puerto fijo , el cleinte no es necesario que tenga un puerto fijo.

```java
DatagramSocket socketUDP = new DatagramSocket(); //objet Socket
byte[] mensaje = dato.getBytes(); //el mensaje en bytes que se enviara
InetAddress hostServidor = InetAddress.getByName(ip); //la ip de; server
```

Despues se empaqueta el mensaje, se lo envia , se prepara la respuesta y se ceirra la conexion.

```java
// 2. EMPAQUETAR EL MENSAJE // Aquí SI especificamos destino (hostServidor) y puerto (6789). 
DatagramPacket peticion = new DatagramPacket(mensaje, dato.length(), hostServidor, puerto); 
// 3. ENVIAR 
socketUDP.send(peticion); 
// 4. PREPARARSE PARA LA RESPUESTA 
byte[] bufer = new byte[1000]; DatagramPacket respuesta = new DatagramPacket(bufer, bufer.length); 
// 5. RECIBIR 
socketUDP.receive(respuesta); System.out.println("Respuesta: " + new String(respuesta.getData())); 
// 6. CERRAR 
socketUDP.close();
```

## Puntos Clave en el Cliente
**Dirección Explícita:** En el `DatagramPacket`, debes poner la direccion  (`hostServidor`) y el puerto (`6789`) cada vez que envías algo. **UDP no recuerda con quién estás hablando.**

