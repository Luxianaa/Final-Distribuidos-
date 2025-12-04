<?php

declare(strict_types=1);

namespace App\GraphQL\Mutations;

use Closure;
use GraphQL\Type\Definition\ResolveInfo;
use GraphQL\Type\Definition\Type;
use Rebing\GraphQL\Support\Mutation;
use Rebing\GraphQL\Support\SelectFields;
use Rebing\GraphQL\Support\Facades\GraphQL;
use App\Models\Estudiante;

class EliminarEstudianteMutation extends Mutation
{
    protected $attributes = [
        'name' => 'eliminarEstudiante',
        'description' => 'A mutation',
        'model' => Estudiante::class,   
    ];

    public function type(): Type
    {
        return Type::string();
    }

    public function args(): array
    {
        return [
            // Define your mutation arguments here
            'CI' => [
                'name' => 'CI',
                'type' => Type::nonNull(Type::string()),
            ],

        ];
    }

    public function resolve($root, array $args, $context, ResolveInfo $resolveInfo, Closure $getSelectFields)
    {
        $fields = $getSelectFields();
        $select = $fields->getSelect();
        $with = $fields->getRelations();

        $estudiante = Estudiante::where('CI', $args['CI'])->first();

        if (!$estudiante) {
            throw new \Exception("El estudiante con CI {$args['CI']} no existe");
        }

        $estudiante->delete();

        return "Estudiante eliminado correctamente";
    }
}
