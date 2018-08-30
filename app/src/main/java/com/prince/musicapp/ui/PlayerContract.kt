package com.prince.musicapp.ui

import com.prince.musicapp.BasePresenter
import com.prince.musicapp.BaseView
import com.prince.musicapp.model.Result


interface PlayerContract {
    interface PlayerView : BaseView<PlayerPresenter> {
        fun isFavourite()
    }

    interface PlayerPresenter : BasePresenter {
        fun checkIfAlreadyFavourite(item: Result)
        fun addToFavourite(item: Result)
    }
}