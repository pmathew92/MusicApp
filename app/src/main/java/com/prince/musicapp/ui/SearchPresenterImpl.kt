package com.prince.musicapp.ui

import com.prince.musicapp.model.ApiResponse
import com.prince.musicapp.model.AutocompleteSuggestions
import com.prince.musicapp.network.NetworkClient
import com.prince.musicapp.repository.DataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchPresenterImpl(val view: SearchActivity, private val dataSource: DataSource<AutocompleteSuggestions>) :
        SearchActivityContract.SearchPresenter {
    private val publishSubject = PublishSubject.create<String>().toSerialized()

    init {
        view.presenter = this
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
    }

    override fun fetchSuggestion() {
        dataSource.fetchItem()
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    view.loadSuggestions(result)
                }, { error ->
                    error.printStackTrace()
                })
    }

    override fun addSuggestion(text: AutocompleteSuggestions) {
        Completable.fromAction { dataSource.addItem(text) }
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    override fun searchQuery(query: String) {
        publishSubject.onNext(query)
        publishSubject
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .switchMap { NetworkClient.getNetworkService().getSongs(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    (this::handleResult)(response)
                },
                        { error ->
                            error.printStackTrace()
                        })
    }


    private fun handleResult(response: ApiResponse) {
        view.setSongsCount(response.getResultCount())
        response.getResults()?.let { view.setSongResults(it) }
    }

}