package com.prince.musicapp.ui

import com.prince.musicapp.BasePresenter
import com.prince.musicapp.BaseView


interface FavouritesContract {
    interface FavouritesView : BaseView<FavouritesPresenter> {

    }

    interface FavouritesPresenter : BasePresenter {

    }
}