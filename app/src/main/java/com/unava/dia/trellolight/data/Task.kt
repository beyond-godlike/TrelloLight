package com.unava.dia.trellolight.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = Board::class,
        parentColumns = ["id"],
        childColumns = ["boardId"],
        onDelete = CASCADE
    )]
)

data class Task(var title: String, var description: String, var boardId: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}