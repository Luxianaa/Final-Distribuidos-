**REST (Representational State Transfer)**
hhttp response y request

SOAP se usa wsdl porque se neceista definir que metodos se van a exponer en el server.
que nombre tiene los metodos, que parametros necesita etc.


**REST** es mas una arquitectura que se basa en metodos http
REST no necensita ninugun tipo de libreria a difertencia de SOAP.
SOAP usa xml, REST JSON(mas corto y legible).
EN REST se habal de recursos, un simple recurso con muchas acciones.


REST , no necesitamos tener los nombres de los metodos, por ende no necesitamos un WSDL,
REST es enfoque para web.
Su diferentes recurosos tiene 5 METODOES
==HTTP (GET, POST, PUT, DELETE). y a veces de usa PATCH==
- **Recursos**: Cada entidad o dato (por ejemplo, “persona”, “producto”) se trata como un recurso identificable por una URL.  
    Ejemplo: `https://miapi.com/api/personas/123`
- **Verbos HTTP**:
    - **GET**: obtener datos
    - **POST**: crear un nuevo recurso
    - **PUT** o **PATCH**: actualizar un recurso existente
    - **DELETE**: eliminar un recurso


- **Stateless (sin estado)**: cada solicitud HTTP debe contener toda la información necesaria — no confiar en estado del servidor entre peticiones.
- **Formatos de representación**: normalmente JSON (y a veces XML). El cliente y el servidor acuerdan formatos. JSON es muy popular por su ligereza y compatibilidad.
- **URLs limpias y semánticas**: las rutas deben reflejar recursos, no acciones verbales (por ejemplo `/api/personas/5` en lugar de `/api/getPersonaById`).
- **Uso de códigos de estado HTTP**: por ejemplo, 200 OK, 201 Created, 404 Not Found, 400 Bad Request, 500 Internal Server Error.

la ventaja de usar estandares es que **cualquiera puede usarlo**
hay seis restrcciones para usar correctamente REST:

| Nº  | Restricción         | Qué significa                                          | Ejemplo en ASP.NET                        |
| --- | ------------------- | ------------------------------------------------------ | ----------------------------------------- |
| 1️⃣ | Cliente–Servidor    | Separar interfaz (cliente) y datos (servidor)          | Controller (servidor) + Postman (cliente) |
| 2️⃣ | Stateless           | No guardar sesión entre peticiones                     | Cada request envía su token               |
| 3️⃣ | Cacheable           | Permitir cache en respuestas                           | `[ResponseCache]`                         |
| 4️⃣ | Interfaz uniforme   | URLs limpias, verbos HTTP correctos, códigos adecuados | `/api/personas/1` con GET/POST/PUT/DELETE |
| 5️⃣ | Sistema en capas    | Arquitectura modular y escalable                       | API Gateway + API + DB                    |
| 6️⃣ | Código bajo demanda | Servidor puede enviar scripts ejecutables              | Opcional (raro)                           |
# PATCH

|Método|Uso|Ejemplo|
|---|---|---|
|**PUT**|Reemplaza el objeto completo|Actualizar **todos** los campos de una persona|
|**PATCH**|Modifica solo algunos campos|Cambiar solo el apellido o el email|

Ventajas de 
progrmacion del lado del cliente JS(fecthc,ajax)


Cada recursos tiene un identificador unico, en otra palabra su **ID**

# STAUS CODE
es el codigo del erstado de la respuesta esto se ve en el http respones
	https://http.dev/ 
son importantes porque aqui no se puede juagr con el **TRY CATCH** ==creo== 
el codigo tHTTTP TIENE LA CABEZA Y EL CUERPO
se puede mandar informacion por el cuerpo y la cabeza.
	Se aplica el mecanismo de seguridad para solicityudes en la **CABECERA**
soliciutd el metodo,ruta y body




SOAP 
utiliza xml,
sus acciones estan definididas con el nombre en el WSDL
solo una URL para el servicio

REST
puede usar los metodos d
usa JSON y,XML
multipoles URL


microservicios rest se puede hacer en lo que se quiera.

https://api.laravel.com/docs/12.x/index.html


el web.php esta protegido
el api.php

Stateless, sin estado un rest no tiene que haber estado.
### JWT (JSON Web Token)
Es un objeto de una cadena compacta y segura la cual ayudara a la seguridad.
Es un **token (cadena de texto codificada)** que confirma que un usuario **ya está autenticado** y tiene permiso para acceder a ciertos recursos.
**Hash** es un resumen, que da una cadena.

==**un token JWT siempre tiene tres partes principales**, y entender eso es clave para saber cómo funciona la autenticación moderna.==

## Partes de JWT

| Parte            | Nombre                     | Contenido                                   | Ejemplo                                            |
| ---------------- | -------------------------- | ------------------------------------------- | -------------------------------------------------- |
| 🟦 **Header**    | Algoritmo + tipo           | `{ "alg": "HS256", "typ": "JWT" }`          | `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9`             |
| 🟨 **Payload**   | Datos del usuario (claims) | `{ "id": 1, "email": "lucia@example.com" }` | `eyJpZCI6MSwiZW1haWwiOiJsdWNpYUBleGFtcGxlLmNvbSJ9` |
| 🟥 **Signature** | Verificación               | HMACSHA256(header + payload + secret)       | `P7Fj8cJv0u_h7EwZKYb9UQHbd4oSM0pP7p4v0y3To6M`      |

