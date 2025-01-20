package br.com.msartor.aularoomdatabase.data.migration

import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.migration.AutoMigrationSpec

// Exemplo para renomear tabelas
//@RenameTable(fromTableName = "usuarios", toTableName = "app_usuarios")
@RenameColumn(tableName = "usuarios", fromColumnName = "sexo", toColumnName = "sexo_usuario")
class Migration2To3 : AutoMigrationSpec {

}