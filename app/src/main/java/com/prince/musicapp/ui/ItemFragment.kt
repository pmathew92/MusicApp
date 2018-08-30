package com.prince.musicapp.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prince.musicapp.R
import com.prince.musicapp.adapter.MyItemRecyclerViewAdapter
import com.prince.musicapp.model.Result
import kotlinx.android.synthetic.main.fragment_item_list.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ItemFragment.OnListFragmentInteractionListener] interface.
 */
class ItemFragment : Fragment() {

    private var itemList: List<Result> = ArrayList()

    private var adapter: MyItemRecyclerViewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        itemList = arguments?.getParcelableArrayList("resultList")!!
        adapter = activity?.applicationContext?.let { MyItemRecyclerViewAdapter(itemList, it) }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_items.setHasFixedSize(true)
        rv_items.itemAnimator = DefaultItemAnimator()
        rv_items.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


}
