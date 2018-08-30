package com.prince.musicapp.ui

import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.DataSource
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers


class PlayerPresenterImpl(val view: PlayerActivity, val dataSource: DataSource<Result>) : PlayerContract.PlayerPresenter {
    init {
        view.presenter = this
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun checkIfAlreadyFavourite(item: Result) {
        dataSource.hasItem(item)
                .subscribeOn(Schedulers.io())
                .subscribe({ _ -> view.isFavourite() }, { error ->
                    error.printStackTrace()
                })
    }

    override fun addToFavourite(item: Result) {
        Completable.fromAction { dataSource.addItem(item) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }
}