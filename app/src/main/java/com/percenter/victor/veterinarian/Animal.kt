package com.percenter.victor.veterinarian

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by victor on 09/04/18.
 */
@Entity
class Animal(nome: String, especie: String, raca: String, peso: Double, nascimento: String, porte: Char)
{
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @ColumnInfo(name = "nome")
    var nome: String? = null

    @ColumnInfo(name = "especie")
    var especie: String? = null

    @ColumnInfo(name = "raca")
    var raca: String? = null

    @ColumnInfo(name = "peso")
    var peso: Double? = null

    @ColumnInfo(name = "nascimento")
    var nascimento: String? = null

    @ColumnInfo(name = "porte")
    var porte: Char? = null

    init
    {
        this.id = id
        this.nome = nome
        this.especie = especie
        this.raca = raca
        this.peso = peso
        this.nascimento = nascimento
        this.porte = porte
    }
}