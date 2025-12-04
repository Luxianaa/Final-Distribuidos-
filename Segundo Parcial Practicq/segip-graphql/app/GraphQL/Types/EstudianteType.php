<?php

declare(strict_types=1);

namespace App\GraphQL\Types;

use Rebing\GraphQL\Support\Type as GraphQLType;
use GraphQL\Type\Definition\Type;//importnateeee para usar Type::
use App\Models\Estudiante;

class EstudianteType extends GraphQLType
{
    protected $attributes = [
        'name' => 'Estudiante',
        'description' => 'A type',
        'model' => Estudiante::class,
    ];

    public function fields(): array
    {
        return [
            'id' =>[
                'type' => Type::nonNull(Type::int()),
                'description' => 'The id of the estudiante'
            ],
            'CI' => [
                'type' => Type::nonNull(Type::string()),
                'description' => 'The CI of the estudiante'
            ],
            'nombre' => [
                'type' => Type::string(),
                'description' => 'The name of the estudiante',
            ],
            'primer_apellido' => [
                'type' => Type::string(),
                'description' => 'The first surname of the estudiante',
            ],
            'segundo_apellido' => [
                'type' => Type::string(),
                'description' => 'The second surname of the estudiante',
            ],
            'fecha_nacimiento' => [
                'type' => Type::string(),
                'description' => 'The birth date of the estudiante',
            ],

        ];
    }
}
