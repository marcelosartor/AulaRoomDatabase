package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import br.com.msartor.aularoomdatabase.data.entity.Produto
import br.com.msartor.aularoomdatabase.data.entity.ProdutoDetalhe
import br.com.msartor.aularoomdatabase.data.entity.relation.ProdutoEProdutoDetalhe

@Dao()
interface ProdutoDao {
    @Insert
    fun salvarProduto(produto: Produto):Long

    @Insert
    fun salvarProdutoDetalhe(produtoDetalhe: ProdutoDetalhe):Long

    @Update
    fun atualizatProduto(produto: Produto)

    @Update
    fun atualizatProdutoDetalhe(produtoDetalhe: ProdutoDetalhe)

    @Delete
    fun removerProduto(produto: Produto)

    @Delete
    fun removerProdutoDetalhe(produtoDetalhe: ProdutoDetalhe)

    @Query("Select * from produtos")
    fun listarProdutos(): List<Produto>

    @Query("Select * from produto_detalhes")
    fun listarProdutoDetalhe(): List<ProdutoDetalhe>

    @Transaction
    @Query("Select * from produtos")
    fun listarProdutosEProdutoDetalhe(): List<ProdutoEProdutoDetalhe>
}
