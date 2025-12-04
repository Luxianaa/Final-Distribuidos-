<?php

declare(strict_types=1);

namespace App\GraphQL\Mutations;

use Closure;
use GraphQL\Type\Definition\ResolveInfo;
use GraphQL\Type\Definition\Type;
use Rebing\GraphQL\Support\Mutation;
use Rebing\GraphQL\Support\SelectFields;
use App\Models\Estudiante;
use Rebing\GraphQL\Support\Facades\GraphQL; 

class CrearEstudianteMutation extends Mutation
{
    protected $attributes = [
        'name' => 'crearEstudiante',
        'description' => 'A mutation'
    ];

    public function type(): Type
    {
        return GraphQL::type('Estudiante');
    }

    public function args(): array
    {
        return [
            // Define your mutation arguments here
            'nombre' => [
                'type' => Type::nonNull(Type::string()),
                'description' => 'The name of the student',
            ],
            'primer_apellido' => [
                'type' => Type::nonNull(Type::string()),
                'description' => 'The first surname of the student',
            ],
            'segundo_apellido' => [
                'type' => Type::string(),
                'description' => 'The second surname of the student',
            ],
            'CI' => [
                'type' => Type::nonNull(Type::string()),
                'description' => 'The CI of the student',
            ],
            'fecha_nacimiento' => [
                'type' => Type::nonNull(Type::string()),
                'description' => 'The birth date of the student',
            ],

        ];
    }

    public function resolve($root, array $args, $context, ResolveInfo $resolveInfo, Closure $getSelectFields)
    {
        $fields = $getSelectFields();
        $select = $fields->getSelect();
        $with = $fields->getRelations();

        $estudiante = Estudiante::create($args);

        return $estudiante;
    }
}
