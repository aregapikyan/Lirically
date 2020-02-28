package com.example.myapplication.db.daos

import androidx.room.*
import com.example.myapplication.db.entities.FavouritesEntry
import com.example.myapplication.mvp.models.songModel.SongViewModel

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavourite(favouritesEntry: FavouritesEntry)

    @Update
    fun updateFavourite(favouritesEntry: FavouritesEntry)

    @Query("DELETE FROM favourites WHERE name == :name AND title == :title")
    fun deleteFavourite(name: String?, title: String?)

    @Query("SELECT * FROM favourites WHERE title == :title AND name == :name")
    fun getFavourite(name: String?, title: String?): FavouritesEntry

    @Query("SELECT * FROM favourites ORDER BY datetime(joined_date) DESC")
    fun getFavourites(): List<FavouritesEntry>
}