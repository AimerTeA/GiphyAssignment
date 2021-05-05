package com.example.giphyassignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gif")
data class GiphyGifObject(
    @PrimaryKey
    val gifId: String,
    val url: String,
    val username: String,
    val title: String,
    var isSaved: Boolean = false
)