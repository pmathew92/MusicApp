package com.prince.musicapp.repository.datasource

import android.content.Context
import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.DataSource
import com.prince.musicapp.repository.database.TrackDataBase
import io.reactivex.Maybe
import io.reactivex.Single

class FavouriteDataSource(context: Context) : DataSource<Result> {
    private var mUserDatabase: TrackDataBase? = null

    init {
        mUserDatabase = TrackDataBase.getInstance(context)
    }


    override fun addItem(item: Result) {
        mUserDatabase?.favouriteDataDao()?.insert(item)
    }

    override fun removeItem(item: Result) {
        mUserDatabase?.favouriteDataDao()?.remove(item)
    }

    override fun fetchItem(): Single<List<Result>> {
        return mUserDatabase?.favouriteDataDao()?.getAll()!!
    }


    override fun hasItem(item: Result): Maybe<Result> {
        return mUserDatabase?.favouriteDataDao()?.checkFavourite(item.getTrackId())!!
    }
}