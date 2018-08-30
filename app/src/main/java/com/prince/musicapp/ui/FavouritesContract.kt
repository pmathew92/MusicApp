package com.prince.musicapp.ui

import com.prince.musicapp.BasePresenter
import com.prince.musicapp.BaseView
import com.prince.musicapp.model.Result


interface FavouritesContract {
    interface FavouritesView : BaseView<FavouritesPresenter> {
        fun loadFavourites(result: List<Result>)
    }

    interface FavouritesPresenter : BasePresenter {
        fun removeItem(item: Result)
    }
}