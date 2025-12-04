<?php

declare(strict_types=1);

namespace App\GraphQL\Queries;

use Closure;
use GraphQL\Type\Definition\ResolveInfo;
use GraphQL\Type\Definition\Type;
use Rebing\GraphQL\Support\Query;
use Rebing\GraphQL\Support\SelectFields;
use Rebing\GraphQL\Support\Facades\GraphQL;//importanteee
// use Grpahql; DEPENDEEE IMPORTAHTE LLAMARLAS
use App\Models\Estudiante;

class BuscarEstudianteQuery extends Query
{
    protected $attributes = [
        'name' => 'buscarEstudiante',
        'description' => 'A query that searches for students by CI',
        'model' => Estudiante::class,
    ];

    public function type(): Type
    {
        //para que devuelva un estudiante
        return GraphQL::type('Estudiante');
    }

    public function args(): array
    {
        return [
            'CI' => [
                'type' => Type::nonNull(Type::string()),
                'description' => 'The CI of the student to search for',
            ],
        ];
    }

    public function resolve($root, array $args, $context, ResolveInfo $resolveInfo, Closure $getSelectFields)
    {
        /** @var SelectFields $fields */
        $fields = $getSelectFields();
        $select = $fields->getSelect();
        $with = $fields->getRelations();

        return Estudiante::where('CI', $args['CI'])->first();
        //where porque si usamos find siempre buscara el id hay que especificar.
    }
}
