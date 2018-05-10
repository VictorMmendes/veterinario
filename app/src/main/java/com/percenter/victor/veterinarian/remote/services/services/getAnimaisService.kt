package com.percenter.victor.veterinarian.remote.services.services

import com.percenter.victor.veterinarian.Animal
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by aluno on 03/05/18.
 */
interface getAnimaisService
{
    @GET("getAllAnimals")
    fun buscaAnimais(): Call<List<Animal>>
}