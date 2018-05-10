package com.percenter.victor.veterinarian.remote.services.services

import com.percenter.victor.veterinarian.Animal

/**
 * Created by aluno on 03/05/18.
 */
interface AnimalRemotoListener
{
    fun onGetAnimaisReturn(animais: List<Animal>)
    fun onAnimalError(mensagem: String)
}