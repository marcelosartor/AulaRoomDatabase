package br.com.msartor.aularoomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "enderecos")
data class Endereco(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val rua: String,
    val numero: Int
)
