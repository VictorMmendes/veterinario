package com.percenter.victor.veterinarian

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update



/**
 * Created by victor on 11/04/18.
 */
@Dao
interface AnimalDAO
{
    @Insert
    fun addAnimal(animal: Animal)

    @Query("select * from animal")
    fun animais(): List<Animal>

    @Query("SELECT * FROM animal WHERE id = :id")
    fun findById(id: Int): Animal

    @Update
    fun updateAnimal(animal: Animal)

    @Query("SELECT * FROM animal WHERE nome LIKE :busca OR especie LIKE :busca")
    fun search(busca: String): List<Animal>

    @Delete
    fun delAnimal(animal: Animal)
}