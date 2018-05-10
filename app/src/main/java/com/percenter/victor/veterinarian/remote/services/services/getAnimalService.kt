package com.percenter.victor.veterinarian.remote.services.services

import com.percenter.victor.veterinarian.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by aluno on 03/05/18.
 */
interface getAnimalService
{
    @GET("getAnimal/{id}")
    fun buscaAnimal(@Path("id") id: Int): Call<List<Animal>>
}