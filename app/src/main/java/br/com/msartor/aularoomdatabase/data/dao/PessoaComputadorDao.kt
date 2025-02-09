package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import br.com.msartor.aularoomdatabase.data.entity.Cliente
import br.com.msartor.aularoomdatabase.data.entity.Computador
import br.com.msartor.aularoomdatabase.data.entity.Pedido
import br.com.msartor.aularoomdatabase.data.entity.Pessoa
import br.com.msartor.aularoomdatabase.data.entity.PessoaComputador

@Dao
interface PessoaComputadorDao {
    @Insert
    fun salvarPessoa(pessoa: Pessoa):Long

    @Insert
    fun salvarComputador(computador: Computador):Long

    @Insert
    fun salvarPessoaComputador(pessoaComputador: PessoaComputador): Void

}