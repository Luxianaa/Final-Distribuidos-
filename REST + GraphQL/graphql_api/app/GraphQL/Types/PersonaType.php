<?php

declare(strict_types=1);

namespace App\GraphQL\Types;

use Rebing\GraphQL\Support\Type as GraphQLType;

class PersonaType extends GraphQLType
{
    protected $attributes = [
        'name' => 'Persona',
        'description' => 'A type'
    ];

    public function fields(): array
    {
        return [

        ];
    }
}
