Se hara una conexion sencilla cliente-servidor

# Server
Este es un poco más sofisticado porque mantiene el "estado" de la conexión a diferencia de UDP.

`ServerSocket` es como el recepcionista en la puerta.
`Socket (el objeto client)` es el guia privado asignado a la visita.

Se crea el puerto y el server:
```java
int port = 5002; //port
ServerSocket server; //server
```

Se incializa el servidor en modo de listener mandandlo el puerto que usar

```java
server = new ServerSocket(port); //aqui se inicia el servidor con el puerto
System.out.println("Se inicio el servidor con éxito en puerto " + port);
```

Se entra en un ucle donde es servidor estara esperando la peticion del cliente.
Cuando el server es llamado , crea un objeto client, que sera exclusivo para hablar con el solicitante 

```java
Socket client = server.accept(); 
System.out.println("Cliente se conecto");
```

Una vez se crea el objeto cleinte , s**e establecen los canales streams(sustituo de los bytes en crudo que usa UDP), aqui usa readLine() y println()**

```java
// Para leer lo que viene del cliente 
BufferedReader fromClient = new BufferedReader(newInputStreamReader(client.getInputStream())); 
// Para escribirle al cliente
PrintStream toClient = new PrintStream(client.getOutputStream());
```

Se lee  lo que envio el cliente
```java
String recibido = fromClient.readLine(); 
System.out.println("El cliente envio el mensaje: " + recibido);
```

Por ultimo se responde
```java
toClient.println("Hola Mundo desde el Servidor");
```

`Nota: Al terminar el bloque, la conexión con este cliente específico podría cerrarse o mantenerse dependiendo de la lógica. Aquí se reinicia el loop para otro cliente.`

## Puntos clave del server
**`ServerSocket` vs `Socket`:** `ServerSocket` es el recepcionista que espera en la puerta. `Socket` (el objeto `client`) es el guía privado asignado a la visita.

**==Streams==:** No ves paquetes ni bytes crudos. Ves `readLine()` y `println()`. Java maneja la complejidad del envío.


# Cliente

Se inicia el puerto donde se comunicara con el servidor
```java
int port = 5002;
```

Se llama al servidor para hacer lo que se llama "**HandShake**", es el apreton de mano entre el server y el cleinte.
Para esto el cleinte debe essart en accetp(); -> `Socket client = server.accept();`

```java
Socket client = new Socket("localhost", port);
```

Luego se obtiene los tubos de comunicacion

```java
PrintStream toServer = new PrintStream(client.getOutputStream()); 
BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));
```

Se habla , se manda un msj al server

```java
toServer.println("Hola Mundo desde el Cliente");
```

Por ultimo se escucha lo que el server no quiere dar:

```java
String result = fromServer.readLine(); 
System.out.println("Cadena devuelta por el servidor es: " + result);
```

y se cierra conexion TCp o se cuelga, una vez que se haya hecho toda la comunicaion.

```java
client.close();
```

## Puntos Importantes Cliente

**Conexión Activa:** `new Socket("localhost", port)` hace todo el trabajo sucio de conectar (el famoso _3-way handshake_ de TCP).

**Orden:** Si envías "Hola", luego "Mundo", TCP garantiza que el servidor recibirá "Hola" y luego "Mundo". En UDP, eso no está 100% garantizado por defecto.