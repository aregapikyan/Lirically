package com.example.myapplication.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.db.MIGRATION_2_3
import com.example.myapplication.db.converters.TiviTypeConverters
import com.example.myapplication.db.daos.FavouritesDao
import com.example.myapplication.db.entities.FavouritesEntry

@Database(entities = [FavouritesEntry::class], version = 3)
@TypeConverters(TiviTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "myDB"
                    ).addMigrations(MIGRATION_2_3)
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}
