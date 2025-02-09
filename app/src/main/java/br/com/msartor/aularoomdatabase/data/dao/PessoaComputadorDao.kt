package br.com.msartor.aularoomdatabase.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.msartor.aularoomdatabase.data.entity.Computador
import br.com.msartor.aularoomdatabase.data.entity.Pessoa
import br.com.msartor.aularoomdatabase.data.entity.PessoaComputador
import br.com.msartor.aularoomdatabase.data.entity.relation.PessoaComComputadores

@Dao
interface PessoaComputadorDao {
    @Insert
    fun salvarPessoa(pessoa: Pessoa):Long

    @Insert
    fun salvarComputador(computador: Computador):Long

    @Insert
    fun salvarPessoaComputador(pessoaComputador: PessoaComputador): Void

    @Transaction
    @Query("Select * from pessoas")
    fun listarPessoaComComputadores(): List<PessoaComComputadores>


}