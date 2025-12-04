Refiriendonos a los sockets de TCP basico , el server tiene un defecto terrible, que, si el usuario A se conectaba, el servidor entraba en un bucle `while` para hablar con el.
Entonces, si venia el cliente B y se intentaba conectar en ese momento, su peticion se queda en cola de espera hasta que acabe el usuario A.
**El servidor es Egoista**

**La Solucion a esto es**: ==`Servicio Concuerrente (Multihilo)`==
La **concurrencia** permite manejar multiples tareas **al mismo tiempo**

## Que es un Thread?
Un hilo es una unidad de ejecucion ligrea dentro de un proceso. el procersos es (el programa `main`) el cual es pesado y tiene mucha memoria.
```css
(el hilo y el proceso)
Comparte la misma memoria pero ejecutan las instrucciones de forma indepediente.!!!
```

## Modelo "Dispatcher-Worker" (Recepcionista-Trabajador)
Es un patron de disenio que se esta usando en el code del ing `SoquetsTCPMultihilo`
1. **Hilo Principal (Dispatcher/Recepcionista)**:  
```css
- Tiene una sola mision q es estar en la puerta server.accept()
- No procesa datos, no calcula, no charla, nada.	
- Apenas llega un .cliente lo que hace es contratar un .trabajador.!!!!
```
2. **Hilos Trabajadores (Workers)**
```css
- Creados bajo demanda.
- Tiene vida util ligada a la conexion con el cliente
- Cuando el cliente se desconecta, el hilo muere y libera recursos.
```

## Teoria detras del Servidor Web
Como se sabe la web trabaja con reglas **HTTP**.

Un servidor Web es **es simplemente un Socket TCP que entiende un idioma específico**.
1. **Socket TCP:** Usa las mismas herramientas que ya viste (`ServerSocket`, `accept`). La web necesita fiabilidad (TCP), no velocidad con pérdidas (UDP).
2. **Puerto 80:** Es una convención social. Si abres un socket en el puerto 80, los navegadores asumen que hablarás HTTP.
3. **Protocolo Texto Plano:** HTTP es legible por humanos.
    - **El Cliente (Navegador) dice:** "GET /index.html HTTP/1.1" (Dame la página de inicio).
    - **El Servidor responde:** "HTTP/1.1 200 OK" (Aquí la tienes) seguido del contenido HTML.
En tu archivo `ServidorWeb.java`, estás implementando esto "a mano". Estás leyendo las líneas de texto que te envía Chrome o Firefox y tratas de responderle en su idioma.


|**Concepto**|**Sockets I (Lo que vimos)**|**Sockets II (Lo que veremos ahora)**|
|---|---|---|
|**Capacidad**|1 Cliente a la vez (Secuencial).|N Clientes simultáneos (Concurrente).|
|**Arquitectura**|Monolítica (Todo en el main).|Distribuida en Hilos (Dispatcher/Worker).|
|**Protocolo**|Texto libre (Inventado por ti).|HTTP (Estándar mundial) o Protocolo propio estructurado.|

