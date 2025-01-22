package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import br.com.msartor.aularoomdatabase.data.model.Produto
import br.com.msartor.aularoomdatabase.data.model.ProdutoDetalhe
import br.com.msartor.aularoomdatabase.data.model.Usuario

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
}