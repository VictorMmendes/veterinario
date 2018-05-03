package com.percenter.victor.veterinarian.remote.services.services

import com.percenter.victor.veterinarian.Animal
import retrofit2.Call
import retrofit2.http.PUT
import retrofit2.http.Query

/**
 * Created by aluno on 03/05/18.
 */
interface editAnimalService
{
    @PUT("/editAnimal")
    fun editAnimal(
            @Query("id") id: Int,
            @Query("nome") nome: String,
            @Query("especie") especie: String,
            @Query("raca") raca: String,
            @Query("nascimento") nascimento: String,
            @Query("porte") porte: Char,
            @Query("peso") peso: Double
    ): Call<String>
}