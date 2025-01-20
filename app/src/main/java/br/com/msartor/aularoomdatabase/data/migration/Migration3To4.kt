package br.com.msartor.aularoomdatabase.data.migration

import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.migration.AutoMigrationSpec

@DeleteColumn(tableName = "usuarios", columnName = "sexo_usuario")
class Migration3To4 : AutoMigrationSpec {

}