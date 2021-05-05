package com.example.giphyassignment.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.giphyassignment.data.cache.database.dao.GiphyGifObjectDao
import com.example.giphyassignment.data.model.GiphyGifObject

@Database(entities = [GiphyGifObject::class], version = 1)
abstract class GiphyDatabase : RoomDatabase() {
    abstract fun giphyGifDao(): GiphyGifObjectDao

    companion object {
        @Volatile
        private var DATABASE_INSTANCE: GiphyDatabase? = null

        fun getDatabaseInstance(context: Context): GiphyDatabase {
            return DATABASE_INSTANCE ?: synchronized(this) {
                DATABASE_INSTANCE ?: buildDatabase(context).also { DATABASE_INSTANCE = it }
            }
        }

        fun buildDatabase(context: Context): GiphyDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                GiphyDatabase::class.java,
                "giphydatabase.db"
            ).build()
        }
    }
}