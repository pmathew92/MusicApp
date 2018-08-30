package com.prince.musicapp.ui

import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.DataSource
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class FavouritesPresenterImpl(val view: FavouritesActivity, private val dataSource: DataSource<Result>) : FavouritesContract.FavouritesPresenter {
    init {
        view.presenter = this
    }

    override fun subscribe() {
    }

    override fun unSubscribe() {
    }

    override fun loadFavourites() {
        dataSource.fetchItem()
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    view.loadFavourites(result)
                }, { error ->
                    error.printStackTrace()
                })
    }

    override fun removeItem(item: Result) {
        Completable.fromAction { dataSource.removeItem(item) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }
}
