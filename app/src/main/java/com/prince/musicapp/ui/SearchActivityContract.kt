package com.prince.musicapp.ui

import com.prince.musicapp.BasePresenter
import com.prince.musicapp.BaseView

interface SearchActivityContract {
    interface SearchView : BaseView<SearchPresenter> {

    }

    interface SearchPresenter : BasePresenter {
        fun searchQuery(query:String)
    }
}