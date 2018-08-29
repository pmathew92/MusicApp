package com.prince.musicapp.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.dao.FavouritesDao


@Database(entities = [(Result::class)], version = 1)
abstract class TrackDataBase : RoomDatabase() {

    abstract fun favouriteDataDao(): FavouritesDao

    companion object {
        private var INSTANCE: TrackDataBase? = null

        fun getInstance(context: Context): TrackDataBase? {
            if (INSTANCE == null) {
                synchronized(TrackDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TrackDataBase::class.java, "track.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}