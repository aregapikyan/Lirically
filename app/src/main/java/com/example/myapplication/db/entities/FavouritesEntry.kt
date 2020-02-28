package com.example.myapplication.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity(tableName = "favourites")

data class FavouritesEntry(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,

    val name: String? = null,
    val title: String? = null,
    val songArtImageUrl: String? = null,
    val lyrics: String? = null,
    val joined_date: OffsetDateTime? = null

)