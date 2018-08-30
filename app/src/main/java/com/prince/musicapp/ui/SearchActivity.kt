package com.prince.musicapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import com.prince.musicapp.R
import com.prince.musicapp.model.AutocompleteSuggestions
import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.datasource.SuggestionDataSource
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import kotlin.collections.ArrayList


class SearchActivity : AppCompatActivity(), SearchActivityContract.SearchView {
    private var songList: List<Result> = ArrayList()
    override lateinit var presenter: SearchActivityContract.SearchPresenter
    private var dots: Array<ImageView?>? = null
    private var adapter: ArrayAdapter<AutocompleteSuggestions>? = null

    private var result: MutableList<AutocompleteSuggestions> = ArrayList()
    private var screenHeight = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.music_symbol)

        SearchPresenterImpl(this, SuggestionDataSource(this))

        adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, result)
        adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        et_search.setAdapter(adapter)
        et_search.threshold = 1

        presenter.fetchSuggestion()

        et_search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable) {
                if (!editable.toString().isEmpty())
                    presenter.searchQuery(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val vto = view_pager.viewTreeObserver
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view_pager.viewTreeObserver.removeOnGlobalLayoutListener(this)
                val height = view_pager.measuredHeight

                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT)
                screenHeight = (height * .8).toInt()
                params.height = screenHeight
                view_pager.layoutParams = params
            }
        })
    }


    override fun loadSuggestions(result: List<AutocompleteSuggestions>) {
        this.result.addAll(result as MutableList<AutocompleteSuggestions>)
        adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.favorites -> {
                startActivity(Intent(applicationContext, FavouritesActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun setSongsCount(count: Int) {
        tv_total_songs.text = "All Songs - ${count}"
    }

    override fun setSongResults(songs: List<Result>) {
        updateSuggestionList()
        songList = songs
        var partitionSize = 4
        if (screenHeight > 0)
            partitionSize = screenHeight / (112 * 2)
        val partitions = LinkedList<List<Result>>()
        var i = 0
        while (i < songList.size) {
            partitions.add(ArrayList<Result>(songList.subList(i,
                    Math.min(i + partitionSize, songList.size))))
            i += partitionSize
        }
        setupViewPager(partitions)
    }

    private fun updateSuggestionList() {
        if (!et_search.text.isEmpty() && et_search.text.length > 3) {
            presenter.addSuggestion(AutocompleteSuggestions(et_search.text.toString()))
            result.add(AutocompleteSuggestions(et_search.text.toString()))
            adapter?.notifyDataSetChanged()
        }
    }

    private fun setupViewPager(partitionedList: LinkedList<List<Result>>) {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        slider_dots.removeAllViewsInLayout()
        for (i in 0 until partitionedList.size) {
            val mFragment = ItemFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("resultList", partitionedList[i] as ArrayList<out Parcelable>)
            mFragment.arguments = bundle
            viewPagerAdapter.addFragment(mFragment, "Fragment${i}")
        }
        view_pager.adapter = viewPagerAdapter
        dots = arrayOfNulls(partitionedList.size)

        for (i in 0 until partitionedList.size) {

            dots!![i] = ImageView(this)
            dots!![i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.tab_unselected))

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            params.setMargins(8, 0, 8, 0)

            slider_dots.addView(dots!![i], params)
        }
        dots!![0]?.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.tab_selected));
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                for (i in 0 until partitionedList.size) {
                    dots!![i]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.tab_unselected))
                }
                dots!![position]?.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.tab_selected))
            }
        })
    }

    private inner class ViewPagerAdapter(mFragmentManager: FragmentManager) : FragmentStatePagerAdapter(mFragmentManager) {
        private val fragmentList = ArrayList<Fragment>()
        private val fragmentTag = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        fun addFragment(fragment: Fragment, tag: String) {
            fragmentList.add(fragment)
            fragmentTag.add(tag)
        }

        override fun getCount(): Int {
            return fragmentList.size
        }
    }

}
