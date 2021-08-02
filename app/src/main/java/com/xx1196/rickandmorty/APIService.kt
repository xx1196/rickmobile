package com.xx1196.rickandmorty

import com.xx1196.rickandmorty.models.DataRickAndMorty
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("character")
    suspend fun getAllCharacters(): Response<DataRickAndMorty>
}