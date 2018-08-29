package com.prince.musicapp.adapter


import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.prince.musicapp.GlideApp
import com.prince.musicapp.R
import com.prince.musicapp.model.Result
import com.prince.musicapp.ui.ItemFragment.OnListFragmentInteractionListener
import com.prince.musicapp.ui.PlayerActivity
import kotlinx.android.synthetic.main.fragment_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
        private val mValues: List<Result>,
        private val mContext: Context)
    : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        private val mTrackName = mView.tv_track_name!!
        val mCardView = mView.layout_track!!
        private val mTrackImage = mView.iv_track_image!!
        private val mArtist = mView.tv_artist_name!!
        private val mAlbum = mView.tv_album_name!!

        fun bindData(item: Result) {
            mTrackName.text = item.getTrackName()
            mArtist.text = item.getArtistName()
            mAlbum.text = item.getCollectionCensoredName()
            GlideApp.with(mContext)
                    .load(item.getArtworkUrl100())
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .into(mTrackImage)
            mCardView.setOnClickListener {
                val intent= Intent(mContext,PlayerActivity::class.java)
                intent.putExtra("audio_track",item)
                mContext.startActivity(intent)
            }
        }
    }
}
