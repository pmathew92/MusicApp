package com.prince.musicapp.repository.datasource

import android.content.Context
import com.prince.musicapp.model.AutocompleteSuggestions
import com.prince.musicapp.repository.DataSource
import com.prince.musicapp.repository.database.TrackDataBase
import io.reactivex.Maybe
import io.reactivex.Single


class SuggestionDataSource(context: Context) : DataSource<AutocompleteSuggestions> {


    private var mUserDatabase: TrackDataBase? = null

    init {
        mUserDatabase = TrackDataBase.getInstance(context)
    }

    override fun addItem(item: AutocompleteSuggestions) {
        mUserDatabase?.suggestionDataDao()?.addSuggestion(item)
    }

    override fun removeItem(item: AutocompleteSuggestions) {
    }

    override fun fetchItem(): Single<List<AutocompleteSuggestions>> {
        return mUserDatabase?.suggestionDataDao()?.getAllSuggestions()!!
    }

    override fun hasItem(item: AutocompleteSuggestions): Maybe<AutocompleteSuggestions> {
        TODO(" implemented") //To change body of created functions use File | Settings | File Templates.
    }

}