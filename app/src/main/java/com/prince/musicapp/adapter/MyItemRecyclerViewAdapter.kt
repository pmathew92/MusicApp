package com.prince.musicapp.adapter


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.prince.musicapp.R
import com.prince.musicapp.model.Result
import com.prince.musicapp.ui.ItemFragment.OnListFragmentInteractionListener
import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
        private val mValues: List<Result>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Result
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bindData(item)


//        GlideApp.with()
//                .load(model.image)
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .placeholder(R.drawable.placeholder)
//                .centerCrop()
//                .into(image)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mTrackName = mView.tv_track_name
        val mCardView = mView.layout_track
        val mTrackImage = mView.iv_track_image
        val mArtist = mView.tv_artist_name
        val mAlbum = mView.tv_album_name

        fun bindData(item: Result) {
            mTrackName.text = item.getTrackName()
        }
    }
}
