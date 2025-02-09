package br.com.msartor.aularoomdatabase.data.entity.relation

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Relation
import br.com.msartor.aularoomdatabase.data.entity.Cliente
import br.com.msartor.aularoomdatabase.data.entity.Pedido
import br.com.msartor.aularoomdatabase.data.entity.Produto
import br.com.msartor.aularoomdatabase.data.entity.ProdutoDetalhe

data class ClienteComPedidos(
    @Embedded
    val cliente: Cliente,

    @Relation(
        parentColumn = "id", // Chave primaria Pedido
        entityColumn = "cliente_id", // Chave Estrangeira Cliente em Pedido
        entity = Pedido::class
    )
    val pedidos: List<Pedido>
)
