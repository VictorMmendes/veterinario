package com.percenter.victor.veterinarian

/**
 * Created by victor on 09/04/18.
 */
class Animal(nome: String, especie: String, raca: String, peso: Double, nascimento: String, porte: Char)
{
    var id: Int = 0
    var nome: String
    var especie: String
    var raca: String
    var peso: Double
    var nascimento: String
    var porte: Char

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