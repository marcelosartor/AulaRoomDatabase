package br.com.msartor.aularoomdatabase.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId


@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val idade: Int,
    val peso: Double,
    @Embedded
    val endereco: Endereco,
    val date: LocalDate,
    val time: LocalTime,
    @ColumnInfo(name = "date_time")
    val dateTime: LocalDateTime,
    //@ColumnInfo(name = "sexo_usuario", defaultValue = "")
    //val sexoUsuario: String = ""

)

data class Endereco(
    val rua: String,
    val numero: Int
)

@ProvidedTypeConverter
class Conversor{

    @TypeConverter
    fun converterDeLocalDateParaLong(localDate: LocalDate?): Long? {
        return localDate?.toEpochDay()
    }

    @TypeConverter
    fun converterDeLocalTimeLong(localTime: LocalTime?): Long? {
        return localTime?.toNanoOfDay()
    }


    @TypeConverter
    fun converterDeLocalDateTimeParaLong(localDateTime: LocalDateTime?): Long? {
        return localDateTime
            ?.atZone(ZoneId.systemDefault())
            ?.toInstant()
            ?.toEpochMilli()
    }

    @TypeConverter
    fun converterDeLongParaLocalDate(epochDays: Long?): LocalDate? {
        return epochDays?.let {
            LocalDate.ofEpochDay(epochDays)
        }
    }

    @TypeConverter
    fun converterDeLongParaLocalTime(nanos: Long?): LocalTime? {
        return nanos?.let {
            LocalTime.ofNanoOfDay(nanos)
        }
    }

    @TypeConverter
    fun converterDeLongParaLocalDateTime(epochMillis: Long?): LocalDateTime? {
        return epochMillis?.let {
            Instant.ofEpochMilli(epochMillis)
                .atZone(ZoneId.systemDefault()) // Associar ao fuso horário
                .toLocalDateTime()
        }
    }



}