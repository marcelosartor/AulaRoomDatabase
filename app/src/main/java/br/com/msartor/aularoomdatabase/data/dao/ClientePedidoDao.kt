package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.msartor.aularoomdatabase.data.entity.Cliente
import br.com.msartor.aularoomdatabase.data.entity.Pedido
import br.com.msartor.aularoomdatabase.data.entity.Produto
import br.com.msartor.aularoomdatabase.data.entity.ProdutoDetalhe
import br.com.msartor.aularoomdatabase.data.entity.relation.ClienteComPedidos
import br.com.msartor.aularoomdatabase.data.entity.relation.ProdutoEProdutoDetalhe

@Dao
interface ClientePedidoDao {

    @Insert
    fun salvarCliente(cliente: Cliente):Long

    @Insert
    fun salvarPedido(pedido: Pedido):Long

    @Transaction
    @Query("Select * from clientes")
    fun listarClienteComPedidod(): List<ClienteComPedidos>
}