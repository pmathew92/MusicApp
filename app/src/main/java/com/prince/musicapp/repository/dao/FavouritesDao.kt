package com.prince.musicapp.repository.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.prince.musicapp.model.Result
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface FavouritesDao {
    @Query("SELECT * from favourites")
    fun getAll(): Single<List<Result>>

    @Insert(onConflict = REPLACE)
    fun insert(favourite: Result)

    @Delete
    fun remove(favourite: Result)

    @Query("SELECT * FROM favourites WHERE trackId = :id")
    fun checkFavourite(id: Int): Maybe<Result>
}