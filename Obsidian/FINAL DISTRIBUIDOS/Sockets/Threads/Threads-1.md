Tenemos como objetivo que el servidor sea capaz de atender 100 clientes a lavez sin ue ninguno espere.
Para esto se necesita 3 archivos que funcionen como equipo.

## Stream
```java
Son como tuberias que sirven para leer y escribir datos que viajaran por el soccket
Caneles de entrada/salida entre cleinte-servidor.
```

- `dis.readUTF()` → para leer un String
- `dis.readInt()` → para leer un entero
- `dos.writeUTF("texto")` → para enviar un String
- `dos.writeInt(12)` → para enviar un entero

| Objetivo                                          | Clase                                     |
| ------------------------------------------------- | ----------------------------------------- |
| Enviar y recibir Strings simples                  | `PrintStream + BufferedReader`            |
| Enviar números, booleanos, etc. de forma ordenada | `DataInputStream / DataOutputStream`      |
| Leer datos byte a byte                            | `InputStream / OutputStream directamente` |
## ClientHandler


Es la parte donde defunuremos el **'que hacer'**, esta clase es el com el eplmeado que ateinede en la ventianilla que debe extender de **==``thread``==** para poder correr en paralelo, (vida independiente).


```java
class ClientHandler extends Thread {
```

Detro de la clase Necesitamos usar streams para recibir o enviar datos.

```java
DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); // Necesitamos guardar la conexión específica de ESTE cliente 
// dis y dos ayudan a enviuar datos primitivos demanera estrucutrada.
final DataInputStream dis; 
final DataOutputStream dos; 
final Socket s; 
// 2. CONSTRUCTOR: Recibimos el "teléfono descolgado" (socket) y los tubos de comunicación 
public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) { 
this.s = s; 
this.dis = dis; 
this.dos = dos; }
```

Se aplica el Metodo run() que hereda o extiene de `Thread`, esta es la logica que antes se ponia al main del servidor. esa que estaba dentro del bucle `while(true)`

```java
while (true) { 
	try {
		// Menú de opciones para el cliente 
		dos.writeUTF("¿Fecha o Hora? [Date | Time]..\n" + "Escribir Exit para terminar la conexión.");
		// Esperamos respuesta (esto bloquea SOLO a este hilo, no al servidor principal) 
		received = dis.readUTF();
		if (received.equals("Exit")) { System.out.println("Cliente " + this.s + " envía exit..."); this.s.close(); // Colgamos la llamada break; }
		Date date = new Date(); // Lógica de respuesta simple 
		switch (received) { 
		case "Date": 
		toreturn = fordate.format(date); 
		dos.writeUTF(toreturn); 
		break; 
		case "Time": 
		toreturn = fortime.format(date); 
		dos.writeUTF(toreturn); 
		break; 
		default: 
		dos.writeUTF("Entrada inválida"); 
		break; }
```
**`extends Thread` y `@Override run()`**: Es la marca de fábrica del multihilo en Java antiguo (pero efectivo).
-Independencia**: Todo lo que pasa dentro de `run()` es problema de este hilo. Si este cliente tarda una hora en responder, al servidor no le importa.

## ServerMulthilo

==`Lo que antes era el servidor principal, ahora se convierte en un despachador`==

Se necesita el puerto por donde escucha:
```java
public static void main(String[] args) throws IOException { ServerSocket ss = new ServerSocket(5056); // Puerto de escucha 
System.out.println("Servidor iniciado en puerto 5056");
```

Y se crea el bucle ==`infinito de recepcion`== 

```java
while (true) { 
Socket s = null; 
try { 
// 1. ACEPTAR: El servidor se bloquea aquí esperando que alguien llame 
s = ss.accept(); 
System.out.println("Un nuevo cliente se ha conectado : " + s);
```

Se preparan las tuberias para enviar y recibir
```java
//preparar la tuberias o strreams
DataInputStream dis = new DataInputStream(s.getInputStream()); 
DataOutputStream dos = new DataOutputStream(s.getOutputStream()); System.out.println("Asignando un nuevo hilo para este cliente");

```

Ahora creamos el trabajador (hilo) y le pasamos el cliente

```java
// 3. DELEGAR: Creamos el trabajador y le pasamos el cliente
Thread t = new ClientHandler(s, dis, dos); 
```

Para empezar implementamos el ==metodo start el cual llamara al metodo run(del thread.) en paralelo.==
```java
// 4. EJECUTAR: .start() llama mágicamente al método run() en paralelo 
t.start();
```

```css
El bucle while termina e INMEDIATAMENTE vuelve arriba a esperrar otro cleinte!!!
¡No se queda esperando a que el cliente termine!!!!
```
## Importante
**`t.start()`**: Esta es la clave. Si pusieras `t.run()`, se ejecutaría secuencialmente (mal). 
`start()` le dice a la CPU "ejecuta esto en otro lado y tú sigue avanzando".

## ClienteMultihilo 

ES el estandar, usa  usa `DataInputStream/Output` para hablar con su hilo asignado.
es lo mismo que un tcp normal

Se llama la ip y el puerto para la coenxion con el server
```java
public static void main(String[] args) throws IOException {
 try { 
 Scanner scn = new Scanner(System.in); 
 InetAddress ip = InetAddress.getByName("localhost");

```
Se conecta al puerto correspoindite

```java
Socket s = new Socket(ip, 5056); DataInputStream dis = new DataInputStream(s.getInputStream()); DataOutputStream dos = new DataOutputStream(s.getOutputStream());

```

