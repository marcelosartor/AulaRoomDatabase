package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.msartor.aularoomdatabase.data.entity.Usuario

@Dao
interface UsuarioDao {
    @Insert
    fun salvar(usuario: Usuario)

    @Delete
    fun remover(usuario: Usuario)

    @Update
    fun atualizat(usuario: Usuario)

    @Query("Select * from usuarios Order by nome")
    fun listar() : List<Usuario>

    //@Query("Select * from usuarios where id = :textoPesquisa Order by nome")
    @Query("Select * from usuarios where nome like '%' || :textoPesquisa || '%' Order by nome")
    fun filtrar(textoPesquisa: String): List<Usuario>
}