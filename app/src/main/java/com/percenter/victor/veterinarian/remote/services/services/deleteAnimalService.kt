package com.percenter.victor.veterinarian.remote.services.services

import com.percenter.victor.veterinarian.Animal
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Query

/**
 * Created by aluno on 03/05/18.
 */
interface deleteAnimalService
{
    @DELETE("/deleteAnimal")
    fun deleteAnimal(
            @Query("id") id: Int
    ):Call<String>
}