package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import br.com.msartor.aularoomdatabase.data.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    fun salvar(usuario: Usuario)

    @Delete
    fun remover(usuario: Usuario)
}