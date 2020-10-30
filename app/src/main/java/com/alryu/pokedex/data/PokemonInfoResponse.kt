package com.alryu.pokedex.data

data class PokemonInfoResponse(
    val id: String,
    val height: String,
    val weight: String,
    val name: String,
    val abilities: List<Ability>
) {

    data class Ability(
        val ability: AbilityPartialInfo,
        val slot: Int
    )

    data class AbilityPartialInfo(
        val name: String,
        val url: String
    )
}

val PokemonInfoResponse.imageUrl: String
    get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

val PokemonInfoResponse.imageLargeUrl: String
    get() = "https://pokeres.bastionbot.org/images/pokemon/$id.png"