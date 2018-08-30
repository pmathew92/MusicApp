package com.prince.musicapp.repository.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.prince.musicapp.model.AutocompleteSuggestions
import io.reactivex.Single

@Dao
interface SuggestionDao {
    @Query("SELECT * from suggestions")
    fun getAllSuggestions(): Single<List<AutocompleteSuggestions>>

    @Insert(onConflict = REPLACE)
    fun addSuggestion(suggestion: AutocompleteSuggestions)
}