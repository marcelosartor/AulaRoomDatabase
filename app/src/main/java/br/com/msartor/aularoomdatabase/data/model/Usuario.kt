package br.com.msartor.aularoomdatabase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val idade: Int,
    val peso: Double,
)