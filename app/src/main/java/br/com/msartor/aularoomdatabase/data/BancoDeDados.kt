package br.com.msartor.aularoomdatabase.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.msartor.aularoomdatabase.data.dao.UsuarioDao
import br.com.msartor.aularoomdatabase.data.migration.Migration2To3
import br.com.msartor.aularoomdatabase.data.migration.Migration3To4
import br.com.msartor.aularoomdatabase.data.model.Conversor
import br.com.msartor.aularoomdatabase.data.model.Usuario

@Database(
    entities = [Usuario::class],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = Migration2To3::class),
        AutoMigration(from = 3, to = 4, spec = Migration3To4::class)
    ]
)
@TypeConverters(Conversor::class)
abstract class BancoDeDados: RoomDatabase() {
    companion object {
        fun getInstance(context: Context): BancoDeDados {
            return Room.databaseBuilder(
                context.applicationContext,
                BancoDeDados::class.java,
                "projeto.db"
            ).addTypeConverter(Conversor())
            .build()
        }
    }

    abstract fun getInstanceUsuarioDao(): UsuarioDao
}