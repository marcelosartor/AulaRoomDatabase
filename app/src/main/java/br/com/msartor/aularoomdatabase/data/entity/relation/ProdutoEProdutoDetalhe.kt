package br.com.msartor.aularoomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Relation
import br.com.msartor.aularoomdatabase.data.entity.Produto
import br.com.msartor.aularoomdatabase.data.entity.ProdutoDetalhe

data class ProdutoEProdutoDetalhe(
    @Embedded
    val produto: Produto,

    @Relation(
        parentColumn = "id",
        entityColumn = "produto_id",
        entity = ProdutoDetalhe::class
    )
    val produtoDetalhe: ProdutoDetalhe
)
