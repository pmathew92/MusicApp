package com.prince.musicapp.ui

class FavouritesPresenterImpl(val view: FavouritesActivity) : FavouritesContract.FavouritesPresenter {
    init {
        view.presenter = this
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
    }

}
