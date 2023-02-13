package com.example.apppokemontcg


data class PokemonResponse(val cards: List<PokemonCard>)

data class PokemonCard(
    val title: String,
    val urlToImage: String,
    var description:String
)


