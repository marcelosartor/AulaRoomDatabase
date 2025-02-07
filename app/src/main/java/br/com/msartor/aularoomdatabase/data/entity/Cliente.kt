package br.com.msartor.aularoomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clientes")
data class Cliente(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nome: String,
    val sobrenome: String
) {
}