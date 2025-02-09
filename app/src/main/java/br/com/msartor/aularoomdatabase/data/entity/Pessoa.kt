package br.com.msartor.aularoomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pessoas")
data class Pessoa(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nome: String,
    val sexo: String
)
