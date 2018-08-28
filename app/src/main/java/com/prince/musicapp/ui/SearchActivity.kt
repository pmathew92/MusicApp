package com.prince.musicapp.ui

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
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import com.prince.musicapp.R
import com.prince.musicapp.model.Result
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import kotlin.collections.ArrayList


class SearchActivity : AppCompatActivity(), SearchActivityContract.SearchView, ItemFragment.OnListFragmentInteractionListener {
    private var songList: List<Result> = ArrayList()
    override lateinit var presenter: SearchActivityContract.SearchPresenter
    private var dots: Array<ImageView?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.music_symbol)

        SearchPresenterImpl(this)
        et_search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(editable: Editable) {
                if (!editable.toString().isEmpty())
                    presenter.searchQuery(editable.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val vto = view_pager.getViewTreeObserver()
        vto.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view_pager.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                val width = view_pager.getMeasuredWidth()
                val height = view_pager.getMeasuredHeight()
                Log.d("PLING", "${height}")
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.favorites -> {
                Toast.makeText(applicationContext, "Menu clicked", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setSongsCount(count: Int) {
        tv_total_songs.text = "All Songs - ${count}"
    }

    override fun setSongResults(songs: List<Result>) {
        songList = songs
        val partitionSize = 10
        val partitions = LinkedList<List<Result>>()
        var i = 0
        while (i < songList.size) {
            partitions.add(ArrayList<Result>(songList.subList(i,
                    Math.min(i + partitionSize, songList.size))))
            i += partitionSize
        }
        setupViewPager(partitions)
    }

    private fun setupViewPager(partitionedList: LinkedList<List<Result>>) {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
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

    override fun onListFragmentInteraction(item: Result?) {

    }
}
