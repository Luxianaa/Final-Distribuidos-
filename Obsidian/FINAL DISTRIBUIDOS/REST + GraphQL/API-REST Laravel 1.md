# Resumen
**desarrollar y comprender el funcionamiento de una API REST en Laravel**, implementando un **sistema de autenticación mediante tokens JWT (JSON Web Tokens)**.
permite **crear, leer, actualizar y eliminar registros** (CRUD) de una entidad `Persona`, garantizando que solo los usuarios autenticados puedan acceder a las rutas protegidas.

# Creacion del Proyecto
```css
composer create-project laravel/laravel laravel-api
```
# Habilitar API en Laravel
```css
php artisan install:api
```

# Crear un modelo, su controlador, su migracion, su seeder y su factory

```css
php artisan make:model Persona -mcrsf --api
```
Esto:
- `app/Models/Persona.php`
- `database/migrations/...create_personas_table.php`
- `app/Http/Controllers/PersonaController.php`
- `database/seeders/PersonaSeeder.php`
- `database/factories/PersonaFactory.php`
- El parámetro `--api` hace que el controlador se cree con métodos tipo API (sin vistas).
# Instalar JWT (autenticación con tokens)
```css
composer require firebase/php-jwt
```


# Crear el middleware para validar el token
```css
php artisan make:middleware JwtMiddleware

```

# Codigo de middleware
```php
<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;
use Firebase\JWT\JWT;
use Firebase\JWT\Key;

class JwtMiddleware
{
    /**
     * Verifica el token JWT antes de permitir acceso a las rutas protegidas
     */
    public function handle(Request $request, Closure $next): Response
    {
        try {
            // 🔹 1. Obtener el header Authorization (formato: "Bearer <token>")
            $autorizacion = $request->header('Authorization');
            // 🔹 2. Extraer solo el token (quita los primeros 7 caracteres "Bearer ")
            $jwt = substr($autorizacion, 7);
            // 🔹 3. Leer clave secreta y algoritmo desde el archivo .env
            $key = env('JWT_SECRET');
            $algoritmo = env('JWT_ALGORITHM');
            // 🔹 4. Decodificar el token usando la librería Firebase JWT
            //      Verifica firma, expiración y obtiene los datos (payload)
            $datos = JWT::decode($jwt, new Key($key, $algoritmo));
            // 🔹 5. Agregar los datos del usuario al request
            //      (para usarlos luego en los controladores)
            $request->attributes->add(['usuario' => $datos->data]);
        }
        catch (\Exception $e) {
            // ❌ 6. Si hay error (token inválido, expirado, ausente)
            //      se devuelve una respuesta 401 "No autorizado"
            return response()->json(['status' => 'No autorizado ' . $e->getMessage()], 401);
        }
        // ✅ 7. Si todo está bien, continúa la petición al siguiente middleware/controlador
        return $next($request);
    }
}

```
# Configurar variables de entorno (.env)
```CSS
JWT_SECRET=secret
JWT_ALGORITHM=HS256
```

# Crear Login
```css
php artisan make:controller LoginController
```

```php
<?php
namespace App\Http\Controllers;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Firebase\JWT\JWT;
use Firebase\JWT\Key;
class LoginController extends Controller
{
    public function login(Request $request)
    {
        // 🔹 1. Buscar usuario por email en la base de datos
        //      (se asume que el modelo User ya está importado)
        $usuario = User::where('email', $request->email)->first();
        // ❌ 2. Si no existe el usuario → devolver error
        if ($usuario == null) {
            return response()->json([
                'mensaje' => 'Usuario inválido',
            ], 400);
        }
        // 🔹 3. Verificar que la contraseña ingresada coincida con la guardada (encriptada)
        if (Hash::check($request->password, $usuario->password)) {
            // 🔹 4. Obtener la clave y algoritmo definidos en el archivo .env
            $key = env('JWT_SECRET');
            $algoritmo = env('JWT_ALGORITHM');

            // 🔹 5. Crear el token JWT con información básica del usuario
            $time = time(); // tiempo actual
            $token = array(
                'iat' => $time,              // Fecha de emisión (issued at)
                'exp' => $time + (1200 * 60), // Fecha de expiración (+1 hora aprox.)
                'data' => [                  // Datos que viajarán dentro del token
                    'user_id' => $usuario->id,
                ],
            );

            // 🔹 6. Codificar el token con la clave secreta y el algoritmo HS256
            $jwt = JWT::encode($token, $key, $algoritmo);

            // ✅ 7. Devolver respuesta exitosa con el token generado
            return response()->json([
                'mensaje' => 'Se logró autenticar al usuario',
                'token' => $jwt,               // Token JWT
                'type' => 'bearer',            // Tipo de token (para el header Authorization)
                'expires' => $time + (1200 * 60), // Fecha de expiración
                'usuario' => $usuario          // Datos del usuario autenticado
            ], 200);
        } 
        else {
            // ❌ 8. Si la contraseña no coincide → error de autenticación
            return response()->json([
                'mensaje' => 'Contraseña inválida',
                'status' => 400
            ], 400);
        }
    }
}

```

# Proteger Rutas Api.php
```php
Route::apiResource('personas', PersonaController::class)
    ->middleware(JwtMiddleware::class);
Route::post('login', [LoginController::class, 'login']);
```

# Cuando se aca=ba migration y seed
```php 
php artisan migrate:fresh --seed
```

# Pruebas de Funcionalidad
Usando **Postman o Thunder Client**

```css
POST http://127.0.0.1:8000/api/login
```
- **Body JSON in Raw**
	Se ponen lois datos con los que se logeara para poder accedeer al crud de personas
```JSON
{
  "email": "lu@edu.com",
  "password": "password"
}
```
- OUTPUT
```JSON
{
  "mensaje": "Se logró autenticar al usuario",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", #TOKEN FUNDAMENTASL PARA HAcer consultas en la tabla CRUD
  "type": "bearer"
}
```

# Acceder a rutas protegida
## Postman
![[Pasted image 20251020131212.png]]
## Thunder Client
![[Pasted image 20251020131341.png]]

# Comandos
| Tipo           | Comando                                               | Descripción            |
| -------------- | ----------------------------------------------------- | ---------------------- |
| 📦 Proyecto    | `composer create-project laravel/laravel laravel-api` | Crea nuevo proyecto    |
| 🌐 API base    | `php artisan install:api`                             | Instala estructura API |
| 🧱 Migrar      | `php artisan migrate`                                 | Crea tablas            |
| 🌱 Sembrar     | `php artisan db:seed`                                 | Inserta datos          |
| 🔄 Reset total | `php artisan migrate:fresh --seed`                    | Reinicia DB con datos  |
| 👤 Modelo API  | `php artisan make:model Persona -mcrsf --api`         | Crea modelo y recursos |
| 🔐 Middleware  | `php artisan make:middleware JwtMiddleware`           | Middleware para token  |
| 🧾 Login       | `php artisan make:controller LoginController`         | Controlador de login   |
| 🧩 Servidor    | `php artisan serve`                                   | Inicia el servidor     |
| 🧭 Rutas       | `php artisan route:list`                              | Ver rutas disponibles  |
|                |                                                       |                        |
