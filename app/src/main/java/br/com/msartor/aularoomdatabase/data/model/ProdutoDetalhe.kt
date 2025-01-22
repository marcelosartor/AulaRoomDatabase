package br.com.msartor.aularoomdatabase.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "produto_detalhes",
    indices = [
        Index(value = ["produto_id"],unique = true )
    ],
    foreignKeys = [
        ForeignKey(
            entity = Produto::class,
            parentColumns = ["id"],
            childColumns=["produto_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class ProdutoDetalhe(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "produto_id")
    val produtoId: Long,
    val marca: String,
    val descricao: String


)
