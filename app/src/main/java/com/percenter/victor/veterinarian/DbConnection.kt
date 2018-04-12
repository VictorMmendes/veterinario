package com.percenter.victor.veterinarian

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by victor on 11/04/18.
 */
@Database(entities = arrayOf(Animal::class), version = 1)
abstract class DbConnection: RoomDatabase()
{
    abstract fun animalDAO(): AnimalDAO
}