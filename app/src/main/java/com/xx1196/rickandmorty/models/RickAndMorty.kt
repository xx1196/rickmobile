package com.xx1196.rickandmorty.models

data class RickAndMorty(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Data,
    val location: Data,
    val image: String,
    val episode: ArrayList<String>
)