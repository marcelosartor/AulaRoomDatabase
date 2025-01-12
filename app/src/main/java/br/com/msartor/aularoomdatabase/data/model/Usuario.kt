package br.com.msartor.aularoomdatabase.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val idade: Int,
    val peso: Double,
    @Embedded
    val endereco: Endereco
)

data class Endereco(
    val rua: String,
    val numero: Int
)