package br.com.msartor.aularoomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import br.com.msartor.aularoomdatabase.data.entity.Computador
import br.com.msartor.aularoomdatabase.data.entity.Pessoa
import br.com.msartor.aularoomdatabase.data.entity.PessoaComputador

data class PessoaComComputadores(
    @Embedded
    val pessoa: Pessoa,

    @Relation(
        parentColumn = "id", // Chave Primaria Pessoa
        entityColumn = "id", // Chave Primaria de Computador
        entity = Computador::class,
        associateBy = Junction(PessoaComputador::class, parentColumn = "pessoa_id", entityColumn = "computador_id")

    )
    val computadores: List<Computador>
)
