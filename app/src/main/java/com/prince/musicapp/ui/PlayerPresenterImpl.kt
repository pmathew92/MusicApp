package com.prince.musicapp.ui

class PlayerPresenterImpl(val view: PlayerActivity) : PlayerContract.PlayerPresenter {
    init {
        view.presenter = this
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }
}