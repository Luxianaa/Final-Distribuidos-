# TCP (Transmision Control Protocol) - Llamada Telefonica

TCP es un protocolo **oreintado a conexion y fiable** :
```c
garantiza que los datos lleguen completos,correctos y en el mismo orden que fueron enviados
```

Antes de enviar datos se debe establecer un camino exclusivo entre cliente y servidor , a esta conexion se la llama ==`Handshake`==
TCP garantiza que los datos lleguen completos y en el mismo orden que fueron enviados.
```css
Si un paquete se pierde en el camino, TCP lo pide de nuevo automaticamente sin que el codigo se entere.!!!!!!!
```

```c
TCP no envia paquetes sino envia infromacion continua de bytesen el telofono.
```

### 4. Flujos de Entrada/Salida (Streams)

En TCP, los datos viajan por "tuberías". Esto es clave para entender cómo manipulas la información.
- **InputStream (`getInputStream`):** Tubería de llegada. De aquí _lees_ lo que el cliente te envía. Usaste `BufferedReader` para convertir esos bytes en texto legible.
- **OutputStream (`getOutputStream`):** Tubería de salida. Aquí _escribes_ para responderle al cliente. Usaste `PrintStream`.