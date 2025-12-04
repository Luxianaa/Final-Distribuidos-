## UDP (User Datagram Protocol) - El correo Postal

UDP es un protocolo de "**mejor esfuerzo**" y "**sin conexion**" (*Transporte no orientado a conexion*), 
```c
Envia los datos rapido pero no garantiza que llegaran bien, ni en orden
```
Se lo usa cuando la velocidad es mas importatne qu ela fiabilidad, ejemplo: para audio,juegos en linea,VoIP.
No llama antes de enviar, solo empaqueta el mensaje y lo envia a su destino por la red.

**Datagramas**: La info viaja en paquetes independientes si se envian 3 cartas(ejemplo), pueden llegar en desorden, pueden perderse, y este protocolo no avisa.
**Velocidad**: Como no verifica si llego y no mantiene una conexion abierta, es demasiado rapido. Por lo que se lo suele usar para Stream o juegos online.

`buffer` : es una region de memoria temporal, que se utiliza para almacenar datos que se envian y se reciben  a traves de una conexion a red (temporalmente.)

```java
DatagramSocket socketUDP = new DatagramSocket(port); 
byte[] bufer = new byte[1000]; // Espacio reservado para el mensaje entrante

```



