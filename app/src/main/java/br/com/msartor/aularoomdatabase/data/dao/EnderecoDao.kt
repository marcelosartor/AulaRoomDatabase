package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import br.com.msartor.aularoomdatabase.data.model.Endereco
import br.com.msartor.aularoomdatabase.data.model.Usuario

@Dao
interface EnderecoDao {
    @Insert
    fun salvar(endereco: Endereco)

    @Delete
    fun remover(endereco: Endereco)

    @Update
    fun atualizat(endereco: Endereco)

}