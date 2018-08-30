package com.prince.musicapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.prince.musicapp.R
import com.prince.musicapp.adapter.FavouritesAdapter
import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.datasource.FavouriteDataSource
import kotlinx.android.synthetic.main.activity_favourites.*
import kotlinx.android.synthetic.main.content_favourites.*


class FavouritesActivity : AppCompatActivity(), FavouritesContract.FavouritesView, FavouritesAdapter.ItemListener {
    override lateinit var presenter: FavouritesContract.FavouritesPresenter
    private var mAdapter: FavouritesAdapter? = null
    private var songCount = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        FavouritesPresenterImpl(this, FavouriteDataSource(this))

        rv_favourites.setHasFixedSize(true)
        rv_favourites.itemAnimator = DefaultItemAnimator()
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(rv_favourites)
        presenter.subscribe()
    }


    private val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            mAdapter?.removeItem(position)
        }
    }

    override fun loadFavourites(result: List<Result>) {
        mAdapter = FavouritesAdapter(result as MutableList<Result>, this)
        rv_favourites.adapter = mAdapter
        setSongCount(result.size)
    }

    private fun setSongCount(count: Int) {
        songCount = count
        tv_total_favourites.text = "All Songs - ${count}"
    }

    override fun removeItem(item: Result) {
        setSongCount(--songCount)
        presenter.removeItem(item)
    }

    override fun onDestroy() {
        presenter.unSubscribe()
        super.onDestroy()
    }
}