Se crea el buvle donde se leer la informacion, despues se enviar una opcion escogida.
```java
while (true) { // Leer menú del servidor 
System.out.println(dis.readUTF()); 
String tosend = scn.nextLine(); 
dos.writeUTF(tosend); // Enviar opción 
if (tosend.equals("Exit")) { 
System.out.println("Cerrando conexión : " + s); 
s.close(); 
break; } // Leer respuesta (Fecha u Hora) 
String received = dis.readUTF(); 
System.out.println(received); 
}
```

Una vez finalice se cierra lois recursos usados para la conexion.

```java
scn.close(); 
dis.close(); 
dos.close();

```

## Servidor Web Casero (`ServidorWeb`)

Entender que ==un navegador web es solo un cliente socket "exigente".==
Este código demuestra cómo interceptar una petición HTTP real (como la que hace Chrome).

Se inicaliza el puerto estandar para web; ==`80`==
```java
public static void main(String[] args) { 
int port = 80; // Puerto estándar de la We
```

Se inicializa la web en el puerto enviado

```java
try { 
	ServerSocket server = new ServerSocket(port); 
	System.out.println("Servidor Web iniciado en puerto 80"); 
	while (true) { 
		Socket client = server.accept();
		BufferedReader fromClient = new BufferedReader(new
		InputStreamReader(client.getInputStream())); 
		PrintStream toClient = new PrintStream(client.getOutputStream());
		System.out.println("Navegador conectado"); 
		
 // LEER CABECERAS HTTP 
 // El navegador envía muchas líneas de texto. Leemos hasta encontrar una vacía.
  String a = fromClient.readLine(); 
  while (a != null && a.length() > 0) { 
	  System.out.println(a); // Imprimimos lo que pide el navegador (User-Agent, etc.) 
	  a = fromClient.readLine(); } 
	  
// RESPONDER (SIMULACIÓN) 
// Aquí estamos "engañando" al navegador enviándole texto plano. 
// Una respuesta real debería ser: "HTTP/1.1 200 OK\r\n\r\n<html>...</html>" 
	toClient.println("HTTP-Version 1.1"); 
	toClient.println("Codigo"); 
	// IMPORTANTE: Cerrar el socket para que el navegador sepa que terminamos.
	client.close(); } 
	} catch (IOException e) {
	 System.out.println(e.getMessage()); }
	  }
	   }
```

# Flujo que sigue y que hace

## 1. Proyecto `SoquetsTCPMultihilo`

Es un sistemas que funciona en un edifcio de oficinas, con ==``un recepcioniista y varios cubiculos de antecion``==
### Recepcionista (`ServerMultihilo`)

```css
Tiene como trabajo el recibir gente y asignarles un escritorio o cubiculo
```

1. Inicio: Abre el puerto 5056 y se queda esperando/.
2. **Lleghada de Cliente**: Cuando llega un cliente (`ss.accept()`), obtiene sus datos de conexion (`Socket s`) y sus canales de entrada y salidad `dis` , `dos`.
3. **Contratacion (aqui es clave)**:  En vez de atender `UN Cliente`, crea una instancia nueva dde `ClientHandlre`( un nuevo trabajador (hilo)) y le entrega el socket `s` y le dice atiendelo tu con `t.start()`.
4. **Repeticion**: Inmediatamente se olvida de ese cleinte y vuelve a esperar al siguiente en la puerta.

### Trabajador Privado (`ClienteHandler`)

Es el que realmente trabaja , hay muschos de esto por cada cliente conectado.
1. **Nacimiento**: Se lo crea cuando recibe `s` que es enviado por el `recepcionista`.
2. **Ejecucion `run()`**:  Todo esto corre en paralelo.
3. **Dialogo**:
	1. Le envía al cliente el menú: "¿Fecha u Hora?".
	2. Espera (`readUTF`) a que el cliente conteste.
	3. Si el cliente dice "Date", calcula la fecha y se la envía. Si dice "Time", envía la hora.
	4. Este bucle se repite indefinidamente hasta que el cliente diga "Exit".
4. **Despido**: Cuando recibe "Exit", cierra su conexion persona (`this.close()`) y el hilo muere.

### Cliente (`ClienteMultihilo`)
Es el usuario final.
1. **Conexion**: Llama al puerto que se conectara `5056`
2. **Bucle de charla**:
	1. Escucha lo que dice el `ClienteHandler` asignado (el menu) y lo imprime.
	2. Le lo que se escribe por teclado (`Scanner`)
	3. Envia tu texto al servidor `dos.writeUTF`
	4. Espera la respuesta (la fecha y la hora) y la imprime
## 2. Proyecto (`ServidorWeb`)
1. **Escucha Web**: abre el puerto `80`(puerto universal de internet)
2. **Intercepcion**:
	1. Cuando entras desde Chrome, el servidor acepta la conexión.
	2. **Lectura de Cabeceras:** Entra en un bucle `while` donde lee línea por línea lo que envía el navegador (`User-Agent`, `Host`, `Accept-Language`, etc.) y lo imprime en la consola de Java para que tú veas las "tripas" de la petición.
3. **Respuesta (Simulacion)**:
	1. Intenta hablar el idioma HTTP enviando `HTTP-Version 1.1`.
	2. Envía el cuerpo del mensaje: `Codigo`.
	3. Cierra la conexión. (Nota: Al ser una implementación muy básica, el navegador podría mostrar error o texto plano, ya que faltan cabeceras estándar como `Content-Type`, pero cumple el objetivo de mostrar el flujo).
## Resumen del  flujo 

1. **TCP Multihilo:** `Cliente` -> (Conecta) -> `ServerMultihilo` -> (Crea) -> `ClientHandler` _A partir de ahí:_ `Cliente` <---> `ClientHandler` (El ServerMultihilo ya está libre).
2. **Servidor Web:** `Navegador (Chrome)` -> (Petición HTTP) -> `ServidorWeb` -> (Lee texto) -> (Responde texto) -> Fin.