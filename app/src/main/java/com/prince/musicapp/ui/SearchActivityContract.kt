package com.prince.musicapp.ui

import com.prince.musicapp.BasePresenter
import com.prince.musicapp.BaseView
import com.prince.musicapp.model.AutocompleteSuggestions
import com.prince.musicapp.model.Result

interface SearchActivityContract {
    interface SearchView : BaseView<SearchPresenter> {
        fun setSongsCount(count: Int)
        fun setSongResults(songs: List<Result>)
        fun loadSuggestions(result: List<AutocompleteSuggestions>)
    }

    interface SearchPresenter : BasePresenter {
        fun searchQuery(query: String)
        fun addSuggestion(text: AutocompleteSuggestions)
    }
}