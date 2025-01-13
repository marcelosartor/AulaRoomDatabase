package br.com.msartor.aularoomdatabase.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.msartor.aularoomdatabase.data.dao.UsuarioDao
import br.com.msartor.aularoomdatabase.data.model.Conversor
import br.com.msartor.aularoomdatabase.data.model.Usuario

@Database(entities = [Usuario::class], version = 1)
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