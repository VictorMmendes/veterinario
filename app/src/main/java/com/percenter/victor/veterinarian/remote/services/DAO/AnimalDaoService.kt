package com.percenter.victor.veterinarian.remote.services.DAO

import com.percenter.victor.veterinarian.Animal
import com.percenter.victor.veterinarian.remote.services.services.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by aluno on 03/05/18.
 */
class AnimalDaoService(val listener: AnimalRemotoListener) {

    private var retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2/api")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun buscarTodas() {
        var service = retrofit.create(getAnimaisService::class.java)

        var call = service.buscaAnimais()

        call.enqueue(object : Callback<List<Animal>> {
            override fun onFailure(call: Call<List<Animal>>?, t: Throwable?) {
                listener.onAnimalError("ERROR on get animals")
            }

            override fun onResponse(call: Call<List<Animal>>?, response: Response<List<Animal>>?) {
                var animais: List<Animal> = response?.body()!!
                listener.onGetAnimaisReturn(animais)
            }
        })
    }

    fun inserir(animal: Animal)
    {
        var service = retrofit.create(insertAnimalService::class.java)

        var call = service.insertAnimal(animal.nome, animal.especie, animal.raca, animal.nascimento, animal.porte, animal.peso)

        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                listener.onAnimalError("ERROR on insert animals")
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {

            }
        })
    }

    fun edit(animal: Animal)
    {
        var service = retrofit.create(editAnimalService::class.java)

        var call = service.editAnimal(animal.id, animal.nome, animal.especie, animal.raca, animal.nascimento, animal.porte, animal.peso)

        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                listener.onAnimalError("ERROR on edit animals")
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {

            }
        })
    }

    fun delete(animal: Animal)
    {
        var service = retrofit.create(deleteAnimalService::class.java)

        var call = service.deleteAnimal(animal.id)

        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                listener.onAnimalError("ERROR on delete animals")
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {

            }
        })
    }
}
