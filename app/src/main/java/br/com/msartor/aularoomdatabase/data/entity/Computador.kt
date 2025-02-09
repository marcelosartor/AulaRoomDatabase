package br.com.msartor.aularoomdatabase.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "computadores")
data class Computador(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val modelo: String,
    val marca: String,
)
