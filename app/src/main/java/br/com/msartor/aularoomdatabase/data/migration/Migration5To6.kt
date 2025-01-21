package br.com.msartor.aularoomdatabase.data.migration

import androidx.room.DeleteColumn
import androidx.room.RenameColumn
import androidx.room.RenameTable
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration5To6(startVersion: Int, endVersion: Int) : Migration(startVersion, endVersion) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            CREATE TABLE IF NOT EXISTS enderecos(
                id integer not null primary key autoincrement,
                rua text not null,
                numero integer not null 
            )
        """.trimIndent())
    }

}