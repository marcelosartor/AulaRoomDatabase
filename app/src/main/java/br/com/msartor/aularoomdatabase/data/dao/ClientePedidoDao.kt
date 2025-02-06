package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.msartor.aularoomdatabase.data.entity.Cliente
import br.com.msartor.aularoomdatabase.data.entity.Pedido
import br.com.msartor.aularoomdatabase.data.entity.Produto
import br.com.msartor.aularoomdatabase.data.entity.ProdutoDetalhe

@Dao
interface ClientePedidoDao {

    @Insert
    fun salvarCliente(cliente: Cliente):Long

    @Insert
    fun salvarPedido(pedido: Pedido):Long
}