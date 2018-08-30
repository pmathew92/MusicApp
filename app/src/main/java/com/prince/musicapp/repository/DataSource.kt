package com.prince.musicapp.repository

import io.reactivex.Maybe
import io.reactivex.Single

interface DataSource<T> {
    fun addItem(item: T)
    fun removeItem(item: T)
    fun fetchItem(): Single<List<T>>
    fun hasItem(item: T): Maybe<T>
}