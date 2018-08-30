package com.prince.musicapp.ui

import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.DataSource
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FavouritesPresenterImpl(val view: FavouritesActivity, private val dataSource: DataSource<Result>) : FavouritesContract.FavouritesPresenter {
    private val disposable = CompositeDisposable()

    init {
        view.presenter = this
    }

    override fun subscribe() {
        disposable.add(dataSource.fetchItem()
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    view.loadFavourites(result)
                }, { error ->
                    error.printStackTrace()
                }))
    }

    override fun unSubscribe() {
        if (!disposable.isDisposed)
            disposable.clear()
    }


    override fun removeItem(item: Result) {
        disposable.add(Completable.fromAction { dataSource.removeItem(item) }
                .subscribeOn(Schedulers.io())
                .subscribe())
    }
}
