<?php

declare(strict_types=1);

namespace App\GraphQL\Mutations;

use App;
use Closure;
use GraphQL\Type\Definition\ResolveInfo;
use GraphQL\Type\Definition\Type;
use Rebing\GraphQL\Support\Mutation;
use Rebing\GraphQL\Support\SelectFields;
use Rebing\GraphQL\Support\Facades\GraphQL;
use App\Models\Estudiante;

class ActualizarEstudianteMutation extends Mutation
{
    protected $attributes = [
        'name' => 'actualizarEstudiante',
        'description' => 'A mutation'
    ];

    public function type(): Type
    {
        return Type::listOf(Type::string());
    }

    public function args(): array
    {
        return [
            'CI' => [
                'type' => Type::nonNull(Type::string()),
                'description' => 'CI del estudiante a actualizar',
            ],
            'nombre' => ['type' => Type::string()], 
            'primer_apellido' => ['type' => Type::string()],
            'segundo_apellido' => ['type' => Type::string()],
            'fecha_nacimiento' => ['type' => Type::string()],
        ];
    }

    public function resolve($root, array $args, $context, ResolveInfo $resolveInfo, Closure $getSelectFields)
    {
        $fields = $getSelectFields();
        $select = $fields->getSelect();
        $with = $fields->getRelations();

        $estudiante = Estudiante::find($args['CI']);

        if (!$estudiante) {
            throw new \Exception("El estudiante con CI {$args['CI']} no existe");
        }

        // Actualizar solo los campos enviados
        $estudiante->fill($args);
        $estudiante->save();

        return $estudiante;
    }
}
