
Primero se tiene que crear un proyecto Laravel con api , si se pide tokenizacion igual se instala 
[[FINAL DISTRIBUIDOS/REST + GraphQL/API-REST Laravel 1|API-REST Laravel 1]]


# API-REST (Representational State Transfer)

Rest se basa en recursos, cada recurso tiene un URL unico y es manipulado por los metodos HTTP `GET,POST,PUT,PATCH,DELETE`




## Instalacion de Laravel Api con GraphQL

## Crear Proyecto
``` cs
composer create-project laravel/laravel graphql_api
cd graphql_api
```

# Habilitar API en Laravel
```css
php artisan install:api
```

# Crear un modelo, su controlador, su migracion, su seeder y su factory

```css
php artisan make:model Persona -mcrsf --api
```

# Instalacion de graphql 

```css
composer require rebing/graphql-laravel
composer require mll-lab/laravel-graphiql
composer require mll-lab/laravel-graphql-playground
```

# Publicar la configuracion de GraphQL

```css
php artisan vendor:publish --provider="Rebing\GraphQL\GraphQLServiceProvider"
```
Esto creará:
```c
config/graphql.php
```
Ese archivo define:
	**Schemas**
	**Types**
	**Queries** 
	**Mutations**


# Publicar GraphiQL (interfaz de pruebas)
```css
php artisan vendor:publish --provider="MLL\GraphiQL\GraphiQLServiceProvider"
```
# Rutas (Probar las dos por si)
```c
http://127.0.0.1:8000/graphql-playground
http://127.0.0.1:8000/graphiql
```

# Publicar GraphQL Playground
```css
php artisan vendor:publish --provider="MLL\GraphQLPlayground\GraphQLPlaygroundServiceProvider"
```



# Comandos para Crear rapido 

### Crear Type
Este describe que tendra el modelo

```css
php artisan make:graphql:type PersonaType
```

### Crear Query

Lee los datos

```css
php artisan make:graphql:query PersonasQuery
```

### Crear Mutation

Aqui se hace el CRUD (Lee,actualiza,elimina)
```css
php artisan make:graphql:mutation CreatePersonaMutation
```


# Flujo de como debo hacer

1. Se instala la ruta api
2. Se crea el recurso, o el modelo
3. Si se usa JWT se auemtna la logica de eso mas.
4. Se migra las cosas a la base
5. se crea las carpetas de Graphql
6. Se registrar en `config/graphql.php`  EJEMPLO:
```php
'schemas' => [
    'default' => [
        'query' => [
            'productos' => App\GraphQL\Queries\ProductosQuery::class,
        ],
        'mutation' => [
            'createProducto' =>App\GraphQL\Mutations\CreateProductoMutation::class,
        ],
        'types' => [
            'Producto' => App\GraphQL\Types\ProductoType::class,
        ],
    ],
],
```

7. Pruebas en GraphQL Playground

**QUERY**
```json
query {
  productos {
    id
    nombre
    precio
  }
}
```

**MUTATION**
```JSON
mutation {
  createProducto(nombre: "Laptop", precio: 1500) {
    id
    nombre
    precio
  }
}
```



# 🎯 Diferencias de endpoints
## `/graphql`

Endpoint real → lo que consume una app.

## `/graphiql`

Interfaz simple para pruebas.

## `/graphql-playground`

Interfaz avanzada → **la ideal para el examen**.

## Queries → solo lectura

##  Mutations → crear, actualizar, eliminar

## Types → estructura de datos (modelo GraphQL)

