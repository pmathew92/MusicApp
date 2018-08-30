package com.prince.musicapp.ui

import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.DataSource
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PlayerPresenterImpl(val view: PlayerActivity, val dataSource: DataSource<Result>) : PlayerContract.PlayerPresenter {
    private val disposable = CompositeDisposable()

    init {
        view.presenter = this
    }

    override fun subscribe() {
        view.initializePlayer()
    }

    override fun unSubscribe() {
        view.releasePlayer()
        if (!disposable.isDisposed)
            disposable.clear()
    }

    override fun checkIfAlreadyFavourite(item: Result) {
        disposable.add(dataSource.hasItem(item)
                .subscribeOn(Schedulers.io())
                .subscribe({ _ -> view.isFavourite() }, { error ->
                    error.printStackTrace()
                }))
    }

    override fun addToFavourite(item: Result) {
        disposable.add(Completable.fromAction { dataSource.addItem(item) }
                .subscribeOn(Schedulers.io())
                .subscribe())
    }
}