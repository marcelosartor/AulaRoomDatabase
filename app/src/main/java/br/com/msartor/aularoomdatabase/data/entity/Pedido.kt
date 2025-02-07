package br.com.msartor.aularoomdatabase.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "pedidos",
    foreignKeys = [
        ForeignKey(
            entity = Cliente::class,
            parentColumns = ["id"],
            childColumns=["cliente_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "cliente_id")
    val clienteId: Long,
    val produto: String,
    val preco: Double
) {
}