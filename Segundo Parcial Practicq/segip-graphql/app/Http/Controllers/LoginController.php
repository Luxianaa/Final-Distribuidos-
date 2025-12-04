<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Firebase\JWT\JWT;
use Firebase\JWT\Key;

class LoginController extends Controller
{
    public function login(Request $request)
    {
        // Validar que envió CI
        if (!$request->CI) {
            return response()->json([
                'error' => 'Debe enviar el CI',
            ], 400);
        }

        $key = env('JWT_SECRET');
        $time = time();

        // Payload del token
        $payload = [
            'iat' => $time,               // momento en que se creó
            'exp' => $time + 3600,        // expira en 1 hora
            'data' => [
                'CI' => $request->CI,     // incluir el CI en el token
            ],
        ];

        // Generar JWT
        $jwt = JWT::encode($payload, $key, 'HS256');

        return response()->json([
            'mensaje' => 'Token generado correctamente',
            'token' => $jwt,
            'type' => 'bearer',
            'expires' => $time + 3600,
        ], 200);
    }
}
