package br.com.msartor.aularoomdatabase.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.msartor.aularoomdatabase.data.dao.EnderecoDao
import br.com.msartor.aularoomdatabase.data.dao.UsuarioDao
import br.com.msartor.aularoomdatabase.data.migration.Migration2To3
import br.com.msartor.aularoomdatabase.data.migration.Migration3To4
import br.com.msartor.aularoomdatabase.data.migration.Migration4To5
import br.com.msartor.aularoomdatabase.data.migration.Migration5To6
import br.com.msartor.aularoomdatabase.data.model.Conversor
import br.com.msartor.aularoomdatabase.data.model.Endereco
import br.com.msartor.aularoomdatabase.data.model.Usuario

@Database(
    entities = [
        Usuario::class,
        Endereco::class
               ],
    version = 6,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = Migration2To3::class),
        AutoMigration(from = 3, to = 4, spec = Migration3To4::class),
        AutoMigration(from = 4, to = 5, spec = Migration4To5::class)
    ]
)
@TypeConverters(Conversor::class)
abstract class BancoDeDados: RoomDatabase() {
    //abstract fun getInstanceUsuarioDao(): UsuarioDao
    abstract val usuarioDao: UsuarioDao
    abstract val enderecoDao: EnderecoDao

    companion object {

        /* 1a forma
        val migration5To6 = object : Migration(5,6){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("""
                    CREATE TABLE IF NOT EXISTS enderecos(
                        id integer not null primary key autoincrement,
                        rua text not null,
                        numero integer
                    )
                """.trimIndent())
            }
        }
        */

        // 2a forma
        val migration5To6 = Migration5To6(5,6)

fun getInstance(context: Context): BancoDeDados {
    return Room.databaseBuilder(
        context.applicationContext,
        BancoDeDados::class.java,
        "projeto.db"
    ).addMigrations( migration5To6 )
    .addTypeConverter(Conversor())
    .build()
}


}


}