# Claims
Los _claims_ son **datos que describen quién es el usuario y qué puede hacer**
### ejemplo
Supón que un usuario inicia sesión con su correo:
```json
{
  "email": "lucia@example.com",
  "rol": "admin",
  "id": 7
}

```

# Tipos de Claim
| Tipo                      | Descripción                                                                                           | Ejemplo                                                                                     |
| ------------------------- | ----------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------- |
| **1️⃣ Registered claims** | Son _estándares definidos por el RFC 7519_ (no obligatorios, pero recomendados).                      | `"iss"` (emisor), `"sub"` (ID del sujeto), `"exp"` (expiración), `"iat"` (fecha de emisión) |
| **2️⃣ Public claims**     | Son claims personalizados que pueden ser compartidos entre sistemas, siempre que usen nombres únicos. | `"email"`, `"role"`, `"name"`                                                               |
| **3️⃣ Private claims**    | Claims creados específicamente para tu aplicación o negocio.                                          | `"cliente_id"`, `"sucursal"`, `"nivel_acceso"`                                              |
Estandar es una
###fecha Uni

Se usa una hora de Greenwich, para tomarlos como una hora estandar

### Explicacion de partes de un TOken

Sí: **un token JWT tiene 3 partes**, separadas por puntos (`.`):
```json
HEADER.PAYLOAD.SIGNATURE
```
Como se ve:
```json
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.
eyJpZCI6MSwiZW1haWwiOiJsdWNpYUBleGFtcGxlLmNvbSIsInJvbGUiOiJhZG1pbiJ9.
bFJjQm5haHhXYzF4V0JlaE1CNkJxRElqR0U0cE92b3p4dHhCd2g0Q0pkdz0
```

# **HEADER** — Encabezado
### Qué es:
Contiene **metadatos sobre el token**, principalmente:
- Qué tipo de token es (`JWT`)
- Qué algoritmo se usó para firmarlo (por ejemplo `HS256`)
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

### **PAYLOAD** — Cuerpo o Datos (_Claims_)
### Qué es:
Aquí están los **datos del usuario** o **claims**, es decir, la información que el servidor quiere incluir en el token.
```json
{
  "id": 1,
  "email": "lucia@example.com",
  "role": "admin",
  "exp": 1734373772
}
```

### **SIGNATURE — Firma digital**

Garantiza que el token **no ha sido alterado**.  
Se genera tomando el _header_ y el _payload_, uniéndolos y firmándolos con una **clave secreta** del servidor.

```json
HMACSHA256(
  base64UrlEncode(header) + "." + base64UrlEncode(payload),
  clave_secreta
)
```
Imagina que JWT es como un **carnet digital firmado** 
- El **Header** es el tipo de documento
- El **Payload** son los datos impresos del usuario
- La **Signature** es el sello oficial que garantiza que no fue falsificado

**==Con este token recien se puede infresar a esos datos.==**
# Base 64
**Base64** es un **método de codificación**, no de encriptación.  
Su función es **convertir datos binarios (como texto, imágenes o archivos)** en una **cadena de caracteres seguros y legibles**, usando solo letras, números y algunos símbolos.
 **Base64 transforma datos a texto plano** para poder enviarlos fácilmente por internet.
 
----------- 



Se modifica la verswion para no cagar a los que cosnumen.

poner seguridad a nuestra ap
```bash
php artisan route:list --path-api
```

POner seguridad a nuestra api:
### Instalacion de libreria para JWT

```bash
composer require firebase/php-jwt
```

Despues en el .env 
todos los datos sencibles se ponen en el env
**Middleware**: es un coduigo que se ejecuta antes de ejecutar el codigo principal.
So,hacemos para JTW
```bash
php artisan make:middleware
```

Se agregan en las librerias
token mas comun es el bearear
- **[Token Bearer](https://www.google.com/search?sca_esv=67b7e7efba1416c5&cs=1&sxsrf=AE3TifNnHvdBUHx7s10BoXqGXFN-fq1NSw%3A1760650832312&q=Token+Bearer&sa=X&ved=2ahUKEwi_2pK716mQAxUorZUCHQILEJMQxccNegQIBRAB&mstk=AUtExfDdScD2OYJWCrtl8XEHc0AB2EUvHTTfzEBGI9V3Zl48QuSFk1lXsJCMIJs6HwtKyzogtnqagCFL0ZFThSBD1svBVUlASAVHqWh2QlLzYEbkRw3nMyY-2PUy8_R5h6ykAxG224vrN9aL-g8ZA1w1tgoxRSyfNkOBbIVoxQDCMMAds-GqZnO1GJX3GRGD0gP4wAG5CP22izSgw5y50ZJpIdhrfDM-hOAwQLnS1LWUGDxjDimdqWxDSl-BaEOdODCati4up_DksZVb753EIuZkAokq&csui=3):** 
Es un tipo de token de acceso que funciona bajo el principio de que quien posea el token ("el portador") tiene permiso para acceder al recurso, sin necesidad de demostrar la posesión con una clave criptográfica adicional.