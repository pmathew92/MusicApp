package com.prince.musicapp.ui

import com.prince.musicapp.model.ApiResponse
import com.prince.musicapp.network.NetworkClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchPresenterImpl(val view: SearchActivity) : SearchActivityContract.SearchPresenter {
    private val publishSubject = PublishSubject.create<String>().toSerialized()

    init {
        view.presenter = this
    }

    override fun subscribe() {

    }

    override fun unSubscribe() {
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

//    private fun fetchSearchResults() {
//        publishSubject
//                .debounce(300, TimeUnit.MILLISECONDS)
//                .distinctUntilChanged()
//                .switchMap { NetworkClient.getNetworkService().getSongs(it) }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ text -> Log.d("PLING", text.getResultCount().toString()) },
//                        { er ->
//                            er.printStackTrace()
//                        })
//    }
}