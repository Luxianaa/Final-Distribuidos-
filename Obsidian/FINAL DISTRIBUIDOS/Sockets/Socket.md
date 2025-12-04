Un socket es una puerta que permite que un progrma salga de la computador y entre a la red.
Es como un enchufe que conecta dos programas para qeu hablen entre si.
Un Socket esta conformado por:
```cs
1. IP
2. Port
3. Protocolo (TCP o UDP)
```

Una vez que **se abre esta puerta** se necesita **un protocolo de conexion** **o transporte**. ==aqui entra UDP/TCP==
**Los sockets son las herramientas** para usar esos protocolos en un programa.
```java
TCP/UDP = carreteras  
Sockets = los autos que viajan  
Tu aplicación = los pasajeros
```
## UDP (User Datagram Protocol)
[[Intro-UDP]]
## TCP (Transmission Control Protocol)

[[Intro-TCP]]

```c
TCP prioriza la confiabilidad, UDP prioriza la velocidad.
```

```css
TCP y UDP son todo lo contrario en cuanto a caracteristicas

TCP- Seguridad y orden, conexion cliente servidor.
UDP - Velocidad e inseguro y no importa como llego(desordenado,incompleto), pero llega.
```

| **Característica**   | **UDP (Tu carpeta SoquetsUDP)**         | **TCP (Tu carpeta SoquetsTCP)**             |
| -------------------- | --------------------------------------- | ------------------------------------------- |
| **Tipo de conexión** | Sin conexión (No hay saludo inicial).   | Orientado a conexión (Requiere `accept()`). |
| **Unidad de datos**  | Datagrama (Paquete discreto).           | Stream (Flujo de bytes).                    |
| **Fiabilidad**       | Baja (Puede perderse o desordenarse).   | Alta (Garantiza entrega y orden).           |
| **Uso ideal**        | Velocidad crítica (Video, Juegos, DNS). | Precisión crítica (Web, Email, Archivos).   |

| Característica                    | UDP                               | TCP                                  |
| --------------------------------- | --------------------------------- | ------------------------------------ |
| **Conexión**                      | No hay conexión                   | Sí hay conexión (handshake)          |
| **Entrega garantizada**           | No                                | Sí                                   |
| **¿El servidor acepta clientes?** | No                                | Sí (`accept()`)                      |
| **Tipo de socket**                | `DatagramSocket`                  | `ServerSocket` y `Socket`            |
| **Formato de datos**              | Paquetes (DatagramPacket)         | Streams (BufferedReader/PrintStream) |
| **Velocidad**                     | Muy rápido                        | Más lento                            |
| **Fiabilidad**                    | Baja                              | Alta                                 |
| **Uso típico**                    | Juegos, VoIP, sensores, streaming | Web, archivos, login, mensajería     |


# Conclusion