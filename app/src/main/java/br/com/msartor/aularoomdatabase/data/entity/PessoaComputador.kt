package br.com.msartor.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "pessoas_computadores", primaryKeys = ["pessoa_id","computador_id"])
data class PessoaComputador(
    @ColumnInfo("pessoa_id")
    val pessoaId:Long,
    @ColumnInfo("computador_id")
    val computadorId:Long
)
